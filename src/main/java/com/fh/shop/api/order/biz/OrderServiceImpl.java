package com.fh.shop.api.order.biz;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.fh.shop.api.cart.po.Cart;
import com.fh.shop.api.cart.po.CartItem;
import com.fh.shop.api.common.ServerResponse;
import com.fh.shop.api.common.SystemConstant;
import com.fh.shop.api.exception.StockLessException;
import com.fh.shop.api.mq.MQConfig;
import com.fh.shop.api.order.mapper.OrderItemMapper;
import com.fh.shop.api.order.mapper.OrderMapper;
import com.fh.shop.api.order.param.OrderParam;
import com.fh.shop.api.order.po.Order;
import com.fh.shop.api.order.po.OrderItem;
import com.fh.shop.api.order.vo.OrderConfirmVo;
import com.fh.shop.api.product.mapper.IProductMapper;
import com.fh.shop.api.recipient.biz.IRecipientService;
import com.fh.shop.api.recipient.mapper.RecipientMapper;
import com.fh.shop.api.recipient.po.Recipient;
import com.fh.shop.api.util.KeyUtil;
import com.fh.shop.api.util.RedisUtil;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service("orderService")
public class OrderServiceImpl implements IOrderService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Resource(name = "recipientService")
    private IRecipientService recipientService;

    @Autowired
    private IProductMapper productMapper;

    @Autowired
    private RecipientMapper recipientMapper;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OrderItemMapper orderItemMapper;


    @Override
    public ServerResponse findList(Long memberId) {
        //获取会员对应的收件人列表
        List<Recipient> recipientList = recipientService.findList(memberId);
        //获取会员对应的购物车信息
        String cartJson = RedisUtil.get(KeyUtil.buildCartKey(memberId));
        Cart cart = JSONObject.parseObject(cartJson, Cart.class);
        //组装要返回的信息
        OrderConfirmVo orderConfirmVo = new OrderConfirmVo();
        orderConfirmVo.setRecipientList(recipientList);
        orderConfirmVo.setCart(cart);
        return ServerResponse.success(orderConfirmVo);
    }

    @Override
    public ServerResponse generateOrder(OrderParam orderParam) {
        //将订单消息发送到消息队列中
        String orderParamJson = JSONObject.toJSONString(orderParam);
        rabbitTemplate.convertAndSend(MQConfig.ORDER_EXCHANGE,MQConfig.ORDER,orderParamJson);
        return ServerResponse.success();

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createOrder(OrderParam orderParam) {
        Long memberId = orderParam.getMemberId();
        String cartJson = RedisUtil.get(KeyUtil.buildCartKey(memberId));
        Cart cart = JSONObject.parseObject(cartJson, Cart.class);
        List<CartItem> cartItemList = cart.getCartItemList();
        //减库存【数据库的乐观锁】
        //update t_product set stock=stock-num where id=id and stock >= num;
        //考虑到并发
        for (CartItem cartItem : cartItemList) {
            Long goodsId = cartItem.getGoodsId();
            int num = cartItem.getNum();
            int rowCount = productMapper.updateStock(goodsId,num);
            if(rowCount == 0){
                //没有更新成功，库存不足
                //既要起到回滚的作用，又要起到提示的作用
                throw new StockLessException("stock less");
            }
        }
        //获取对应的收件人信息
        Long recipientId = orderParam.getRecipientId();
        Recipient recipient = recipientMapper.selectById(recipientId);
        //插入订单表
        Order order = new Order();
        //手工设置id[通过雪花算法生成的唯一标识]
        String orderId = IdWorker.getIdStr();
        order.setId(orderId);
        order.setCreateTime(new Date());
        order.setRecipientor(recipient.getRecipientor());
        order.setPhone(recipient.getPhone());
        order.setAddress(recipient.getAddress());
        order.setUserId(memberId);
        order.setTotalPrice(cart.getTotalPrice());
        order.setRecipientId(recipientId);
        order.setPayType(orderParam.getPayType());
        order.setStatus(SystemConstant.OrderStatus.WAIT_PAY);//未支付
        order.setTotalNum(cart.getTotalNum());
        orderMapper.insert(order);
        //插入订单明细表
        for (CartItem cartItem : cartItemList) {
            OrderItem orderItem = new OrderItem();
            orderItem.setOrderId(orderId);
            orderItem.setProductId(cartItem.getGoodsId());
            orderItem.setPrice(cartItem.getPrice());
            orderItem.setProductName(cartItem.getGoodsName());
            orderItem.setUserId(memberId);
            orderItem.setImageUrl(cartItem.getImageUrl());
            orderItem.setNum(cartItem.getNum());
            orderItem.setSubPrice(cartItem.getSubPrice());
            orderItemMapper.insert(orderItem);
        }

    }
}

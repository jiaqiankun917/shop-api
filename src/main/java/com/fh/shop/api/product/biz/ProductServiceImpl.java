package com.fh.shop.api.product.biz;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fh.shop.api.common.ServerResponse;
import com.fh.shop.api.product.mapper.IProductMapper;
import com.fh.shop.api.product.po.Product;
import com.fh.shop.api.product.vo.ProductVo;
import com.fh.shop.api.util.RedisUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("productService")
public class ProductServiceImpl implements IProductService {

    @Value("${stock.less}")
    private Long stockCount;

    @Autowired
    private IProductMapper productMapper;

    @Override
    public ServerResponse findHotStatus() {

        String productHotStatus = RedisUtil.get("productHotStatus");
        if(StringUtils.isNotEmpty(productHotStatus)){
            //将json格式的字符串转换为java对象
            //[如果要转为Java中的集合则用parseArray]
            //[如果要转换为java中的对象则用parseObject]
            List<ProductVo> productVoList = JSONObject.parseArray(productHotStatus, ProductVo.class);
            return ServerResponse.success(productVoList);
        }

        QueryWrapper<Product> productQueryWrapper = new QueryWrapper<>();
        productQueryWrapper.select("id","productName","price","mainImagePath");
        productQueryWrapper.eq("isHot",1);
        productQueryWrapper.eq("status",1);
        List<Product> productList = productMapper.selectList(productQueryWrapper);
        List<ProductVo> productVoList = new ArrayList<>();
        for (Product product : productList) {
            ProductVo productVo = new ProductVo();
            productVo.setId(product.getId());
            productVo.setProductName(product.getProductName());
            productVo.setPrice(product.getPrice().toString());
            productVo.setMainImagePath(product.getMainImagePath());
            productVoList.add(productVo);
        }

        //往缓存中存一份
        //把java对象转换为json格式的字符串
        String productListJson =  JSONObject.toJSONString(productVoList);
        RedisUtil.setEx("productHotStatus",productListJson,2*60);

        return ServerResponse.success(productVoList);
    }

    @Override
    public List<Product> findStock() {
        QueryWrapper<Product> productQueryWrapper = new QueryWrapper<>();
        productQueryWrapper.lt("stock",stockCount);
        List<Product> productList = productMapper.selectList(productQueryWrapper);
        return productList;
    }


}

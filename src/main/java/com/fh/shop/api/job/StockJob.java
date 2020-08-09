package com.fh.shop.api.job;

import com.fh.shop.api.product.biz.IProductService;
import com.fh.shop.api.product.po.Product;
import com.fh.shop.api.util.MailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
public class StockJob {

    @Autowired
    private MailUtil mailUtil;

    @Resource(name = "productService")
    private IProductService  productService;

    @Scheduled(cron = "0 30 0 * * ?")
    public void stockJob(){
        //获取库存量不足的商品
        List<Product> productList = productService.findStock();
        //生成表格
        StringBuffer productHtml = new StringBuffer();
        productHtml.append("<table border=\"1\" bgcolor=\"aqua\" cellspacing=\"0\" width=\"500px\" cellpadding=\"0\">\n" +
                "    <thead>\n" +
                "        <tr>\n" +
                "            <th>汽车名称</th>\n" +
                "            <th>汽车价格</th>\n" +
                "            <th>剩余库存</th>\n" +
                "        </tr>\n" +
                "    </thead>\n" +
                "    <tbody>");
        //循环添加数据
        for (Product product : productList) {
            productHtml.append("<tr>\n" +
                    "            <td>"+product.getProductName()+"</td>\n" +
                    "            <td>"+product.getPrice().toString()+"万</td>\n" +
                    "            <td>"+product.getStock()+"辆</td>\n" +
                    "        </tr>");
        }
        //拼接结束标签
        productHtml.append(" </tbody>\n" +
                "</table>");
        //转换成string类型
        String tableHtml = productHtml.toString();
        //发送邮件
        mailUtil.sendMail("3060691383@qq.com","库存不足提示！",tableHtml);
    }
}

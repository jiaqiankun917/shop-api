package com.fh.shop.api.product.controller;

import com.fh.shop.api.common.ServerResponse;
import com.fh.shop.api.product.biz.IProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api/product")
@Api(tags = "商品接口")
public class ProductController {

    /*测试nginx时看到端口号的来回变化
    @Value("${server.port:}")
    private String port;

    @GetMapping("/test")
    public String test(){
        return port;
    }*/

    @Resource(name = "productService")
    private IProductService productService;



    //查询上架热销的商品
    @GetMapping
    @ApiOperation("获取所有上架并热销的商品")
    public ServerResponse findHotStatus(){
       return productService.findHotStatus();
    }




}

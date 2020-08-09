package com.fh.shop.api.brand.controller;

import com.fh.shop.api.annotation.Check;
import com.fh.shop.api.brand.biz.IBrandService;
import com.fh.shop.api.brand.po.Brand;
import com.fh.shop.api.common.ServerResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/brand")
@Api(tags = "品牌接口")
public class BrandController {

    @Autowired
    private IBrandService brandService;

    @GetMapping
    @ApiOperation("获取所有品牌列表")
    public ServerResponse queryBrand(){
        List<Brand> brandList = brandService.queryBrand();
        return ServerResponse.success(brandList);
    }
}

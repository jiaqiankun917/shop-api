package com.fh.shop.api.category.controller;


import com.fh.shop.api.category.biz.ICategoryService;
import com.fh.shop.api.category.po.Category;
import com.fh.shop.api.common.ServerResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/api/category")
@Api(tags = "商品分类接口")
public class CategoryController {

    @Resource(name = "categoryService")
    private ICategoryService categoryService;

    @GetMapping()
    @ApiOperation("查询所有商品分类列表")
    public ServerResponse queryCategoryList(){

        return   categoryService.queryCategory();

    }

}

package com.fh.shop.api.book.controller;

import com.fh.shop.api.book.biz.ITypeService;
import com.fh.shop.api.common.ServerResponse;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api/type")
@Api(tags = "图书类型接口")
public class TypeController {

    @Resource(name = "typeService")
    private ITypeService typeService;

    @GetMapping
    public ServerResponse findType(){
        return typeService.findType();
    }
}

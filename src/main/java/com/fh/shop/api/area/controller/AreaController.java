package com.fh.shop.api.area.controller;

import com.fh.shop.api.area.biz.IAreaService;
import com.fh.shop.api.common.ServerResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api/area")
@Api(tags = "地区接口")
public class AreaController {
    @Resource(name = "areaService")
    private IAreaService areaService;

    @GetMapping
    @ApiOperation("查询地区")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "地区ID",type = "long",required = true,paramType = "query")
    })
    public ServerResponse findArea(Long id){
        return areaService.findArea(id);
    }
}

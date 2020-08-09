package com.fh.shop.api.order.controller;

import com.fh.shop.api.annotation.Check;
import com.fh.shop.api.common.ServerResponse;
import com.fh.shop.api.common.SystemConstant;
import com.fh.shop.api.member.vo.MemberVo;
import com.fh.shop.api.order.biz.IOrderService;
import com.fh.shop.api.order.param.OrderParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/order")
@Api(tags = "订单接口")
public class OrderController {

    @Resource(name = "orderService")
    private IOrderService orderService;

    @GetMapping
    @Check
    @ApiOperation("生成确认订单页面")
    @ApiImplicitParams({
            @ApiImplicitParam(name="x-auth",value = "头信息",type = "string",required = true,paramType = "header")
    })
    public ServerResponse findList(HttpServletRequest request){
        MemberVo memberVo = (MemberVo) request.getAttribute(SystemConstant.CURR_MEMBER);
        Long memberId = memberVo.getId();
        return orderService.findList(memberId);
    }

    @GetMapping("/generateOrder")
    @Check
    @ApiOperation("生成订单")
    public ServerResponse generateOrder(HttpServletRequest request, OrderParam orderParam){
        MemberVo memberVo = (MemberVo) request.getAttribute(SystemConstant.CURR_MEMBER);
        Long memberId = memberVo.getId();
        orderParam.setMemberId(memberId);
        return orderService.generateOrder(orderParam);
    }



}

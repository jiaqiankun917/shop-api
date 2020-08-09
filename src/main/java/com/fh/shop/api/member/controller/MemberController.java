package com.fh.shop.api.member.controller;

import com.fh.shop.api.annotation.Check;
import com.fh.shop.api.common.ResponseEnum;
import com.fh.shop.api.common.ServerResponse;
import com.fh.shop.api.common.SystemConstant;
import com.fh.shop.api.member.biz.IMemberService;
import com.fh.shop.api.member.po.Member;
import com.fh.shop.api.member.vo.MemberVo;
import com.fh.shop.api.util.KeyUtil;
import com.fh.shop.api.util.RedisUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/member")
@Api(tags = "会员注册登录接口")
public class MemberController {

    @Resource(name = "memberService")
    private IMemberService memberService;

    @PostMapping
    @ApiOperation("会员注册接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name="memberName",value = "会员名",type = "string",required = true,paramType = "query"),
            @ApiImplicitParam(name="password",value = "密码",type = "string",required = true,paramType = "query"),
            @ApiImplicitParam(name="realName",value = "真实姓名",type = "string",required = true,paramType = "query"),
            @ApiImplicitParam(name="birthday",value = "出生日期",type = "string",required = true,paramType = "query"),
            @ApiImplicitParam(name="mail",value = "邮箱",type = "string",required = true,paramType = "query"),
            @ApiImplicitParam(name="phone",value = "手机号码",type = "string",required = true,paramType = "query"),
            @ApiImplicitParam(name="shengId",value = "省ID",type = "long",required = false,paramType = "query"),
            @ApiImplicitParam(name="shiId",value = "市ID",type = "long",required = false,paramType = "query"),
            @ApiImplicitParam(name="xianId",value = "县ID",type = "long",required = false,paramType = "query"),
            @ApiImplicitParam(name="areaName",value = "地区名",type = "string",required = false,paramType = "query")
    })
    public ServerResponse addMember(Member member){
        return memberService.addMember(member);
    }

    @GetMapping("validateMemberName")
    @ApiOperation("判断会员名的唯一性")
    @ApiImplicitParams({
            @ApiImplicitParam(name="memberName",value = "会员名",type = "string",required = true,paramType = "query"),
    })
    public ServerResponse validateMemberName(String memberName){
        return memberService.validateMemberName(memberName);
    }

    @GetMapping("validateMail")
    @ApiOperation("判断邮箱的唯一性")
    @ApiImplicitParams({
            @ApiImplicitParam(name="mail",value = "会员名",type = "string",required = true,paramType = "query"),
    })
    public ServerResponse validateMail(String mail){
        return memberService.validateMail(mail);
    }

    @GetMapping("validatePhone")
    @ApiOperation("判断手机号码的唯一性")
    @ApiImplicitParams({
            @ApiImplicitParam(name="phone",value = "会员名",type = "string",required = true,paramType = "query"),
    })
    public ServerResponse validatePhone(String phone){
        return memberService.validatePhone(phone);
    }


    //登录
    @PostMapping("/login")
    @ApiOperation("会员登录接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name="memberName",value = "会员名",type = "string",required = true,paramType = "query"),
            @ApiImplicitParam(name="password",value = "密码",type = "string",required = true,paramType = "query")
    })
    public ServerResponse login(String memberName , String password){
        return memberService.login(memberName,password);
    }

    @GetMapping("/findMember")
    @Check
    @ApiOperation("获取会员信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name="x-auth",value = "头信息",type = "string",required = true,paramType = "header"),
    })
    public ServerResponse findMember(HttpServletRequest request){
       MemberVo memberVo = (MemberVo) request.getAttribute(SystemConstant.CURR_MEMBER);
        return ServerResponse.success(memberVo);
    }

    @GetMapping("/logout")
    @Check
    @ApiOperation("退出登录")
    public ServerResponse logout(HttpServletRequest request){
        MemberVo memberVo = (MemberVo) request.getAttribute(SystemConstant.CURR_MEMBER);
        Long id = memberVo.getId();
        String uuid = memberVo.getUuid();
        //清除redis中的数据
        RedisUtil.delete(KeyUtil.buildMemberKey(uuid,id));
        return ServerResponse.success();
    }
}

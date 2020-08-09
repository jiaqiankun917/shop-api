package com.fh.shop.api.member.biz;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fh.shop.api.common.ResponseEnum;
import com.fh.shop.api.common.ServerResponse;
import com.fh.shop.api.member.mapper.IMemberMapper;
import com.fh.shop.api.member.po.Member;
import com.fh.shop.api.member.vo.MemberVo;
import com.fh.shop.api.mq.MQSender;
import com.fh.shop.api.mq.MailMessage;
import com.fh.shop.api.util.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;

@Service("memberService")
public class IMemberServiceImpl implements IMemberService {

    @Autowired
    private IMemberMapper memberMapper;

    @Autowired
    private MailUtil mailUtil;

    @Autowired
    private MQSender mqSender;

    @Override
    public ServerResponse addMember(Member member) {
        //验证逻辑
        if(StringUtils.isEmpty(member.getMemberName()) || StringUtils.isEmpty(member.getMail())
                                                       || StringUtils.isEmpty(member.getPhone())
                                                       || StringUtils.isEmpty(member.getRealName())
                                                       || StringUtils.isEmpty(member.getBirthday().toString())
                                                       || StringUtils.isEmpty(member.getPassword())){
            return ServerResponse.error(ResponseEnum.REG_MEMBER_IS_NULL);
        }

        //判断会员是否存在
        QueryWrapper<Member> objectQueryWrapper = new QueryWrapper<>();
        objectQueryWrapper.eq("memberName",member.getMemberName());
        Member member1 = memberMapper.selectOne(objectQueryWrapper);
        if(member1!=null){
           return ServerResponse.error(ResponseEnum.REG_MEMBER_IS_EXIST);
        }
        //判断邮件是否存在
        QueryWrapper<Member> objectQueryWrapper1 = new QueryWrapper<>();
        objectQueryWrapper1.eq("mail",member.getMail());
        Member member2 = memberMapper.selectOne(objectQueryWrapper1);
        if(member2!=null){
            return ServerResponse.error(ResponseEnum.REG_MEMBER_IS_MAIL);
        }
        //判断手机号是否存在
        QueryWrapper<Member> objectQueryWrapper2 = new QueryWrapper<>();
        objectQueryWrapper2.eq("phone",member.getPhone());
        Member member3 = memberMapper.selectOne(objectQueryWrapper2);
        if (member3!=null){
            return ServerResponse.error(ResponseEnum.REG_MEMBER_IS_PHONE);
        }

        memberMapper.addMember(member);
        //注册成功发送邮件
        mailUtil.sendMail(member.getMail(),"飞狐会员！","恭喜您，"+member.getRealName()+"注册会员成功！");
        return ServerResponse.success();
    }

    @Override
    public ServerResponse validateMemberName(String memberName) {
        if(StringUtils.isEmpty(memberName)){
            return ServerResponse.error(ResponseEnum.REG_MEMBER_IS_NULL);
        }
        QueryWrapper<Member> memberNameQueryWrapper = new QueryWrapper<>();
        memberNameQueryWrapper.eq("memberName",memberName);
        Member member = memberMapper.selectOne(memberNameQueryWrapper);
        if(member!=null){
            return ServerResponse.error(ResponseEnum.REG_MEMBER_IS_EXIST);
        }
        return ServerResponse.success();
    }

    @Override
    public ServerResponse validateMail(String mail) {
        if(StringUtils.isEmpty(mail)){
            return ServerResponse.error(ResponseEnum.REG_MEMBER_IS_NULL);
        }
        QueryWrapper<Member> memberNameQueryWrapper = new QueryWrapper<>();
        memberNameQueryWrapper.eq("mail",mail);
        Member member = memberMapper.selectOne(memberNameQueryWrapper);
        if(member!=null){
            return ServerResponse.error(ResponseEnum.REG_MEMBER_IS_MAIL);
        }
        return ServerResponse.success();
    }

    @Override
    public ServerResponse validatePhone(String phone) {
        if (StringUtils.isEmpty(phone)) {
            return ServerResponse.error(ResponseEnum.REG_MEMBER_IS_NULL);
        }
        QueryWrapper<Member> memberNameQueryWrapper = new QueryWrapper<>();
        memberNameQueryWrapper.eq("phone", phone);
        Member member = memberMapper.selectOne(memberNameQueryWrapper);
        if (member != null) {
            return ServerResponse.error(ResponseEnum.REG_MEMBER_IS_PHONE);
        }
        return ServerResponse.success();
    }

    @Override
    public ServerResponse login(String memberName , String password) {

        //判断会员名密码是否为空
        if(StringUtils.isEmpty(memberName) || StringUtils.isEmpty(password)){
            return ServerResponse.error(ResponseEnum.LOGIN_MEMBERNAME_PASSWORD_IS_NOT);
        }

        //判断用户名是否正确
        QueryWrapper<Member> memberQueryWrapper = new QueryWrapper<>();
        memberQueryWrapper.eq("memberName",memberName);
        Member member = memberMapper.selectOne(memberQueryWrapper);
        if(member==null){
            return ServerResponse.error(ResponseEnum.LOGIN_MEMBERNAME_IS_ERROR);
        }

        if(!password.equals(member.getPassword())){
            return ServerResponse.error(ResponseEnum.LOGIN_PASSWORD_IS_ERROR);
        }
        //=========生成token===============
        //模拟JWT[Json Web Token]
        //生成token样子类似于xxx.yyy 用户信息.对用户信息的签名
        //签名的目的：保证用户信息不被篡改
        //怎么生成签名：md5(用户信息结合秘钥)
        //sign代表签名：secret/secretKey代表秘钥
        //秘钥是在服务端保存的，黑客，攻击者它们获取不到
        //=========================================

        //生成用户信息对应的json
        MemberVo memberVo = new MemberVo();
        memberVo.setId(member.getId());
        memberVo.setMemberName(member.getMemberName());
        memberVo.setPassword(member.getPassword());
        memberVo.setRealName(member.getRealName());
        String uuid = UUID.randomUUID().toString();
        memberVo.setUuid(uuid);
        //转换java对象到json
        String memberJson = JSONObject.toJSONString(memberVo);
        //对用户信息进行base64编码
        //[起到一定的安全作用]
        //(对于计算机小白来说，吓唬他一下，但是对于计算机专业人士来说，起不到作用，可以直接将base64解码)
        //jdk1.8内部直接提供了base64的工具类，如果jdk的版本低于1.8就需要使用第三方来完成base64编码
        try {
            String memberJsonBase64 = Base64.getEncoder().encodeToString(memberJson.getBytes("utf-8"));
            //生成用户信息所对应的签名
            String sign = MD5.sign(memberJsonBase64, MD5.SECRET);
            //对签名也进行base64编码
            String signBase64 = Base64.getEncoder().encodeToString(sign.getBytes("utf-8"));
            //处理超时
            RedisUtil.setEx(KeyUtil.buildMemberKey(uuid,member.getId()),"",KeyUtil.MEMBER_KEY_EXPIRE);
            //发送邮件(同步)
            //mailUtil.sendMail(member.getMail(),"登陆成功！",member.getRealName()+"在"+ DateUtil.date2str(new Date(),DateUtil.FULL_TIME)+"登录了！");
            //异步发送邮件(消息中间件)
            MailMessage mailMessage = new MailMessage();
            mailMessage.setMail(member.getMail());
            mailMessage.setTitle("登录信息提醒！");
            mailMessage.setRealName(member.getRealName());
            mailMessage.setContent(member.getRealName()+"在"+ DateUtil.date2str(new Date(),DateUtil.FULL_TIME)+"登录了！");
            mqSender.sendMailMessage(mailMessage);
            //响应数据给客户端
            return ServerResponse.success(memberJsonBase64+"."+signBase64);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return ServerResponse.error();
        }

    }
}

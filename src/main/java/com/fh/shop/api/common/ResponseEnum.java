package com.fh.shop.api.common;

public enum ResponseEnum {


    CART_PRODUCT_IS_NULL(3001,"添加的商品不存在！"),
    CART_PRODUCT_IS_DOWN(3002,"商品下架了！"),
    CART_NUM_IS_NULL(3002,"商品数量异常！"),
    CART_BATCH_DELETE_IDS_IS_NULL(3003,"批量删除时,ids必须传递！"),

    LOGIN_MEMBERNAME_PASSWORD_IS_NOT(2001,"会员名或密码为空！"),
    LOGIN_MEMBERNAME_IS_ERROR(2002,"会员名错误！"),
    LOGIN_PASSWORD_IS_ERROR(2003,"密码错误！"),
    LOGIN_HEADER_LOSE(2004,"头信息丢失！"),
    LOGIN_HEADER_BROKEN(2005,"头信息不完整！"),
    LOGIN_MEMBER_TAMPER(2005,"会员信息被篡改！"),
    LOGIN_TIME_OUT(2006,"登录超时！"),

   REG_MEMBER_IS_PHONE(1003,"手机号已存在！"),
   REG_MEMBER_IS_MAIL(1002,"邮箱名已存在！"),
   REG_MEMBER_IS_EXIST(1001,"会员名已存在！"),
   REG_MEMBER_IS_NULL(1000,"任何一项都不得为空！");

    private int code;
    private String msg;

    private ResponseEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}

package com.fh.shop.api.common;

public class SystemConstant {

    public static final String CURR_MEMBER = "user";

    public static final int PRODUCT_STATUS_DOWN = 0;


    public interface OrderStatus{
        //接口中的变量  默认情况下会自动加上 public static final
        int WAIT_PAY = 10;//未支付
        int PAY_SUCCESS = 20;//支付成功
        int SEND_GOODS = 30;//已发货
    }

}

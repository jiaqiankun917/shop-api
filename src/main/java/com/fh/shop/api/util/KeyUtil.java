package com.fh.shop.api.util;

public class KeyUtil {

    public static final int MEMBER_KEY_EXPIRE = 1*60;

    public static String buildMemberKey(String uuid,Long memberId){
        return "member:"+uuid+":"+memberId;
    }


    public static String buildCartKey(Long memberId) {
        return "cart:"+memberId;
    }

    public static String buildStockLessKey(Long memberId) {
        return "order:stock:less:"+memberId;
    }
}

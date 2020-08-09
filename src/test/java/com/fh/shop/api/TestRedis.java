package com.fh.shop.api;

import com.fh.shop.api.util.RedisUtil;
import org.junit.jupiter.api.Test;

public class TestRedis {

    @Test
    public void getRedis(){
        /*RedisUtil.set("ppname","xiaolizi");
        String ppname = RedisUtil.get("ppname");
        System.out.println(ppname);*/

        RedisUtil.delete("ppname");
    }
}

package com.fh.shop.api.cart.po;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fh.shop.api.util.BigDecimalJackson;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
@Data
public class Cart implements Serializable {

    @JsonSerialize(using = BigDecimalJackson.class)
    private BigDecimal totalPrice;//总价格

    private int totalNum;//总个数

    private List<CartItem> cartItemList = new ArrayList<>();//初始化(一个购物车有多个商品)


    @JsonSerialize(using = BigDecimalJackson.class)
    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(int totalNum) {
        this.totalNum = totalNum;
    }

    public List<CartItem> getCartItemList() {
        return cartItemList;
    }

    public void setCartItemList(List<CartItem> cartItemList) {
        this.cartItemList = cartItemList;
    }
}

package com.fh.shop.api.order.vo;

import com.fh.shop.api.cart.po.Cart;
import com.fh.shop.api.recipient.po.Recipient;

import java.util.ArrayList;
import java.util.List;

public class OrderConfirmVo {

    private List<Recipient> recipientList = new ArrayList<>();

    private Cart cart;

    public List<Recipient> getRecipientList() {
        return recipientList;
    }

    public void setRecipientList(List<Recipient> recipientList) {
        this.recipientList = recipientList;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }
}

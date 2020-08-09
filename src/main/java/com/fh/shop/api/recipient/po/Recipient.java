package com.fh.shop.api.recipient.po;

import lombok.Data;

import java.io.Serializable;

@Data
public class Recipient implements Serializable {

    private Long id ;

    private String recipientor;

    private String address;

    private String phone;

    private String mail;

    private Long memberId;

    private int status;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRecipientor() {
        return recipientor;
    }

    public void setRecipientor(String recipientor) {
        this.recipientor = recipientor;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}

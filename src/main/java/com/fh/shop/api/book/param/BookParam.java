package com.fh.shop.api.book.param;

import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

public class BookParam extends Page {

    private String bookName;

    private BigDecimal minPrice;

    private BigDecimal maxPrice;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date minCreateTime;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date maxCreateTime;

    private Integer typeId;

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public BigDecimal getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(BigDecimal minPrice) {
        this.minPrice = minPrice;
    }

    public BigDecimal getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(BigDecimal maxPrice) {
        this.maxPrice = maxPrice;
    }

    public Date getMinCreateTime() {
        return minCreateTime;
    }

    public void setMinCreateTime(Date minCreateTime) {
        this.minCreateTime = minCreateTime;
    }

    public Date getMaxCreateTime() {
        return maxCreateTime;
    }

    public void setMaxCreateTime(Date maxCreateTime) {
        this.maxCreateTime = maxCreateTime;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }
}

package com.fh.shop.api.product.po;

import com.baomidou.mybatisplus.annotation.TableField;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

public class Product {

    private Long id;
    private String productName;
    private BigDecimal price;
    private Long brandId;
    @TableField(exist=false)
    private String brandName;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date createDate;
    private Date inputTime;
    private Date updateTime;
    private String mainImagePath;
    @TableField(exist = false)//MP,就是MP和mybatis结合使用的时候，生成sql语句的时候没能再数据库找到相关的字段，所以就通过这个注解给关闭
    private String oldMainImagePath;//老的图片路径
    private Integer isHot;
    private Integer status;
    private Long stock;//库存

    public Long getStock() {
        return stock;
    }

    public void setStock(Long stock) {
        this.stock = stock;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getIsHot() {
        return isHot;
    }

    public void setIsHot(Integer isHot) {
        this.isHot = isHot;
    }

    public String getOldMainImagePath() {
        return oldMainImagePath;
    }

    public void setOldMainImagePath(String oldMainImagePath) {
        this.oldMainImagePath = oldMainImagePath;
    }

    public String getMainImagePath() {
        return mainImagePath;
    }

    public void setMainImagePath(String mainImagePath) {
        this.mainImagePath = mainImagePath;
    }

    public Date getInputTime() {
        return inputTime;
    }

    public void setInputTime(Date inputTime) {
        this.inputTime = inputTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public Long getBrandId() {
        return brandId;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Product(){

    }

    public Product(String productName) {

        this.productName = productName;

    }
}

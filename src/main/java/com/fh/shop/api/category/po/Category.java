package com.fh.shop.api.category.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("t_category")
public class Category implements Serializable {

    @TableId(value = "id" , type = IdType.AUTO)
    private Long id;

    private String categoryName;

    private Long pid;

    private Integer type;

}

package com.fh.shop.api.brand.mapper;


import com.fh.shop.api.brand.po.Brand;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface IBrandMapper {

    @Select("select id,name from t_brand")
    List<Brand> queryBrand();
}

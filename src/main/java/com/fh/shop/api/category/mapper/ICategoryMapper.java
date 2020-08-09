package com.fh.shop.api.category.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fh.shop.api.category.po.Category;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ICategoryMapper extends BaseMapper<Category> {

    /*@Select("select * from t_category")
    List<Category> queryCategory();*/
}

package com.fh.shop.api.book.mapper;

import com.fh.shop.api.book.po.Type;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TypeMapper{

    @Select("select id,name from t_booktype")
    List<Type> findType();
}

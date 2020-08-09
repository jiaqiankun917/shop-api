package com.fh.shop.api.book.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fh.shop.api.book.param.BookParam;
import com.fh.shop.api.book.po.Book;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookMapper extends BaseMapper<Book> {
    Long findCount(BookParam bookParam);

    List<Book> findPageList(BookParam bookParam);
}

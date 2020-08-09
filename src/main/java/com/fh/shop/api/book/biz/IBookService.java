package com.fh.shop.api.book.biz;

import com.fh.shop.api.book.param.BookParam;
import com.fh.shop.api.book.po.Book;
import com.fh.shop.api.common.DataTableResult;
import com.fh.shop.api.common.ServerResponse;

public interface IBookService {
    DataTableResult findList(BookParam bookParam);

    ServerResponse addBook(Book book);

    ServerResponse deleteBook(Long id);

    ServerResponse queryBookById(Long id);

    ServerResponse updateBook(Book book);
}

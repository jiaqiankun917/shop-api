package com.fh.shop.api.book.biz;

import com.fh.shop.api.book.mapper.BookMapper;
import com.fh.shop.api.book.param.BookParam;
import com.fh.shop.api.book.po.Book;
import com.fh.shop.api.common.DataTableResult;
import com.fh.shop.api.common.ServerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("bookService")
public class BookServiceImpl implements IBookService {

    @Autowired
    private BookMapper bookMapper;

    @Override
    public DataTableResult findList(BookParam bookParam) {
        Long totalCount =  bookMapper.findCount(bookParam);
        List<Book> bookList = bookMapper.findPageList(bookParam);

        return new DataTableResult(bookParam.getDraw(),totalCount,totalCount,bookList);
    }

    @Override
    public ServerResponse addBook(Book book) {

        bookMapper.insert(book);

        return ServerResponse.success();
    }

    @Override
    public ServerResponse deleteBook(Long id) {

        bookMapper.deleteById(id);

        return ServerResponse.success();
    }

    @Override
    public ServerResponse queryBookById(Long id) {

        Book book = bookMapper.selectById(id);

        return ServerResponse.success(book);
    }

    @Override
    public ServerResponse updateBook(Book book) {

        bookMapper.updateById(book);

        return ServerResponse.success();
    }
}

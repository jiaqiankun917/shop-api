package com.fh.shop.api.book.controller;

import com.fh.shop.api.book.biz.IBookService;
import com.fh.shop.api.book.param.BookParam;
import com.fh.shop.api.book.po.Book;
import com.fh.shop.api.common.DataTableResult;
import com.fh.shop.api.common.ServerResponse;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api/book")
@Api(tags="图书接口")
public class BookController {

    @Resource(name = "bookService")
    private IBookService bookService;

    @GetMapping("/queryBookList")
    public DataTableResult findList(BookParam bookParam){
        return bookService.findList(bookParam);
    }

    @PostMapping
    public ServerResponse addBook(Book book){

        return bookService.addBook(book);
    }

    @DeleteMapping
    public ServerResponse deleteBook(Long id){

        return bookService.deleteBook(id);
    }

    @GetMapping("/queryBookById")
    public ServerResponse queryBookById(Long id){

        return bookService.queryBookById(id);
    }

    @PutMapping
    public ServerResponse updateBook(Book book){

        return bookService.updateBook(book);
    }
}

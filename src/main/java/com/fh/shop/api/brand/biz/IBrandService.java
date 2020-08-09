package com.fh.shop.api.brand.biz;

import com.fh.shop.api.book.param.BookParam;
import com.fh.shop.api.brand.po.Brand;
import com.fh.shop.api.common.ServerResponse;

import java.util.List;

public interface IBrandService {
    List<Brand> queryBrand();

}

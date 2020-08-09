package com.fh.shop.api.brand.biz;

import com.fh.shop.api.brand.mapper.IBrandMapper;
import com.fh.shop.api.brand.po.Brand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandServiceImpl implements IBrandService{
    @Autowired
    private IBrandMapper brandMapper;

    @Override
    public List<Brand> queryBrand() {
        return brandMapper.queryBrand();
    }
}

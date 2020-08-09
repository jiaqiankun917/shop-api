package com.fh.shop.api.book.biz;

import com.fh.shop.api.book.mapper.TypeMapper;
import com.fh.shop.api.book.po.Type;
import com.fh.shop.api.common.ServerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("typeService")
public class TypeServiceImpl implements ITypeService{

    @Autowired
    private TypeMapper typeMapper;

    @Override
    public ServerResponse findType() {
        List<Type> typeList =  typeMapper.findType();

        return ServerResponse.success(typeList);
    }
}

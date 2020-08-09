package com.fh.shop.api.category.biz;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fh.shop.api.category.mapper.ICategoryMapper;
import com.fh.shop.api.category.po.Category;
import com.fh.shop.api.common.ServerResponse;
import com.fh.shop.api.util.RedisUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("categoryService")
public class ICategoryServiceImpl implements ICategoryService{

    @Autowired
    private ICategoryMapper categoryMapper;


    @Override
    public ServerResponse queryCategory() {
        String categoryCache = RedisUtil.get("category");
        if(StringUtils.isNotEmpty(categoryCache)){
            List<Category> categories = JSONArray.parseArray(categoryCache, Category.class);
            return ServerResponse.success(categories);
        }

        List<Category> categoryList = categoryMapper.selectList(null);
        String categoryArrJson = JSONObject.toJSONString(categoryList);
        RedisUtil.setEx("category",categoryArrJson,2*60);

        return ServerResponse.success(categoryList);
    }
}

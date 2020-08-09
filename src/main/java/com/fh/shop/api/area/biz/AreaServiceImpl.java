package com.fh.shop.api.area.biz;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fh.shop.api.area.mapper.IAreaMapper;
import com.fh.shop.api.area.po.Area;
import com.fh.shop.api.common.ServerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("areaService")
public class AreaServiceImpl implements IAreaService {

    @Autowired
    private IAreaMapper areaMapper;

    @Override
    public ServerResponse findArea(Long id) {
        QueryWrapper<Area> objectQueryWrapper = new QueryWrapper<>();
        objectQueryWrapper.eq("pid",id);
        List<Area> areaList = areaMapper.selectList(objectQueryWrapper);
        return ServerResponse.success(areaList);
    }
}

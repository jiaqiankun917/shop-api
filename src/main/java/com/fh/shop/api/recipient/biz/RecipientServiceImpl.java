package com.fh.shop.api.recipient.biz;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fh.shop.api.common.ServerResponse;
import com.fh.shop.api.recipient.mapper.RecipientMapper;
import com.fh.shop.api.recipient.po.Recipient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("recipientService")
public class RecipientServiceImpl implements IRecipientService {

    @Autowired
    private RecipientMapper recipientMapper;


    @Override
    public List<Recipient> findList(Long memberId) {
        QueryWrapper<Recipient> wrapper = new QueryWrapper<>();
        wrapper.eq("memberId", memberId);
        List<Recipient> recipients = recipientMapper.selectList(wrapper);
        return recipients;
    }
}

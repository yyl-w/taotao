package com.taotao.content.service.impl;

import com.github.pagehelper.StringUtil;
import com.taotao.content.jedis.JedisClient;
import com.taotao.content.service.ContentService;
import com.taotao.mapper.TbContentMapper;
import com.taotao.pojo.TbContent;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pojo.EasyUIResult;
import pojo.TaotaoResult;
import utils.JsonUtils;

import java.util.Date;
import java.util.List;

@Service
public class ContentServiceImpl implements ContentService {
    @Autowired
    private TbContentMapper tbContentMapper;
    @Autowired
    private JedisClient jedisClient;

    @Value("${CONTENT_KEY}")
    private String CONTENT_KEY;
    @Override
    public EasyUIResult findContentAll(Long contentCategoryId) {
        List<TbContent> tbContents = tbContentMapper.findContentCategory(contentCategoryId);
        EasyUIResult result = new EasyUIResult();
        result.setTotal(tbContents.size());
        result.setRows(tbContents);
        return result;
    }

    @Override
    public TaotaoResult addContent(TbContent tbContent) {
        Date date = new Date();
        tbContent.setCreated(date);
        tbContent.setUpdated(date);
        tbContentMapper.addContent(tbContent);
        return TaotaoResult.ok(tbContent);
    }

    @Override
    public List<TbContent> getContentAll(Long contentCategoryId) {
        String josn = jedisClient.get(CONTENT_KEY);
        if(StringUtils.isNoneBlank(josn)){
            List<TbContent> contents = JsonUtils.jsonToList(josn,TbContent.class);
            return contents;
        }
        List<TbContent> contents = tbContentMapper.findContentCategory(contentCategoryId);
        return contents;
    }

    @Override
    public TaotaoResult deleteContent(Long tbContentId) {
       int i = tbContentMapper.deleteContent(tbContentId);
       if(i != 0){
           return TaotaoResult.ok();
       }
       return null;
    }
}

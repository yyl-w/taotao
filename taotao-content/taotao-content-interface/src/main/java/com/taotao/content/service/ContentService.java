package com.taotao.content.service;

import com.taotao.pojo.TbContent;
import pojo.EasyUIResult;
import pojo.TaotaoResult;

import java.util.List;

public interface ContentService {
    EasyUIResult findContentAll(Long contentCategoryId);
    TaotaoResult addContent(TbContent tbContent);
    List<TbContent> getContentAll(Long contentCategoryId);
    TaotaoResult deleteContent(Long tbContentId);
}

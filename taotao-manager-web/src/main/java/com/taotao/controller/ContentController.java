package com.taotao.controller;

import com.taotao.content.service.ContentService;
import com.taotao.pojo.TbContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pojo.EasyUIResult;
import pojo.TaotaoResult;

@Controller
@RequestMapping("/content")
public class ContentController {
    @Autowired
    private ContentService contentService;

    @RequestMapping("/query/list")
    @ResponseBody
    public EasyUIResult findAllContentCategory(long categoryId){
       EasyUIResult result = contentService.findContentAll(categoryId);
       return result;
    }
    @RequestMapping("/save")
    @ResponseBody
    public TaotaoResult addContent(TbContent tbContent) {
        TaotaoResult result = contentService.addContent(tbContent);
        return result;
    }
    @RequestMapping("/delete")
    @ResponseBody
    public TaotaoResult deleteTbContent(Long tbContentId){
        TaotaoResult result = contentService.deleteContent(tbContentId);
        return result;
    }
}

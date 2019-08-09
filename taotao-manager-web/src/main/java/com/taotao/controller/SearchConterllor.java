package com.taotao.controller;

import com.taotao.search.service.SearchItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pojo.TaotaoResult;

@Controller
public class SearchConterllor {

    @Autowired
    private SearchItemService service;

    @RequestMapping("/index/importall")
    @ResponseBody
    public TaotaoResult initSearchItems(){
        TaotaoResult result = service.importAllItems();
        return result;
    }
}

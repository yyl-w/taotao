package com.taotao.controller;

import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;
import com.taotao.service.ItemService;
import pojo.EasyUIResult;
import pojo.TaotaoResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ItemController {

    @Autowired
    private ItemService itemService;

    @RequestMapping("/item/list")
    @ResponseBody
    public EasyUIResult getItemList(Integer page, Integer rows){
        EasyUIResult result = itemService.findItem(page, rows);
        return result;
    }

    @RequestMapping("/rest/item/delete")
    @ResponseBody
    public TaotaoResult delItem(Long[] ids){
        TaotaoResult result= itemService.delItems(ids);
        return result;
    }
    @RequestMapping("/rest/item/reshelf")
    @ResponseBody
    public TaotaoResult updateUpItem(Long[] ids){
        TaotaoResult result = itemService.updateUpItem(ids);
        return result;
    }
    @RequestMapping("/rest/item/instock")
    @ResponseBody
    public TaotaoResult updateDownItem(Long[] ids){
        TaotaoResult result = itemService.updateDownItem(ids);
        return result;
    }

    @RequestMapping("/item/save")
    @ResponseBody
    public TaotaoResult addItems(TbItem item, String desc){
        TaotaoResult result = itemService.addItems(item,desc);
        return result;
    }
}



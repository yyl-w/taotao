package com.taotao.service;

import com.taotao.pojo.TbItem;
import pojo.EasyUIResult;
import pojo.TaotaoResult;

public interface ItemService {

    TbItem findItemById(Long itemId);

    EasyUIResult findItem(Integer page, Integer rows);

    TaotaoResult delItems(Long[] ids);
    TaotaoResult updateUpItem(Long[] ids);
    TaotaoResult updateDownItem(Long[] ids);
    TaotaoResult addItems(TbItem tbItem,String desc);
}

package com.taotao.mapper;

import org.apache.ibatis.annotations.Select;
import pojo.SearchItem;

import java.util.List;

public interface SearchItemMapper {
    @Select("select a.id,a.title,a.sellpoint,a.price,a.image,b.NAME categoryname,c.itemdesc from tbitem a JOIN tbitemcat b ON a.cid = b.id JOIN tbitemdesc c ON a.id = c.itemid")
    List<SearchItem> getItemList();
    @Select("select a.id,a.title,a.sellpoint,a.price,a.image,b.NAME categoryname,c.itemdesc from tbitem a JOIN tbitemcat b ON a.cid = b.id JOIN tbitemdesc c ON a.id = c.itemid where a.id=#{itemId}")
    SearchItem getItemById(Long itemId);
}

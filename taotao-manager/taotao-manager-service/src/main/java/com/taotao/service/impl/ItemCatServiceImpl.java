package com.taotao.service.impl;

import com.taotao.mapper.ItemCatMapper;
import com.taotao.pojo.TbItemCat;
import com.taotao.service.ItemCatService;
import pojo.EasyUITreeNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class ItemCatServiceImpl implements ItemCatService {
    @Autowired
    private ItemCatMapper itemCatMapper;
    @Override
    public List<EasyUITreeNode> getCatList(Long id) {
        List<TbItemCat> itemCats = itemCatMapper.findItemCatByParentId(id);
        List<EasyUITreeNode> result = new ArrayList<>();
        for(TbItemCat tbItemCat:itemCats){
            EasyUITreeNode node = new EasyUITreeNode();
            node.setId(tbItemCat.getId());
            node.setText(tbItemCat.getName());
            node.setState(tbItemCat.getIsParent()?"closed":"open");
            result.add(node);
        }
        return result;
    }
}

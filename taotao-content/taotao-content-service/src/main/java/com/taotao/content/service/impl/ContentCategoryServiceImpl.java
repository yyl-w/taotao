package com.taotao.content.service.impl;

import com.taotao.content.service.ContentCategoryService;
import com.taotao.content.service.ContentService;
import com.taotao.mapper.TbContentCategoryMapper;
import com.taotao.pojo.TbContentCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pojo.EasyUITreeNode;
import pojo.TaotaoResult;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Service
public class ContentCategoryServiceImpl implements ContentCategoryService {
   @Autowired
   private TbContentCategoryMapper tbContentCategoryMapper;
    @Override
    public List<EasyUITreeNode> getContentCtaegoryList(Long parentId) {
        List<TbContentCategory> contentCategories = tbContentCategoryMapper.findContentCategoryByParentId(parentId);
        List<EasyUITreeNode> nodes = new ArrayList<>();
        for (TbContentCategory category:contentCategories){
            EasyUITreeNode node = new EasyUITreeNode();
            node.setId(category.getId());
            node.setText(category.getName());
            node.setState(category.getIsParent()?"closed":"open");
            nodes.add(node);
        }
        return nodes;
    }

    @Override
    public TaotaoResult addContentCategory(Long parentId, String name) {
        TbContentCategory contentCategory = new TbContentCategory();
        Date date = new Date();
        //默认没有父节点
        contentCategory.setIsParent(false);
        //需要添加借点的名称
        contentCategory.setName(name);
        //关联父节点
        contentCategory.setParentId(parentId);
        contentCategory.setSortOrder(1);
        contentCategory.setStatus(1);
        contentCategory.setCreated(date);
        contentCategory.setUpdated(date);
        tbContentCategoryMapper.addContentCategory(contentCategory);
        TbContentCategory category = tbContentCategoryMapper.findContentCategoryById(parentId);
        if(!category.getIsParent()){
            category.setIsParent(true);
            tbContentCategoryMapper.updateContentCategory(category);
        }
        return TaotaoResult.ok(contentCategory);
    }

    @Override
    public void deleteContentCategory(Long id) {
        List<TbContentCategory> categories = tbContentCategoryMapper.findContentCategoryByParentId(id);
        if(categories.size()>0){
            for (TbContentCategory category : categories) {
                deleteContentCategory(category.getId());
            }
        }else{
            tbContentCategoryMapper.deleteContentCategoryById(id);
        }
    }

    @Override
    public void updateCategoryStatus(Long id) {
       TbContentCategory category = tbContentCategoryMapper.findContentCategoryById(id);
        category.setIsParent(false);
        category.setId(category.getId());
        tbContentCategoryMapper.updateContentCategory(category);
    }

    @Override
    public void updateContentCategoryName(Long id, String name) {
        tbContentCategoryMapper.updateContentCategoryName(id,name);
    }
}

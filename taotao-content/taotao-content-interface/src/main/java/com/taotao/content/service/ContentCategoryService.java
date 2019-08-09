package com.taotao.content.service;

import com.taotao.pojo.TbContentCategory;
import pojo.EasyUITreeNode;
import pojo.TaotaoResult;

import java.util.List;

public interface ContentCategoryService {
    List<EasyUITreeNode> getContentCtaegoryList(Long parentId);
    TaotaoResult addContentCategory(Long parentId,String name);
    void deleteContentCategory(Long id);
    void updateCategoryStatus(Long id);
    void updateContentCategoryName(Long id,String name);
}

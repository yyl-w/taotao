package com.taotao.controller;

import com.taotao.content.service.ContentCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import pojo.EasyUITreeNode;
import pojo.TaotaoResult;

import java.util.List;

@Controller
@RequestMapping("/content/category")
public class ContentCategoryController {
    @Autowired
    private ContentCategoryService categoryService;
    @RequestMapping("/list")
    @ResponseBody
    public List<EasyUITreeNode> showContentCategory(@RequestParam(value = "id",defaultValue = "0")Long parentId){
        List<EasyUITreeNode> result = categoryService.getContentCtaegoryList(parentId);
        return result;
    }
    @RequestMapping("/create")
    @ResponseBody
    public TaotaoResult createContentCategory(Long parentId,String name){
        TaotaoResult result = categoryService.addContentCategory(parentId,name);
        return result;
    }
    @RequestMapping("/delete")
    @ResponseBody
    public String deleteContentCategory(Long id){
        categoryService.deleteContentCategory(id);
        categoryService.updateCategoryStatus(id);
        return "forward:/content/category/list?id=0";
    }
    @RequestMapping("/update")
    @ResponseBody
    public void updateContentCategoryName(Long id,String name){
        categoryService.updateContentCategoryName(id,name);
    }
}

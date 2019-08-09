package com.taotao.mapper;

import com.taotao.pojo.TbContent;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import pojo.TaotaoResult;

import java.util.List;

public interface TbContentMapper {
    @Select("select * from tbcontent where categoryId=#{categoryId}")
    List<TbContent> findContentCategory(Long contentCategoryId);
    @Insert("insert into tbcontent (categoryId, title, subTitle, titleDesc, url, pic, pic2, content, created, updated) VALUE (#{categoryId},#{title},#{subTitle},#{titleDesc},#{url},#{pic},#{pic2},#{content},#{created},#{updated})")
    void addContent(TbContent tbContent);
    @Delete("delete from tbcontent where id=#{tbContentId}")
    int deleteContent(Long tbContentId);
}

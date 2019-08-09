package com.taotao.mapper;

import com.taotao.pojo.TbContentCategory;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface TbContentCategoryMapper {
    @Select("select * from tbcontentcategory where parentId=#{parentId}")
    List<TbContentCategory> findContentCategoryByParentId(Long parentId);
    @Select("select * from tbcontentcategory where id=#{id}")
    TbContentCategory findContentCategoryById(Long parentId);
    @Update("update tbcontentcategory set isParent=#{isParent} WHERE id=#{id}")
    void updateContentCategory(TbContentCategory category);
    @Insert("insert into tbcontentcategory(parentId, name, status, sortOrder, isParent, created, updated) value (#{parentId},#{name},#{status},#{sortOrder},#{isParent},#{created},#{updated})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    void addContentCategory(TbContentCategory contentCategory);
    @Delete("delete from tbcontentcategory where id=#{id}")
    void deleteContentCategoryById(Long id);
    @Update("update tbcontentcategory set name = #{name} where id=#{id}")
    void updateContentCategoryName(Long id, String name);
}

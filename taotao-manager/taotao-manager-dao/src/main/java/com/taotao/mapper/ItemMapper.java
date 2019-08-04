package com.taotao.mapper;

import com.taotao.pojo.TbItem;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface ItemMapper {

    @Select("SELECT * FROM tbitem WHERE id=#{itemId}")
    TbItem findItemById(Long itemId);

    @Select("SELECT * FROM tbitem")
    List<TbItem> findItemAll();

    @Delete("<script> delete from tbitem where id in <foreach collection = 'array' item='id' open='(' separator = ',' close=')'>#{id}</foreach></script>")
    int delItems(Long[] ids);
    @Update("<script> update tbitem set status=1 where id in <foreach collection = 'array' item='id' open='(' separator = ',' close=')'>#{id}</foreach></script>")
    int updateUpItem(Long[] ids);
    @Update("<script> update tbitem set status=2 where id in <foreach collection = 'array' item='id' open='(' separator = ',' close=')'>#{id}</foreach></script>")
    int updateDownItem(Long[] ids);
    @Insert("INSERT INTO tbitem(id, title, sellPoint, price, num, barcode, image, cid, created, updated) VALUE(#{id},#{title},#{sellPoint},#{price},#{num},#{barcode},#{image},#{cid},#{created},#{updated})")
    int addItems(TbItem tbItem);
}

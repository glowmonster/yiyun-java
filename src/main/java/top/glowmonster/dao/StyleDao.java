package top.glowmonster.dao;

import org.apache.ibatis.annotations.Param;
import top.glowmonster.model.Style;

import java.util.List;

public interface StyleDao {
    // 不分页查询所有风格
    List<Style> selectAllStyle();
    // 分页查询所有风格
    List<Style> selectAllStyleByName(@Param("style_name") String style_name);
    // 添加风格
    Integer insertStyle(Style style);
    // 删除(可以多个)风格(假删)
    Integer deleteStyle(@Param("style_ids") String[] style_ids);
    // 修改风格
    Integer updateStyle(Style style);
}

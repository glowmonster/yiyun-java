package top.glowmonster.service;

import top.glowmonster.base.vo.BaseModel;
import top.glowmonster.model.Style;

public interface StyleService {
    BaseModel selectAllStyle(BaseModel baseModel) throws Exception;

    BaseModel insertStyle(Style style, BaseModel baseModel) throws Exception;

    BaseModel selectAllStyleByName(Integer curr_page, Integer page_size, BaseModel baseModel, String style_name) throws Exception;

    // 删除风格
    BaseModel deleteStyle(String[] style_ids, BaseModel baseModel) throws Exception;

    BaseModel updateStyle(Style style, BaseModel baseModel) throws Exception;
}

package top.glowmonster.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.glowmonster.base.em.ErrorMessageEnum;
import top.glowmonster.base.exception.BusinessException;
import top.glowmonster.base.vo.BaseModel;
import top.glowmonster.dao.StyleDao;
import top.glowmonster.model.Style;
import top.glowmonster.service.StyleService;

import java.util.List;

@Service
public class StyleServiceImpl implements StyleService {
    @Autowired
    StyleDao styleDao;

    public BaseModel selectAllStyle(BaseModel baseModel) throws Exception {
        List<Style> styles = styleDao.selectAllStyle();
        if (styles == null || styles.isEmpty()) {
            throw new BusinessException(ErrorMessageEnum.NO_DATA_ERROR);
        }
        baseModel.setResultCode(0);
        baseModel.setMessage("非分页查询风格表成功");
        baseModel.setData(styles);
        return baseModel;
    }

    public BaseModel insertStyle(Style style, BaseModel baseModel) throws Exception {
        Integer result = styleDao.insertStyle(style);
        if (result <= 0) {
            throw new BusinessException(ErrorMessageEnum.STYLE_INSERT_ERROR);
        }
        baseModel.setResultCode(0);
        baseModel.setMessage("添加歌曲风格成功!");
        baseModel.setData(result);
        return baseModel;
    }

    public BaseModel selectAllStyleByName(Integer curr_page, Integer page_size, BaseModel baseModel, String style_name) throws Exception {
        PageHelper.startPage(curr_page, page_size);
        List<Style> styles = styleDao.selectAllStyleByName(style_name);
        if (styles == null) {
            throw new BusinessException(ErrorMessageEnum.NO_DATA_ERROR);
        }
        PageInfo pageInfo = new PageInfo(styles, 5);
        baseModel.setResultCode(0);
        baseModel.setMessage("分页查询歌曲风格成功");
        baseModel.setData(pageInfo);
        return baseModel;
    }

    public BaseModel deleteStyle(String[] style_ids, BaseModel baseModel) throws Exception {
        Integer result = styleDao.deleteStyle(style_ids);
        if (result <= 0) {
            throw new BusinessException(ErrorMessageEnum.STYLE_DELETE_ERROR);
        }
        baseModel.setResultCode(0);
        baseModel.setMessage("成功删除" + result + "条数据");
        baseModel.setData(result);
        return baseModel;
    }

    public BaseModel updateStyle(Style style, BaseModel baseModel) throws Exception {
        Integer result = styleDao.updateStyle(style);
        if (result <= 0) {
            throw new BusinessException(ErrorMessageEnum.STYLE_UPDATE_ERROR);
        }
        baseModel.setResultCode(0);
        baseModel.setMessage("修改" + result + "条数据成功");
        baseModel.setData(result);
        return baseModel;
    }
}

package top.glowmonster.controller;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiImplicitParam;
import com.wordnik.swagger.annotations.ApiImplicitParams;
import com.wordnik.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import top.glowmonster.base.aop.AopOperation;
import top.glowmonster.base.controller.BaseController;
import top.glowmonster.base.em.ErrorMessageEnum;
import top.glowmonster.base.em.OperationModuleEnum;
import top.glowmonster.base.exception.BusinessException;
import top.glowmonster.base.vo.BaseModel;
import top.glowmonster.model.Style;
import top.glowmonster.service.StyleService;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

@Api("歌曲风格模块")
@Controller
@RequestMapping(value = "/style")
public class StyleController extends BaseController {

    @Autowired
    StyleService styleService;

    @AopOperation(desc = "修改歌曲风格信息", type = "修改", module = OperationModuleEnum.MODULE_SONG_STYLE)
    @ResponseBody
    @RequestMapping(value = "/updateStyle", method = RequestMethod.POST)
    @ApiOperation(value="修改歌曲风格", httpMethod = "POST", notes = "修改")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "风格名", name = "style_name", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(value = "风格版本", name = "style_version", required = true, dataType = "int", paramType = "query"),
            @ApiImplicitParam(value = "风格id", name = "style_id", required = true, dataType = "int", paramType = "query")
    })
    public BaseModel updateStyle(Integer style_id, String style_name, Integer style_version, BaseModel baseModel, Style style) throws Exception {
        if (style_name == null) {
            throw new BusinessException(ErrorMessageEnum.NO_VALUE_RECEIVED);
        }
        Timestamp timestamp = new Timestamp(new Date().getTime());
        style.setStyle_name(style_name);
        style.setStyle_update_time(timestamp);
        style.setStyle_id(style_id);
        style.setStyle_version(style_version);

        // SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // long longTimestamp = new Long(style_create_time);
        // String timeText = sdf.format(longTimestamp);
        // Date date = sdf.parse(timeText);
        // Timestamp timestampCreate = new Timestamp(date.getTime());
        // style.setStyle_create_time(style_create_time);
        baseModel = styleService.updateStyle(style, baseModel);
        return baseModel;
    }

    @AopOperation(desc = "添加歌曲风格信息", type = "新增", module = OperationModuleEnum.MODULE_SONG_STYLE)
    @ResponseBody
    @RequestMapping(value = "/insertStyle", method = RequestMethod.POST)
    @ApiOperation(value="添加歌曲风格", httpMethod = "POST", notes = "新增")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "风格名", name = "style_name", required = true, dataType = "String", paramType = "query")
    })
    public BaseModel insertStyle(String style_name, BaseModel baseModel, Style style) throws Exception {
        if (style_name == null) {
            throw new BusinessException(ErrorMessageEnum.NO_VALUE_RECEIVED);
        }
        Timestamp timestamp = new Timestamp(new Date().getTime());
        style.setStyle_version(0);
        style.setIs_deleted(0);
        style.setStyle_name(style_name);
        style.setStyle_create_time(timestamp);
        style.setStyle_update_time(timestamp);
        baseModel = styleService.insertStyle(style, baseModel);
        return baseModel;
    }

    @AopOperation(desc = "分页查询所有歌曲风格信息", type = "查询", module = OperationModuleEnum.MODULE_SONG_STYLE)
    @ResponseBody
    @RequestMapping(value = "/selectAllStyle", method = RequestMethod.GET)
    @ApiOperation(value="分页查询所有歌曲风格并可以搜索查询", httpMethod = "GET", notes = "新增")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "风格名", name = "style_name", dataType = "String", paramType = "query"),
            @ApiImplicitParam(value = "当前页面", name = "curr_page", required = true, dataType = "int", paramType = "query"),
            @ApiImplicitParam(value = "页面数据显示数量大小", name = "page_size", required = true, dataType = "int", paramType = "query")
    })
    public BaseModel selectAllStyleByName(Integer curr_page, Integer page_size, String style_name, BaseModel baseModel) throws Exception {
        baseModel = styleService.selectAllStyleByName(curr_page, page_size, baseModel, style_name);
        return baseModel;
    }

    @AopOperation(desc = "删除歌曲风格", type = "删除", module = OperationModuleEnum.MODULE_SONG_STYLE)
    @ResponseBody
    @RequestMapping(value = "/deleteStyle", method = RequestMethod.POST)
    @ApiOperation(value="删除歌曲风格", httpMethod = "POST", notes = "删除(假)")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "风格名", required = true, name = "style_ids", dataType = "String", paramType = "query")
    })
    public BaseModel deleteStyle(String style_ids, BaseModel baseModel) throws Exception {
        String[] idArray = style_ids.split(",");
        System.out.println("idArray:" + idArray);
        baseModel = styleService.deleteStyle(idArray, baseModel);
        return baseModel;
    }

    @AopOperation(desc = "查询所有歌曲风格", type = "查询", module = OperationModuleEnum.MODULE_SONG_STYLE)
    @ResponseBody
    @RequestMapping(value = "/selectAllStyleNo", method = RequestMethod.GET)
        @ApiOperation(value="查询所有风格", httpMethod = "get", notes = "查询")
        public BaseModel selectAllStyle(BaseModel baseModel) throws Exception {
            baseModel = styleService.selectAllStyle(baseModel);
        return baseModel;
    }
}

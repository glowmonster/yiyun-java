package top.glowmonster.controller;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiImplicitParam;
import com.wordnik.swagger.annotations.ApiImplicitParams;
import com.wordnik.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import top.glowmonster.base.controller.BaseController;
import top.glowmonster.base.vo.BaseModel;
import top.glowmonster.service.LoginLogService;

@Api("登陆日志模块")
@Controller
@RequestMapping(value = "/loginLog")
public class LoginLogController extends BaseController {
    @Autowired
    LoginLogService loginLogService;

    @RequestMapping(value = "/selectAllLoginLogByUserAccount")
    @ResponseBody
    @ApiOperation(value="可以根据用户名分页查询所有登陆日志", httpMethod = "GET", notes = "查询")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "用户名", name = "user_account", dataType = "String", paramType = "query"),
            @ApiImplicitParam(value = "当前页面", name = "curr_page", required = true, dataType = "int", paramType = "query"),
            @ApiImplicitParam(value = "页面数据显示数量大小", name = "page_size", required = true, dataType = "int", paramType = "query")
    })
    public BaseModel selectAllLoginLogByUserAccount(Integer curr_page, Integer page_size, BaseModel baseModel, String user_account) {
        baseModel = loginLogService.selectAllLoginLogByUserAccount(curr_page, page_size, user_account, baseModel);
        return baseModel;
    }

}

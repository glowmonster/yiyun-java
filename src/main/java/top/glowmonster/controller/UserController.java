package top.glowmonster.controller;

import com.aliyun.oss.OSS;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiImplicitParam;
import com.wordnik.swagger.annotations.ApiImplicitParams;
import com.wordnik.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import top.glowmonster.base.aop.AopOperation;
import top.glowmonster.base.controller.BaseController;
import top.glowmonster.base.em.ErrorMessageEnum;
import top.glowmonster.base.em.OperationModuleEnum;
import top.glowmonster.base.exception.BusinessException;
import top.glowmonster.base.oss.BaseOSS;
import top.glowmonster.base.vo.BaseModel;
import top.glowmonster.model.User;
import top.glowmonster.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.InputStream;
import java.net.URL;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

@Api("用户模块")
@Controller
@RequestMapping(value = "/user")
public class UserController extends BaseController {

    @Autowired
    UserService userService;

    InputStream userAvatarFileInputStream;
    String userAvatarFileName;

    @AopOperation(desc = "登陆", type = "查询", module = OperationModuleEnum.MODULE_USER)
    @ApiImplicitParams({
            @ApiImplicitParam(value = "用户名", name = "account", required = true, defaultValue = "admin", dataType = "String", paramType = "query"),
            @ApiImplicitParam(value = "密码",  name = "password", required = true, defaultValue = "admin", dataType = "String", paramType = "query")
    })
    @ApiOperation(value = "登陆", httpMethod = "POST", notes = "查询")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public BaseModel login(String user_account, String user_password, BaseModel baseModel, HttpServletRequest request) throws Exception {
        baseModel = userService.selectUserByAccountAndPassword(user_account, user_password, baseModel);
        HttpSession session = request.getSession();
        session.setAttribute("currentUser", baseModel.getData());
        return baseModel;
    }


    // @AopOperation(desc = "注册", type = "新增", module = OperationModuleEnum.MODULE_USER)
    @ApiImplicitParams({
            @ApiImplicitParam(value = "用户名", name = "user_account", required = true, defaultValue = "admin", dataType = "String", paramType = "query"),
            @ApiImplicitParam(value = "密码",  name = "user_password", required = true, defaultValue = "admin", dataType = "String", paramType = "query"),
            @ApiImplicitParam(value = "用户头像",  name = "user_avatar_url", required = true, defaultValue = "admin", dataType = "String", paramType = "query"),
            @ApiImplicitParam(value = "用户昵称",  name = "user_name", required = true, defaultValue = "admin", dataType = "String", paramType = "query"),
            @ApiImplicitParam(value = "用户描述",  name = "user_describes", defaultValue = "admin", dataType = "String", paramType = "query"),
            @ApiImplicitParam(value = "用户类型",  name = "user_type", required = true, defaultValue = "admin", dataType = "int", paramType = "query"),
    })
    @ApiOperation(value = "注册", httpMethod = "POST", notes = "新增")
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public BaseModel insertUser(String user_account, String user_password, String user_name, String user_describes, BaseModel baseModel) throws Exception {
        Timestamp timestamp = new Timestamp(new Date().getTime());
        User user = new User();
        user.setUser_account(user_account);
        user.setUser_password(user_password);
        OSS ossClient = new BaseOSS().bulidOSSClient();
        String bucketName = "yiyunmusic";
        // 设置URL过期时间为1年(不知道对不对),如果是3600 * 1000就是一小时。
        Date expiration = new Date(new Date().getTime() + 3600 * 1000 * 24 * 365);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss");
        String date = sdf.format(new Date());
        String userImageFileNameKey = "userAvatar/" + userAvatarFileName + date;
        // 先将文件上传至oss服务器
        // 上传音乐图片文件到oss服务器
        ossClient.putObject(bucketName, userImageFileNameKey, userAvatarFileInputStream);
        // 生成以GET方法访问的签名URL，访客可以直接通过浏览器访问相关内容
        // 设置授权访问oss服务器的文件(期限为设为了一年,大概吧...)(经过验证实际上时间为1个月,一个月后文件就会失效)
        // 音乐图片地址
        URL userAvatarUrl = ossClient.generatePresignedUrl(bucketName, userImageFileNameKey, expiration);
        user.setUser_avatar_url(userAvatarUrl + "");
        user.setUser_name(user_name);
        user.setUser_describes(user_describes);
        user.setUser_version(0);
        user.setIs_deleted(0);
        user.setUser_create_time(timestamp);
        user.setUser_update_time(timestamp);
        baseModel = userService.insertUser(user, baseModel);
        ossClient.shutdown();
        return baseModel;
    }

    @AopOperation(desc = "上传用户头像", type = "上传", module = OperationModuleEnum.MODULE_USER)
    @ResponseBody
    @RequestMapping(value = "/uploadUserAvatarFile")
    @ApiOperation(value = "上传用户头像", httpMethod = "POST", notes = "上传")
    public void uploadUserAvatarFile(MultipartFile file) throws Exception {
        String realFileName = file.getOriginalFilename();
        String suffix = realFileName.substring(realFileName.lastIndexOf(".") + 1);

        if (file.isEmpty()) {
            throw new BusinessException(ErrorMessageEnum.USER_AVATAR_UPLOAD_ERROR);
        } else if (!suffix.equals("jpg") && !suffix.equals("jpeg")) {
            // System.out.println("进了异常");
            throw new BusinessException(ErrorMessageEnum.USER_AVATAR_TYPE_ERROR);
        }

        System.out.println("(songImage)该文件后缀为" + suffix);
        // 获取音乐图片上传时的名字
        userAvatarFileName = file.getOriginalFilename();
        // 获取音乐图片文件流
        userAvatarFileInputStream = file.getInputStream();
    }

    @AopOperation(desc = "更新用户", type = "修改", module = OperationModuleEnum.MODULE_USER)
    @ApiImplicitParams({
            @ApiImplicitParam(value = "用户id", name = "user_id", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(value = "用户昵称",  name = "user_name", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(value = "用户描述",  name = "user_describes", required = true, dataType = "String", paramType = "query")
    })
    @ApiOperation(value = "更新用户", httpMethod = "POST", notes = "修改")
    @RequestMapping(value = "/updateUser", method = RequestMethod.POST)
    @ResponseBody
    public BaseModel updateUser(Integer user_id, String user_name, String user_describes, BaseModel baseModel, HttpServletRequest request) throws Exception {
        baseModel = userService.updateUser(user_id, user_name, user_describes, baseModel);
        return baseModel;
    }



}

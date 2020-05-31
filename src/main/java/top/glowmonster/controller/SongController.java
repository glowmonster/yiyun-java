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
import top.glowmonster.model.Song;
import top.glowmonster.service.SongService;

import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;


@Api("歌曲模块")
@Controller
@RequestMapping(value = "/song")
public class SongController extends BaseController {
    @Autowired
    SongService songService;

    InputStream songImageInputStream;
    InputStream songFileInputStream;
    String songImageFileName;
    String songFileName;



    @AopOperation(desc = "可根据歌曲名或歌曲作者分页查询所有歌曲", type = "查询", module = OperationModuleEnum.MODULE_SONG)
    @ResponseBody
    @RequestMapping(value = "/selectAllSongByNameOrAuthor", method = RequestMethod.GET)
    @ApiOperation(value="根据歌曲名或者歌曲作者分页查询歌曲内容", httpMethod = "GET", notes = "查询")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "歌曲名", name = "song_name", dataType = "String", paramType = "query"),
            @ApiImplicitParam(value = "歌曲作者", name = "song_author", dataType = "String", paramType = "query"),
            @ApiImplicitParam(value = "当前页面", name = "curr_page", required = true, dataType = "int", paramType = "query"),
            @ApiImplicitParam(value = "页面数据显示数量大小", name = "page_size", required = true, dataType = "int", paramType = "query")
    })
    public BaseModel selectAllSongByNameOrAuthor(BaseModel baseModel, Integer curr_page, Integer page_size, String song_name, String song_author) throws Exception {
        if (curr_page == null || page_size == null) {
            throw new BusinessException(ErrorMessageEnum.SELECT_NO_PARAMETERS_ERROR);
        }
        baseModel = songService.selectAllSongByNameOrAuthor(curr_page, page_size, song_name, song_author, baseModel);
        return baseModel;
    }

    @AopOperation(desc = "添加歌曲", type = "新增", module = OperationModuleEnum.MODULE_SONG)
    @ResponseBody
    @RequestMapping(value = "/insertSong", method = RequestMethod.POST)
    @ApiOperation(value="添加歌曲", httpMethod = "POST", notes = "新增(swagger无法调用)")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "歌曲名", name = "song_name", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(value = "歌曲作者", name = "song_author", required = true, dataType = "String", paramType = "query")
    })
    public BaseModel insertSong(Integer style_id, String song_name, String song_author, Song song, BaseModel baseModel) throws Exception {
        if (songFileInputStream == null || songImageInputStream == null || song_name == null || song_author == null) {
            throw new BusinessException(ErrorMessageEnum.SONG_INSERT_ERROR);
        }
        Timestamp timestamp = new Timestamp(new Date().getTime());
        // 得到oss连接
        OSS ossClient = new BaseOSS().bulidOSSClient();
        String bucketName = "yiyunmusic";
        // 设置URL过期时间为1年(不知道对不对),如果是3600 * 1000就是一小时。
        Date expiration = new Date(new Date().getTime() + 3600 * 1000 * 24 * 365);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss");
        String date = sdf.format(new Date());
        String songFileNameKey = "music/" + songFileName + date;
        String songImageFileNameKey = "avatar/" + songImageFileName + date;
        // 先将文件上传至oss服务器
        // 上传音乐图片文件到oss服务器
        ossClient.putObject(bucketName, songImageFileNameKey, songImageInputStream);
        // 上传音乐文件到oss服务器
        ossClient.putObject(bucketName, songFileNameKey, songFileInputStream);
        // 生成以GET方法访问的签名URL，访客可以直接通过浏览器访问相关内容
        // 设置授权访问oss服务器的文件(期限为设为了一年,大概吧...)
        // 音乐图片地址
        URL songImageUrl = ossClient.generatePresignedUrl(bucketName, songImageFileNameKey, expiration);
        // 音乐文件地址
        URL songFileUrl = ossClient.generatePresignedUrl(bucketName, songFileNameKey, expiration);
        ossClient.shutdown();
        song.setSong_name(song_name);
        song.setSong_author(song_author);
        song.setSong_create_time(timestamp);
        song.setSong_update_time(timestamp);
        song.setSong_image(songImageUrl + "");
        song.setSong_url(songFileUrl + "");
        song.setIs_deleted(0);
        song.setSong_version(0);
        song.setStyle_id(style_id);
        song.setSong_great(0);
        baseModel = songService.insertSong(song, baseModel);
        return baseModel;
    }

    @AopOperation(desc = "上传歌曲图片", type = "上传", module = OperationModuleEnum.MODULE_SONG)
    @ResponseBody
    @ApiOperation(value="上传歌曲图片", httpMethod = "POST", notes = "上传")
    @RequestMapping(value = "/avatarUpload", method = RequestMethod.POST)
    public void imageUpload(MultipartFile file) throws Exception {
        String realFileName = file.getOriginalFilename();
        String suffix = realFileName.substring(realFileName.lastIndexOf(".") + 1);
        // System.out.println("文件名为:" + realFileName + ";" + "文件类型为:" + suffix);
        // ossClient.putObject("yiyunmusic", "avatar/" + realFileName, inputStream);
        // ossClient.shutdown();generatePresignedUrl
        if (file.isEmpty()) {
            throw new BusinessException(ErrorMessageEnum.SONG_IMAGE_UPLOAD_ERROR);
        } else if (!suffix.equals("jpg")) {
            // System.out.println("进了异常");
            throw new BusinessException(ErrorMessageEnum.SONG_IMAGE_TYPE_ERROR);
        }
        // System.out.println(suffix.equals("jpg"));
        System.out.println("(songImage)该文件后缀为" + suffix);
        // 获取音乐图片上传时的名字
        songImageFileName = file.getOriginalFilename();
        // 获取音乐图片文件流
        songImageInputStream = file.getInputStream();
    }

    @AopOperation(desc = "上传歌曲文件", type = "上传", module = OperationModuleEnum.MODULE_SONG)
    @ResponseBody
    @ApiOperation(value="上传歌曲文件", httpMethod = "POST", notes = "上传")
    @RequestMapping(value = "/songFileUpload", method = RequestMethod.POST)
    public void songFileUpload(MultipartFile file) throws Exception {
        String realFileName = file.getOriginalFilename();
        String suffix = realFileName.substring(realFileName.lastIndexOf(".") + 1);
        if (file.isEmpty()) {
            throw new BusinessException(ErrorMessageEnum.SONG_FILE_UPLOAD_ERROR);
        } else if (!suffix.equals("mp3")) {
            throw new BusinessException(ErrorMessageEnum.SONG_FILE_TYPE_ERROR);
        }
        System.out.println("(songFile)该文件后缀为" + suffix);
        // 获取音乐文件流
        songFileInputStream = file.getInputStream();
        // 获取音乐文件上传时的名字
        songFileName = file.getOriginalFilename();
    }

    @AopOperation(desc = "删除歌曲", type = "删除", module = OperationModuleEnum.MODULE_SONG)
    @ResponseBody
    @RequestMapping(value = "/deleteSong", method = RequestMethod.POST)
    @ApiOperation(value="删除歌曲", httpMethod = "POST", notes = "删除(假)")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "歌曲id", required = true, name = "song_id", dataType = "int", paramType = "query")
    })
    public BaseModel deleteStyle(Integer song_id, BaseModel baseModel) throws Exception {
        baseModel = songService.deleteSongById(song_id, baseModel);
        return baseModel;
    }

    @AopOperation(desc = "修改歌曲信息", type = "修改", module = OperationModuleEnum.MODULE_SONG)
    @ResponseBody
    @RequestMapping(value = "/updateSong", method = RequestMethod.POST)
    @ApiOperation(value="更新歌曲", httpMethod = "POST", notes = "修改")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "歌曲id", required = true, name = "song_id", dataType = "int", paramType = "query")
    })
    public BaseModel updateSong(Integer song_id, String song_name, String song_author, BaseModel baseModel) throws Exception {
        Timestamp timestamp = new Timestamp(new Date().getTime());
        baseModel = songService.updateSong(song_id, baseModel, song_name, song_author, timestamp);
        return baseModel;
    }

    @AopOperation(desc = "根据ids查询所有歌曲", type = "查询", module = OperationModuleEnum.MODULE_SONG)
    @ResponseBody
    @RequestMapping(value = "/selectAllSong", method = RequestMethod.GET)
    @ApiOperation(value="查询歌曲", httpMethod = "GET", notes = "查询")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "歌曲id数组", name = "song_ids", dataType = "String", paramType = "query")
    })
    public BaseModel selectAllSong(String song_ids, BaseModel baseModel) throws Exception {
        String[] idArray = null;
        if (song_ids != null) {
            idArray = song_ids.split(",");
            System.out.println("idArray:" + idArray);
        }
        baseModel = songService.selectAllSong(idArray, baseModel);
        return baseModel;
    }

    @AopOperation(desc = "根据以外的的ids查询歌曲", type = "上传", module = OperationModuleEnum.MODULE_SONG)
    @ResponseBody
    @RequestMapping(value = "/selectOtherAllSong", method = RequestMethod.GET)
    @ApiOperation(value="查询歌曲", httpMethod = "GET", notes = "查询")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "歌曲id数组", name = "song_ids", dataType = "String", paramType = "query")
    })
    public BaseModel selectOtherAllSong(String song_ids, BaseModel baseModel) throws Exception {
        String[] idArray = null;
        if (song_ids != null) {
            idArray = song_ids.split(",");
            System.out.println("idArray:" + idArray);
        }
        baseModel = songService.selectOtherAllSong(idArray, baseModel);
        return baseModel;
    }

    @AopOperation(desc = "点赞歌曲", type = "修改", module = OperationModuleEnum.MODULE_SONG)
    @ResponseBody
    @RequestMapping(value = "/greatSong", method = RequestMethod.POST)
    @ApiOperation(value="点赞歌曲", httpMethod = "POST", notes = "修改")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "歌曲id", required = true, name = "song_id", dataType = "int", paramType = "query")
    })
    public BaseModel greatSong(Integer song_id, BaseModel baseModel) throws Exception {
        baseModel = songService.songGreat(baseModel, song_id);
        return baseModel;
    }
}

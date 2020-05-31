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
import top.glowmonster.model.SongList;
import top.glowmonster.service.SongListService;

import java.io.InputStream;
import java.net.URL;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;


@Api("歌单模块")
@Controller
@RequestMapping(value = "/songList")
public class SongListController extends BaseController {
    @Autowired
    SongListService songListService;

    InputStream songListImageInputStream;
    String songListImageFileName;

    @AopOperation(desc = "上传歌曲图片", type = "上传", module = OperationModuleEnum.MODULE_SONG_LIST)
    @ResponseBody
    @ApiOperation(value="上传歌曲图片", httpMethod = "POST", notes = "上传")
    @RequestMapping(value = "/songListImageUpload", method = RequestMethod.POST)
    public void imageUpload(MultipartFile file) throws Exception {
        String realFileName = file.getOriginalFilename();
        String suffix = realFileName.substring(realFileName.lastIndexOf(".") + 1);
        if (file.isEmpty()) {
            throw new BusinessException(ErrorMessageEnum.SONG_IMAGE_UPLOAD_ERROR);
        } else if (!suffix.equals("jpg")) {
            throw new BusinessException(ErrorMessageEnum.SONG_IMAGE_TYPE_ERROR);
        }
        System.out.println("(songImage)该文件后缀为" + suffix);
        songListImageInputStream = file.getInputStream();
        songListImageFileName = file.getOriginalFilename();
    }


    @AopOperation(desc = "添加歌单", type = "新增", module = OperationModuleEnum.MODULE_SONG_LIST)
    @ResponseBody
    @RequestMapping(value = "/addSongList", method = RequestMethod.POST)
    @ApiOperation(value="添加歌单", httpMethod = "POST", notes = "添加")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "歌单名", required = true, name = "list_name", dataType = "String", paramType = "query"),
    })
    public BaseModel addSongList(String list_name, SongList songList, BaseModel baseModel) throws Exception {
        Timestamp timestamp = new Timestamp(new Date().getTime());
        // 得到oss连接
        OSS ossClient = new BaseOSS().bulidOSSClient();
        String bucketName = "yiyunmusic";

        // 设置URL过期时间为1年(不知道对不对),如果是3600 * 1000就是一小时。
        Date expiration = new Date(new Date().getTime() + 3600 * 1000 * 24 * 365);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss");
        String date = sdf.format(new Date());
        String songListImageFileNameKey = "songListImage/" + songListImageFileName + date;
        ossClient.putObject(bucketName, songListImageFileNameKey, songListImageInputStream);
        URL songImageUrl = ossClient.generatePresignedUrl(bucketName, songListImageFileNameKey, expiration);
        ossClient.shutdown();
        songList.setList_image(songImageUrl + "");
        songList.setList_name(list_name);
        songList.setIs_deleted(0);
        songList.setList_create_time(timestamp);
        songList.setList_update_time(timestamp);
        songList.setList_version(0);
        songList.setSong_ids(null);
        baseModel = songListService.addSongList(songList, baseModel);
        return baseModel;
    }

    @AopOperation(desc = "分页查询所有歌单", type = "查询", module = OperationModuleEnum.MODULE_SONG_LIST)
    @ResponseBody
    @RequestMapping(value = "/selectSongList", method = RequestMethod.GET)
    @ApiOperation(value="根据歌单名分页查询所有歌单", httpMethod = "GET", notes = "查询")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "歌单名", name = "list_name", dataType = "String", paramType = "query"),
            @ApiImplicitParam(value = "当前页面", name = "curr_page", required = true, dataType = "int", paramType = "query"),
            @ApiImplicitParam(value = "页面数据显示数量大小", name = "page_size", required = true, dataType = "int", paramType = "query")
    })
    public BaseModel selectAllSongByNameOrAuthor(Integer curr_page, Integer page_size, String list_name, BaseModel baseModel) throws Exception {
        if (curr_page == null || page_size == null) {
            throw new BusinessException(ErrorMessageEnum.SELECT_NO_PARAMETERS_ERROR);
        }
        baseModel = songListService.selectSongList(baseModel, list_name, curr_page, page_size);
        return baseModel;
    }

    @AopOperation(desc = "根据歌单名分页查询所有歌单", type = "查询", module = OperationModuleEnum.MODULE_SONG_LIST)
    @ResponseBody
    @RequestMapping(value = "/updateSongList", method = RequestMethod.POST)
    @ApiOperation(value="根据歌单名分页查询所有歌单", httpMethod = "POST", notes = "查询")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "歌单名", name = "list_name", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(value = "歌单版本号", name = "list_version", required = true, dataType = "int", paramType = "query"),
            @ApiImplicitParam(value = "歌单id", name = "list_id", required = true, dataType = "int", paramType = "query")
    })
    public BaseModel updateSongList(BaseModel baseModel, Integer list_version, String list_name, Integer list_id) throws Exception {
        Timestamp timestamp = new Timestamp(new Date().getTime());
        baseModel = songListService.updateSongList(list_id, list_name, timestamp, baseModel, list_version);
        return baseModel;
    }

    @AopOperation(desc = "修改歌单信息", type = "修改", module = OperationModuleEnum.MODULE_SONG_LIST)
    @ResponseBody
    @RequestMapping(value = "/updateSongIds", method = RequestMethod.POST)
    @ApiOperation(value="修改song_ids", httpMethod = "POST", notes = "修改")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "歌曲id字符串", name = "song_ids", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(value = "歌单id", name = "list_id", required = true, dataType = "int", paramType = "query")
    })
    public BaseModel updateSongIds(BaseModel baseModel, String song_ids, Integer list_id) throws Exception {
        baseModel = songListService.updateSong_ids(song_ids, list_id, baseModel);
        return baseModel;
    }

    @AopOperation(desc = "删除歌单", type = "删除", module = OperationModuleEnum.MODULE_SONG_LIST)
    @ResponseBody
    @RequestMapping(value = "/deleteSongList", method = RequestMethod.POST)
    @ApiOperation(value="删除歌单", httpMethod = "POST", notes = "删除(假)")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "歌单id", name = "list_id", required = true, dataType = "int", paramType = "query")
    })
    public BaseModel deleteSongList(BaseModel baseModel, Integer list_id) throws Exception {
        baseModel = songListService.deleteSongList(list_id, baseModel);
        return baseModel;
    }

}

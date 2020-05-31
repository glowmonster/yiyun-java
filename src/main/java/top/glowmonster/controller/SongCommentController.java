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
import top.glowmonster.model.SongComment;
import top.glowmonster.service.SongCommentService;
import top.glowmonster.service.SongService;

import java.io.InputStream;
import java.net.URL;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;


@Api("歌曲评论模块")
@Controller
@RequestMapping(value = "/songComment")
public class SongCommentController extends BaseController {
    @Autowired
    SongCommentService songCommentService;

    @AopOperation(desc = "添加歌曲评论", type = "新增", module = OperationModuleEnum.MODULE_SONG_COMMENT)
    @ResponseBody
    @RequestMapping(value = "/insertSongComment", method = RequestMethod.POST)
    @ApiOperation(value="添加歌曲评论", httpMethod = "POST", notes = "新增")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "歌曲id", name = "song_id", dataType = "int", paramType = "query"),
            @ApiImplicitParam(value = "该歌曲的评论内容", name = "song_comment_text", dataType = "String", paramType = "query")
    })
    public BaseModel insertSongComment(Integer song_id, String comment_text, BaseModel baseModel) throws Exception {
        Timestamp timestamp = new Timestamp(new Date().getTime());
        SongComment songComment = new SongComment();
        songComment.setComment_create_time(timestamp);
        songComment.setComment_update_time(timestamp);
        songComment.setComment_version(0);
        songComment.setIs_deleted(0);
        songComment.setSong_id(song_id);
        songComment.setComment_text(comment_text);
        baseModel = songCommentService.insertSongComment(baseModel, songComment);
        return baseModel;
    }

    @AopOperation(desc = "根据歌曲id查询评论", type = "查询", module = OperationModuleEnum.MODULE_SONG_COMMENT)
    @ResponseBody
    @RequestMapping(value = "/selectAllSongComment", method = RequestMethod.GET)
    @ApiOperation(value="根据歌曲id查询评论", httpMethod = "GET", notes = "查询")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "歌曲id", name = "song_id", dataType = "int", paramType = "query"),
            @ApiImplicitParam(value = "该歌曲的评论内容", name = "song_comment_text", dataType = "String", paramType = "query"),
            @ApiImplicitParam(value = "当前页面", required = true, name = "curr_page", dataType = "int", paramType = "query"),
            @ApiImplicitParam(value = "页面大小", required = true, name = "page_size", dataType = "int", paramType = "query")
    })
    public BaseModel selectAllSongComment(Integer song_id, Integer curr_page, Integer page_size, BaseModel baseModel) throws Exception {
        baseModel = songCommentService.selectAllSongComment(baseModel, song_id, curr_page, page_size);
        return baseModel;
    }


}

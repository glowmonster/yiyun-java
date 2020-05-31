package top.glowmonster.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.glowmonster.base.em.ErrorMessageEnum;
import top.glowmonster.base.exception.BusinessException;
import top.glowmonster.base.vo.BaseModel;
import top.glowmonster.dao.SongCommentDao;
import top.glowmonster.model.SongComment;
import top.glowmonster.service.SongCommentService;

import java.util.List;

@Service
public class SongCommentServiceImpl implements SongCommentService {
    @Autowired
    SongCommentDao songCommentDao;


    public BaseModel insertSongComment(BaseModel baseModel, SongComment songComment) throws Exception {
        Integer result = songCommentDao.insertSongComment(songComment);
        if (result <= 0) {
            throw new BusinessException(ErrorMessageEnum.SONG_COMMENT_INSERT_ERROR);
        }
        baseModel.setResultCode(0);
        baseModel.setMessage("评论成功");
        return baseModel;
    }

    public BaseModel selectAllSongComment(BaseModel baseModel, Integer song_id, Integer curr_page, Integer page_size) throws Exception {
        PageHelper.startPage(curr_page, page_size);
        List<SongComment> songCommentList = songCommentDao.selectAllSongComment(song_id);
        PageInfo pageInfo = new PageInfo(songCommentList, 5);
        baseModel.setResultCode(0);
        baseModel.setMessage("根据歌曲id分页查询评论");
        baseModel.setData(pageInfo);
        return baseModel;
    }
}

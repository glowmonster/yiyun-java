package top.glowmonster.service;

import top.glowmonster.base.vo.BaseModel;
import top.glowmonster.model.SongComment;

public interface SongCommentService {
    // 新增评论
    BaseModel insertSongComment(BaseModel baseModel, SongComment songComment) throws Exception;

    BaseModel selectAllSongComment(BaseModel baseModel, Integer song_id, Integer curr_page, Integer page_size) throws Exception;
}

package top.glowmonster.dao;

import org.apache.ibatis.annotations.Param;
import top.glowmonster.model.SongComment;

import java.util.List;

public interface SongCommentDao {
    Integer insertSongComment(SongComment songComment);

    List<SongComment> selectAllSongComment(@Param("song_id") Integer song_id);
}

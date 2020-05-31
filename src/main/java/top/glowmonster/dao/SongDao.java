package top.glowmonster.dao;

import org.apache.ibatis.annotations.Param;
import top.glowmonster.model.Song;

import java.util.Date;
import java.util.List;

public interface SongDao {

    List<Song> selectAllSongByNameOrAuthor(@Param("song_name") String song_name, @Param("song_author") String song_author);

    Integer insertSong(Song song);

    // 删除歌曲
    Integer deleteSongById(@Param("song_id") Integer song_id);

    Integer updateSong(@Param("song_id") Integer song_id, @Param("song_name") String song_name, @Param("song_author") String song_author, @Param("song_update_time") Date song_update_time);

    // 不分页查询所有歌曲
    List<Song> selectAllSong(@Param("song_ids") String[] song_ids);

    // 除song_ids以外的歌曲
    List<Song> selectOtherAllSong(@Param("song_ids") String[] song_ids);

    // 歌曲点赞功能
    Integer songGreat(@Param("song_id") Integer song_id);


}

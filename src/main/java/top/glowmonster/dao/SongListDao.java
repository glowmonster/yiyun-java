package top.glowmonster.dao;

import org.apache.ibatis.annotations.Param;
import top.glowmonster.model.SongList;

import java.util.Date;
import java.util.List;

public interface SongListDao {
    // 添加歌单
    Integer addSongList(SongList songList);

    // 分页查询所有歌单并带有搜索
    List<SongList> selectSongList(@Param("list_name") String list_name);

    // 修改歌单
    Integer updateSongList(@Param("list_id") Integer list_id, @Param("list_name") String list_name, @Param("list_update_time") Date list_update_time, @Param("list_version") Integer list_version);

    // 修改song_ids
    Integer updateSong_ids(@Param("song_ids") String ids, @Param("list_id") Integer list_id);

    // 删除歌单
    Integer deleteSongList(@Param("list_id") Integer list_id);
}

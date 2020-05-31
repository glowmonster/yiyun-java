package top.glowmonster.service;

import org.apache.ibatis.annotations.Param;
import top.glowmonster.base.vo.BaseModel;
import top.glowmonster.model.SongList;

import java.util.Date;

public interface SongListService {
    // 添加歌单
    BaseModel addSongList(SongList songList, BaseModel baseModel) throws Exception;

    // 分页查询所有歌单
    BaseModel selectSongList(BaseModel baseModel, String list_name, Integer curr_page, Integer page_size) throws Exception;

    // 修改歌单
    BaseModel updateSongList(Integer list_id, String list_name, Date list_update_time, BaseModel baseModel, Integer list_version) throws Exception;

    BaseModel updateSong_ids(String song_ids, Integer list_id, BaseModel baseModel) throws Exception;

    BaseModel deleteSongList(Integer list_id, BaseModel baseModel) throws Exception;
}

package top.glowmonster.service;

import top.glowmonster.base.vo.BaseModel;
import top.glowmonster.model.Song;

import java.util.Date;

public interface SongService {
    // 根据歌曲名或者歌曲作者分页查询歌曲内容
    BaseModel selectAllSongByNameOrAuthor(Integer curr_page, Integer page_size, String song_name, String song_author, BaseModel baseModel) throws Exception;

    // 上传歌曲
    BaseModel insertSong(Song song, BaseModel baseModel) throws Exception;

    BaseModel deleteSongById(Integer song_id, BaseModel baseModel) throws Exception;

    BaseModel updateSong(Integer song_id, BaseModel baseModel, String song_name, String song_author, Date song_update_time) throws Exception;

    // 不分页查询歌曲
    BaseModel selectAllSong(String[] song_ids, BaseModel baseModel) throws Exception;

    BaseModel selectOtherAllSong(String[] song_ids, BaseModel baseModel) throws Exception;

    // 歌曲点赞
    BaseModel songGreat(BaseModel baseModel, Integer song_id) throws Exception;


}

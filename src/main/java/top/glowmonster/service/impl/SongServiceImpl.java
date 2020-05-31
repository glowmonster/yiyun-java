package top.glowmonster.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.glowmonster.base.em.ErrorMessageEnum;
import top.glowmonster.base.exception.BusinessException;
import top.glowmonster.base.vo.BaseModel;
import top.glowmonster.dao.SongDao;
import top.glowmonster.model.Song;
import top.glowmonster.service.SongService;

import java.util.Date;
import java.util.List;

@Service
public class SongServiceImpl implements SongService {
    @Autowired
    SongDao songDao;

    public BaseModel selectAllSongByNameOrAuthor(Integer curr_page, Integer page_size, String song_name, String song_author, BaseModel baseModel) throws Exception {
        PageHelper.startPage(curr_page, page_size);
        List<Song> songList = songDao.selectAllSongByNameOrAuthor(song_name, song_author);
        PageInfo pageInfo = new PageInfo(songList, 5);
        baseModel.setResultCode(0);
        baseModel.setMessage("根据歌曲名或者歌曲作者分页查询歌曲内容成功");
        baseModel.setData(pageInfo);
        return baseModel;
    }

    public BaseModel insertSong(Song song, BaseModel baseModel) throws Exception {
        Integer result = songDao.insertSong(song);
        if (result <= 0) {
            throw new BusinessException(ErrorMessageEnum.SONG_INSERT_ERROR);
        }
        baseModel.setResultCode(0);
        baseModel.setMessage("上传成功");
        baseModel.setData(result);
        return baseModel;
    }

    public BaseModel deleteSongById(Integer song_id, BaseModel baseModel) throws Exception {
        Integer result = songDao.deleteSongById(song_id);
        if (result <= 0) {
            throw new BusinessException(ErrorMessageEnum.SONG_DELETE_ERROR);
        }
        baseModel.setResultCode(0);
        baseModel.setMessage("删除歌曲成功!");
        baseModel.setData(result);
        return baseModel;
    }

    public BaseModel updateSong(Integer song_id, BaseModel baseModel, String song_name, String song_author, Date song_update_time) throws Exception {
        Integer result = songDao.updateSong(song_id, song_name, song_author, song_update_time);
        if (result <= 0) {
            throw new BusinessException(ErrorMessageEnum.SONG_UPDATE_ERROR);
        }
        baseModel.setResultCode(0);
        baseModel.setMessage("更新歌曲成功");
        baseModel.setData(result);
        return baseModel;
    }

    public BaseModel selectAllSong(String[] song_ids, BaseModel baseModel) {
        List<Song> songList = songDao.selectAllSong(song_ids);
        baseModel.setResultCode(0);
        baseModel.setMessage("查询所有歌曲成功(可有song_ids)");
        baseModel.setData(songList);
        return baseModel;
    }

    public BaseModel selectOtherAllSong(String[] song_ids, BaseModel baseModel) throws Exception {
        List<Song> songList = songDao.selectOtherAllSong(song_ids);
        baseModel.setResultCode(0);
        baseModel.setMessage("查询歌曲成功(除song_ids以外的歌曲)");
        baseModel.setData(songList);
        return baseModel;
    }

    public BaseModel songGreat(BaseModel baseModel, Integer song_id) throws Exception {
        Integer result = songDao.songGreat(song_id);
        if (result <= 0) {
            throw new BusinessException(ErrorMessageEnum.SONG_GREAT_ERROR);
        }
        baseModel.setResultCode(0);
        baseModel.setMessage("点赞成功");
        return baseModel;
    }
}

package top.glowmonster.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.glowmonster.base.em.ErrorMessageEnum;
import top.glowmonster.base.exception.BusinessException;
import top.glowmonster.base.vo.BaseModel;
import top.glowmonster.dao.SongListDao;
import top.glowmonster.model.Song;
import top.glowmonster.model.SongList;
import top.glowmonster.service.SongListService;

import java.util.Date;
import java.util.List;

@Service
public class SongListServiceImpl implements SongListService {
    @Autowired
    SongListDao songListDao;

    public BaseModel addSongList(SongList songList, BaseModel baseModel) throws Exception {
        Integer result = songListDao.addSongList(songList);
        if (result <= 0) {
            throw new BusinessException(ErrorMessageEnum.SONG_LIST_ADD_ERROR);
        }
        baseModel.setResultCode(0);
        baseModel.setMessage("添加歌单成功");
        baseModel.setData(result);
        return baseModel;
    }

    public BaseModel selectSongList(BaseModel baseModel, String list_name, Integer curr_page, Integer page_size) throws Exception {
        PageHelper.startPage(curr_page, page_size);
        List<SongList> songList = songListDao.selectSongList(list_name);
        PageInfo pageInfo = new PageInfo(songList, 5);
        baseModel.setResultCode(0);
        baseModel.setMessage("根据歌单名称分页查询所有歌单成功");
        baseModel.setData(pageInfo);
        return baseModel;
    }

    public BaseModel updateSongList(Integer list_id, String list_name, Date list_update_time, BaseModel baseModel, Integer list_version) throws Exception {
        Integer result = songListDao.updateSongList(list_id, list_name, list_update_time, list_version);
        if (result <= 0) {
            throw new BusinessException(ErrorMessageEnum.SONG_LIST_UPDATE_ERROR);
        }
        baseModel.setResultCode(0);
        baseModel.setMessage("修改歌单信息成功");
        baseModel.setData(result);
        return baseModel;
    }

    public BaseModel updateSong_ids(String song_ids, Integer list_id, BaseModel baseModel) throws Exception {
        Integer result = songListDao.updateSong_ids(song_ids, list_id);
        if (result <= 0) {
            throw new BusinessException(ErrorMessageEnum.SONG_IDS_UPDATE_ERROR);
        }
        baseModel.setResultCode(0);
        baseModel.setMessage("修改song_ids成功");
        baseModel.setData(result);
        return baseModel;
    }

    public BaseModel deleteSongList(Integer list_id, BaseModel baseModel) throws Exception {
        Integer result = songListDao.deleteSongList(list_id);
        if (result <= 0) {
            throw new BusinessException(ErrorMessageEnum.SONG_LIST_DELETE_ERROR);
        }
        baseModel.setResultCode(0);
        baseModel.setMessage("删除歌单成功");
        baseModel.setData(result);
        return baseModel;
    }
}

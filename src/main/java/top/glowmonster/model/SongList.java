package top.glowmonster.model;

import java.util.Date;

public class SongList {
    private Integer list_id;
    private String list_name;
    private String song_ids;
    private String list_image;
    private Date list_create_time;
    private Date list_update_time;
    private Integer list_version;
    private Integer is_deleted;

    public String getList_image() {
        return list_image;
    }

    public void setList_image(String list_image) {
        this.list_image = list_image;
    }

    public Integer getList_id() {
        return list_id;
    }

    public void setList_id(Integer list_id) {
        this.list_id = list_id;
    }

    public String getList_name() {
        return list_name;
    }

    public void setList_name(String list_name) {
        this.list_name = list_name;
    }

    public String getSong_ids() {
        return song_ids;
    }

    public void setSong_ids(String song_ids) {
        this.song_ids = song_ids;
    }

    public Date getList_create_time() {
        return list_create_time;
    }

    public void setList_create_time(Date list_create_time) {
        this.list_create_time = list_create_time;
    }

    public Date getList_update_time() {
        return list_update_time;
    }

    public void setList_update_time(Date list_update_time) {
        this.list_update_time = list_update_time;
    }

    public Integer getList_version() {
        return list_version;
    }

    public void setList_version(Integer list_version) {
        this.list_version = list_version;
    }

    public Integer getIs_deleted() {
        return is_deleted;
    }

    public void setIs_deleted(Integer is_deleted) {
        this.is_deleted = is_deleted;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("SongList{");
        sb.append("list_id=").append(list_id);
        sb.append(", list_name='").append(list_name).append('\'');
        sb.append(", song_ids='").append(song_ids).append('\'');
        sb.append(", list_image='").append(list_image).append('\'');
        sb.append(", list_create_time=").append(list_create_time);
        sb.append(", list_update_time=").append(list_update_time);
        sb.append(", list_version=").append(list_version);
        sb.append(", is_deleted=").append(is_deleted);
        sb.append('}');
        return sb.toString();
    }
}

package top.glowmonster.model;

import java.util.Date;

public class SongComment {
    private Integer comment_id;
    private Integer song_id;
    private String comment_text;
    private Date comment_create_time;
    private Date comment_update_time;
    private Integer comment_version;
    private Integer is_deleted;

    public Integer getComment_id() {
        return comment_id;
    }

    public void setComment_id(Integer comment_id) {
        this.comment_id = comment_id;
    }

    public Integer getSong_id() {
        return song_id;
    }

    public void setSong_id(Integer song_id) {
        this.song_id = song_id;
    }

    public String getComment_text() {
        return comment_text;
    }

    public void setComment_text(String comment_text) {
        this.comment_text = comment_text;
    }

    public Date getComment_create_time() {
        return comment_create_time;
    }

    public void setComment_create_time(Date comment_create_time) {
        this.comment_create_time = comment_create_time;
    }

    public Date getComment_update_time() {
        return comment_update_time;
    }

    public void setComment_update_time(Date comment_update_time) {
        this.comment_update_time = comment_update_time;
    }

    public Integer getComment_version() {
        return comment_version;
    }

    public void setComment_version(Integer comment_version) {
        this.comment_version = comment_version;
    }

    public Integer getIs_deleted() {
        return is_deleted;
    }

    public void setIs_deleted(Integer is_deleted) {
        this.is_deleted = is_deleted;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("SongComment{");
        sb.append("comment_id=").append(comment_id);
        sb.append(", song_id=").append(song_id);
        sb.append(", comment_text='").append(comment_text).append('\'');
        sb.append(", comment_create_time=").append(comment_create_time);
        sb.append(", comment_update_time=").append(comment_update_time);
        sb.append(", comment_version=").append(comment_version);
        sb.append(", is_deleted=").append(is_deleted);
        sb.append('}');
        return sb.toString();
    }
}

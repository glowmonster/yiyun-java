package top.glowmonster.model;

import java.util.Date;

public class Song {
    private Integer song_id;
    private String song_name;
    private String song_author;
    private String song_image;
    private String song_url;
    private Integer song_great;
    private Integer style_id;
    private Integer song_version;
    private Integer is_deleted;
    private Date song_create_time;
    private Date song_update_time;

    public Integer getSong_id() {
        return song_id;
    }

    public void setSong_id(Integer song_id) {
        this.song_id = song_id;
    }

    public String getSong_name() {
        return song_name;
    }

    public void setSong_name(String song_name) {
        this.song_name = song_name;
    }

    public String getSong_author() {
        return song_author;
    }

    public void setSong_author(String song_author) {
        this.song_author = song_author;
    }

    public String getSong_image() {
        return song_image;
    }

    public void setSong_image(String song_image) {
        this.song_image = song_image;
    }

    public String getSong_url() {
        return song_url;
    }

    public void setSong_url(String song_url) {
        this.song_url = song_url;
    }

    public Integer getSong_great() {
        return song_great;
    }

    public void setSong_great(Integer song_great) {
        this.song_great = song_great;
    }

    public Integer getStyle_id() {
        return style_id;
    }

    public void setStyle_id(Integer style_id) {
        this.style_id = style_id;
    }

    public Integer getSong_version() {
        return song_version;
    }

    public void setSong_version(Integer song_version) {
        this.song_version = song_version;
    }

    public Integer getIs_deleted() {
        return is_deleted;
    }

    public void setIs_deleted(Integer is_deleted) {
        this.is_deleted = is_deleted;
    }

    public Date getSong_create_time() {
        return song_create_time;
    }

    public void setSong_create_time(Date song_create_time) {
        this.song_create_time = song_create_time;
    }

    public Date getSong_update_time() {
        return song_update_time;
    }

    public void setSong_update_time(Date song_update_time) {
        this.song_update_time = song_update_time;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Song{");
        sb.append("song_id=").append(song_id);
        sb.append(", song_name='").append(song_name).append('\'');
        sb.append(", song_author='").append(song_author).append('\'');
        sb.append(", song_image='").append(song_image).append('\'');
        sb.append(", song_url='").append(song_url).append('\'');
        sb.append(", song_great=").append(song_great);
        sb.append(", style_id=").append(style_id);
        sb.append(", song_version=").append(song_version);
        sb.append(", is_deleted=").append(is_deleted);
        sb.append(", song_create_time=").append(song_create_time);
        sb.append(", song_update_time=").append(song_update_time);
        sb.append('}');
        return sb.toString();
    }
}

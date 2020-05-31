package top.glowmonster.model;

import java.util.Date;

public class Style {
    private Integer style_id;
    private String style_name;
    private Integer style_version;
    private Integer is_deleted;
    private Date style_create_time;
    private Date style_update_time;

    public Integer getStyle_id() {
        return style_id;
    }

    public void setStyle_id(Integer style_id) {
        this.style_id = style_id;
    }

    public String getStyle_name() {
        return style_name;
    }

    public void setStyle_name(String style_name) {
        this.style_name = style_name;
    }

    public Integer getStyle_version() {
        return style_version;
    }

    public void setStyle_version(Integer style_version) {
        this.style_version = style_version;
    }

    public Integer getIs_deleted() {
        return is_deleted;
    }

    public void setIs_deleted(Integer is_deleted) {
        this.is_deleted = is_deleted;
    }

    public Date getStyle_create_time() {
        return style_create_time;
    }

    public void setStyle_create_time(Date style_create_time) {
        this.style_create_time = style_create_time;
    }

    public Date getStyle_update_time() {
        return style_update_time;
    }

    public void setStyle_update_time(Date style_update_time) {
        this.style_update_time = style_update_time;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Style{");
        sb.append("style_id=").append(style_id);
        sb.append(", style_name='").append(style_name).append('\'');
        sb.append(", style_version=").append(style_version);
        sb.append(", is_deleted=").append(is_deleted);
        sb.append(", style_create_time=").append(style_create_time);
        sb.append(", style_update_time=").append(style_update_time);
        sb.append('}');
        return sb.toString();
    }
}

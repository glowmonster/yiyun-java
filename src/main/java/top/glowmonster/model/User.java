package top.glowmonster.model;

import java.util.Date;

public class User {
    private Integer user_id;
    private String user_account;
    private String user_avatar_url;
    private String user_name;
    private String user_password;
    private String user_describes;
    private Integer user_version;
    private Integer is_deleted;
    private Date user_create_time;
    private Date user_update_time;
    private String user_type;

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getUser_account() {
        return user_account;
    }

    public void setUser_account(String user_account) {
        this.user_account = user_account;
    }

    public String getUser_avatar_url() {
        return user_avatar_url;
    }

    public void setUser_avatar_url(String user_avatar_url) {
        this.user_avatar_url = user_avatar_url;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_password() {
        return user_password;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }

    public String getUser_describes() {
        return user_describes;
    }

    public void setUser_describes(String user_describes) {
        this.user_describes = user_describes;
    }

    public Integer getUser_version() {
        return user_version;
    }

    public void setUser_version(Integer user_version) {
        this.user_version = user_version;
    }

    public Integer getIs_deleted() {
        return is_deleted;
    }

    public void setIs_deleted(Integer is_deleted) {
        this.is_deleted = is_deleted;
    }

    public Date getUser_create_time() {
        return user_create_time;
    }

    public void setUser_create_time(Date user_create_time) {
        this.user_create_time = user_create_time;
    }

    public Date getUser_update_time() {
        return user_update_time;
    }

    public void setUser_update_time(Date user_update_time) {
        this.user_update_time = user_update_time;
    }

    public String getUser_type() {
        return user_type;
    }

    public void setUser_type(String user_type) {
        this.user_type = user_type;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("User{");
        sb.append("user_id=").append(user_id);
        sb.append(", user_account='").append(user_account).append('\'');
        sb.append(", user_avatar_url='").append(user_avatar_url).append('\'');
        sb.append(", user_name='").append(user_name).append('\'');
        sb.append(", user_password='").append(user_password).append('\'');
        sb.append(", user_describes='").append(user_describes).append('\'');
        sb.append(", user_version=").append(user_version);
        sb.append(", is_deleted=").append(is_deleted);
        sb.append(", user_create_time=").append(user_create_time);
        sb.append(", user_update_time=").append(user_update_time);
        sb.append(", user_type='").append(user_type).append('\'');
        sb.append('}');
        return sb.toString();
    }
}

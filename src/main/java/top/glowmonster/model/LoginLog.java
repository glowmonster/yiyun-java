package top.glowmonster.model;

import java.util.Date;

public class LoginLog {
    private Integer login_log_id;
    private String user_account;
    private Date login_time;
    private Integer state;
    private String request_ip;
    private Integer is_deleted;

    public Integer getLogin_log_id() {
        return login_log_id;
    }

    public void setLogin_log_id(Integer login_log_id) {
        this.login_log_id = login_log_id;
    }

    public String getUser_account() {
        return user_account;
    }

    public void setUser_account(String user_account) {
        this.user_account = user_account;
    }

    public Date getLogin_time() {
        return login_time;
    }

    public void setLogin_time(Date login_time) {
        this.login_time = login_time;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getRequest_ip() {
        return request_ip;
    }

    public void setRequest_ip(String request_ip) {
        this.request_ip = request_ip;
    }

    public Integer getIs_deleted() {
        return is_deleted;
    }

    public void setIs_deleted(Integer is_deleted) {
        this.is_deleted = is_deleted;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("LoginLog{");
        sb.append("login_log_id=").append(login_log_id);
        sb.append(", user_account='").append(user_account).append('\'');
        sb.append(", login_time=").append(login_time);
        sb.append(", state=").append(state);
        sb.append(", request_ip='").append(request_ip).append('\'');
        sb.append(", is_deleted=").append(is_deleted);
        sb.append('}');
        return sb.toString();
    }
}

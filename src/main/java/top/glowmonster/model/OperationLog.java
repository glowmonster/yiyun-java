package top.glowmonster.model;

import java.util.Date;

public class OperationLog {
    private Integer operation_log_id;
    private Date operation_time;
    private Integer user_id;
    private String user_account;
    private String user_name;
    private String operation_module;
    private String request_ip;
    private String request_method;
    private String operation_type;
    private Integer is_deleted;
    private String operation_describe;

    public String getOperation_describe() {
        return operation_describe;
    }

    public void setOperation_describe(String operation_describe) {
        this.operation_describe = operation_describe;
    }

    public Integer getOperation_log_id() {
        return operation_log_id;
    }

    public void setOperation_log_id(Integer operation_log_id) {
        this.operation_log_id = operation_log_id;
    }

    public Date getOperation_time() {
        return operation_time;
    }

    public void setOperation_time(Date operation_time) {
        this.operation_time = operation_time;
    }

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

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getOperation_module() {
        return operation_module;
    }

    public void setOperation_module(String operation_module) {
        this.operation_module = operation_module;
    }

    public String getRequest_ip() {
        return request_ip;
    }

    public void setRequest_ip(String request_ip) {
        this.request_ip = request_ip;
    }

    public String getRequest_method() {
        return request_method;
    }

    public void setRequest_method(String request_method) {
        this.request_method = request_method;
    }

    public String getOperation_type() {
        return operation_type;
    }

    public void setOperation_type(String operation_type) {
        this.operation_type = operation_type;
    }

    public Integer getIs_deleted() {
        return is_deleted;
    }

    public void setIs_deleted(Integer is_deleted) {
        this.is_deleted = is_deleted;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("OperationLog{");
        sb.append("operation_log_id=").append(operation_log_id);
        sb.append(", operation_time=").append(operation_time);
        sb.append(", user_id=").append(user_id);
        sb.append(", user_account='").append(user_account).append('\'');
        sb.append(", user_name='").append(user_name).append('\'');
        sb.append(", operation_module='").append(operation_module).append('\'');
        sb.append(", request_ip='").append(request_ip).append('\'');
        sb.append(", request_method='").append(request_method).append('\'');
        sb.append(", operation_type='").append(operation_type).append('\'');
        sb.append(", is_deleted=").append(is_deleted);
        sb.append(", operation_describe='").append(operation_describe).append('\'');
        sb.append('}');
        return sb.toString();
    }
}

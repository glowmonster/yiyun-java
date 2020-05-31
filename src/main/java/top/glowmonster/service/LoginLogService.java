package top.glowmonster.service;

import top.glowmonster.base.vo.BaseModel;
import top.glowmonster.model.LoginLog;

import java.util.Date;
import java.util.List;

public interface LoginLogService {
    Integer insertLoginLog(String user_account, Date login_time, Integer state, String request_ip, Integer is_deleted, BaseModel baseModel);

    BaseModel selectAllLoginLogByUserAccount(Integer curr_page, Integer page_size, String user_account, BaseModel baseModel);
}

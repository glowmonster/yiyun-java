package top.glowmonster.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.glowmonster.base.vo.BaseModel;
import top.glowmonster.dao.LoginLogDao;
import top.glowmonster.model.LoginLog;
import top.glowmonster.service.LoginLogService;

import java.util.Date;
import java.util.List;

@Service
public class LoginLogServiceImpl implements LoginLogService {

    @Autowired
    LoginLogDao loginLogDao;

    public Integer insertLoginLog(String user_account, Date login_time, Integer state, String request_ip, Integer is_deleted, BaseModel baseModel) {
        LoginLog loginLog = new LoginLog();
        loginLog.setUser_account(user_account);
        loginLog.setLogin_time(login_time);
        loginLog.setState(state);
        loginLog.setRequest_ip(request_ip);
        loginLog.setIs_deleted(is_deleted);
        Integer result = loginLogDao.insertLoginLog(loginLog);
        return result;
    }


    public BaseModel selectAllLoginLogByUserAccount(Integer curr_page, Integer page_size, String user_account, BaseModel baseModel) {
        PageHelper.startPage(curr_page, page_size);
        List<LoginLog> loginLogList = loginLogDao.selectAllLoginLogByUserAccount(user_account);
        PageInfo pageInfo = new PageInfo(loginLogList, 5);
        baseModel.setResultCode(0);
        baseModel.setMessage("可以根据用户名分页查询登陆日志成功");
        baseModel.setData(pageInfo);
        return baseModel;
    }
}

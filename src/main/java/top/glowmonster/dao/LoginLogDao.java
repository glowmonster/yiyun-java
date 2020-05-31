package top.glowmonster.dao;

import org.apache.ibatis.annotations.Param;
import top.glowmonster.model.LoginLog;

import java.util.List;

public interface LoginLogDao {

    Integer insertLoginLog(LoginLog loginLog);

    // 分页查询登陆日志
    List<LoginLog> selectAllLoginLogByUserAccount(@Param("user_account") String user_account);
}

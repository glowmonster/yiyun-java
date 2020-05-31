package top.glowmonster.dao;

import org.apache.ibatis.annotations.Param;
import top.glowmonster.model.User;

import java.util.List;

public interface UserDao {
    User selectUserByAccountAndPassword(@Param("account") String account, @Param("password") String password);

    // 添加用户
    Integer insertUser(User user);

    // 修改用户
    Integer updateUser(@Param("user_id") Integer user_id, @Param("user_name") String user_name, @Param("user_describes") String user_describes);
}

package top.glowmonster.service;

import top.glowmonster.base.vo.BaseModel;
import top.glowmonster.model.User;

public interface UserService {
    BaseModel selectUserByAccountAndPassword(String account, String password, BaseModel baseModel) throws Exception;


    // 新增用户
    BaseModel insertUser(User user, BaseModel baseModel) throws Exception;

    // 修改用户
    BaseModel updateUser(Integer user_id, String user_name, String user_describes, BaseModel baseModel) throws Exception;

}

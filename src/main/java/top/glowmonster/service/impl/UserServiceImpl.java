package top.glowmonster.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.glowmonster.base.em.ErrorMessageEnum;
import top.glowmonster.base.exception.BusinessException;
import top.glowmonster.base.vo.BaseModel;
import top.glowmonster.dao.UserDao;
import top.glowmonster.model.User;
import top.glowmonster.service.UserService;


@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserDao userDao;

    public BaseModel selectUserByAccountAndPassword(String account, String password, BaseModel baseModel) throws Exception {
        User user = userDao.selectUserByAccountAndPassword(account, password);
        if (user == null) {
            throw new BusinessException(ErrorMessageEnum.LOGIN_ERROR);
        }
        baseModel.setResultCode(0);
        baseModel.setMessage("根据用户名和密码查询用户成功");
        baseModel.setData(user);
        return baseModel;
    }

    public BaseModel insertUser(User user, BaseModel baseModel) throws Exception {
        Integer result = userDao.insertUser(user);
        if (result <= 0) {
            throw new BusinessException(ErrorMessageEnum.REGISTER_ERROR);
        }
        baseModel.setResultCode(0);
        baseModel.setMessage("注册成功");
        baseModel.setData(result);
        return baseModel;
    }

    public BaseModel updateUser(Integer user_id, String user_name, String user_describes, BaseModel baseModel) throws Exception {
        Integer result = userDao.updateUser(user_id, user_name, user_describes);
        if (result <= 0) {
            throw new BusinessException(ErrorMessageEnum.USER_UPDATE_ERROR);
        }
        baseModel.setResultCode(0);
        baseModel.setMessage("用户更新成功");
        return baseModel;
    }
}

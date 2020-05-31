package top.glowmonster.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.glowmonster.base.vo.BaseModel;
import top.glowmonster.dao.OperationLogDao;
import top.glowmonster.model.OperationLog;
import top.glowmonster.service.OperationLogService;

import java.util.List;

@Service
public class OperationLogServiceImpl implements OperationLogService {

    @Autowired
    OperationLogDao operationLogDao;

    public BaseModel selectAllOperationLog(Integer curr_page, Integer page_size, String user_account, BaseModel baseModel) {
        PageHelper.startPage(curr_page, page_size);
        List<OperationLog> operationLogList = operationLogDao.selectAllOperationLog(user_account);
        PageInfo pageInfo = new PageInfo(operationLogList, 5);
        baseModel.setResultCode(0);
        baseModel.setMessage("分页查询所有操作日志");
        baseModel.setData(pageInfo);
        return baseModel;
    }
}

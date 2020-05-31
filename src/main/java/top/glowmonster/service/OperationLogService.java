package top.glowmonster.service;

import top.glowmonster.base.vo.BaseModel;

public interface OperationLogService {
    // 分页查询操作日志
    BaseModel selectAllOperationLog(Integer curr_page, Integer page_size, String user_account, BaseModel baseModel);
}

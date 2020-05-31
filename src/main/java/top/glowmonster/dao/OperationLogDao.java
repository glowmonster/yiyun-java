package top.glowmonster.dao;

import org.apache.ibatis.annotations.Param;
import top.glowmonster.model.OperationLog;

import java.util.List;

public interface OperationLogDao {

    // 插入操作日志
    Integer insertOperationLog(OperationLog operationLog);

    // 查询所有操作日志
    List<OperationLog> selectAllOperationLog(@Param("user_account") String user_account);

}

package top.glowmonster.base.aop;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import top.glowmonster.base.em.ErrorMessageEnum;
import top.glowmonster.base.exception.BusinessException;
import top.glowmonster.base.vo.BaseModel;
import top.glowmonster.dao.OperationLogDao;
import top.glowmonster.model.OperationLog;
import top.glowmonster.model.User;
import top.glowmonster.service.LoginLogService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.Date;

@Aspect
@Component
public class OperationAspect {
    protected BaseModel baseModel;
    protected HttpServletRequest httpServletRequest;
    protected HttpSession httpSession;
    protected Logger logger = Logger.getLogger(this.getClass());
    protected Object[] params;

    @Autowired
    LoginLogService loginLogService;

    @Autowired
    OperationLogDao operationLogDao;

    @Pointcut("execution(* top.glowmonster.controller..*.*(..))")
    public void pointcut() {}

    @Before("pointcut() && @annotation(operation)")
    public void doBefore(JoinPoint joinPoint, AopOperation operation) throws Exception {
        httpServletRequest = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        httpSession = httpServletRequest.getSession();
        params = joinPoint.getArgs();
        for (int temp = 0; temp < params.length; temp++) {
            System.out.println("第" + (temp + 1) + "个参数:" + params[temp]);
            if (params[temp].getClass() == BaseModel.class) {
                baseModel = (BaseModel) params[temp];
            }
        }
    }

    @After("pointcut() && @annotation(operation)")
    public void doAfter(JoinPoint joinPoint, AopOperation operation) throws Exception {
        User user = (User) httpSession.getAttribute("currentUser");
        if (user == null) {
            throw new BusinessException(ErrorMessageEnum.LOGIN_TIMEOUT);
        }
        Timestamp timestamp = new Timestamp(new Date().getTime());
        OperationLog operationLog = new OperationLog();
        operationLog.setIs_deleted(0);
        operationLog.setOperation_module(operation.module().getDescribe());
        operationLog.setOperation_time(timestamp);
        operationLog.setRequest_ip(httpServletRequest.getRemoteAddr());
        operationLog.setOperation_describe(operation.desc());
        operationLog.setOperation_type(operation.type());
        operationLog.setRequest_method(joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature());
        operationLog.setUser_id(user.getUser_id());
        operationLog.setUser_name(user.getUser_name());
        operationLog.setUser_account(user.getUser_account());
        Integer result = operationLogDao.insertOperationLog(operationLog);
        if (result <= 0) {
            throw new BusinessException(ErrorMessageEnum.OPERATION_LOG_INSERT_ERROR);
        }
        if (operation.desc().equals("登陆")) {
            result = 0;
            if (this.baseModel.getResultCode() == null) {
                this.baseModel.setResultCode(1);
            }
            if (this.baseModel.getResultCode() == 0) {
                result = loginLogService.insertLoginLog(String.valueOf(params[0]), timestamp, 0, httpServletRequest.getRemoteAddr(), 0, baseModel);
            } else {
                result = loginLogService.insertLoginLog(String.valueOf(params[0]), timestamp, 1, httpServletRequest.getRemoteAddr(), 0, baseModel);
            }
            if (result <= 0) {
                throw new BusinessException(ErrorMessageEnum.LOGIN_LOG_INSERT_ERROR);
            }
        }
    }

}

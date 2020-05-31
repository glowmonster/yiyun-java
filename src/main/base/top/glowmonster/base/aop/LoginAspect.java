package top.glowmonster.base.aop;

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
import top.glowmonster.dao.LoginLogDao;
import top.glowmonster.model.LoginLog;
import top.glowmonster.model.User;
import top.glowmonster.service.LoginLogService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.Date;

@Aspect
@Component
public class LoginAspect {
    protected BaseModel baseModel;
    protected HttpServletRequest httpServletRequest;
    protected HttpSession httpSession;
    protected Object[] params;

    @Autowired
    LoginLogService loginLogService;

    @Pointcut("execution(* top.glowmonster.controller..*.*(..))")
    public void pointcut() {}
}

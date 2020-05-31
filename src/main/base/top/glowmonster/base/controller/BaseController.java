package top.glowmonster.base.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import top.glowmonster.base.em.ErrorMessageEnum;
import top.glowmonster.base.exception.BusinessException;

@Controller
@ResponseBody
public class BaseController {

    @ExceptionHandler(BusinessException.class)
    public BusinessException businessException(BusinessException be) {
        return be;
    }

    @ExceptionHandler(Exception.class)
    public BusinessException businessException(Exception e) {
        BusinessException businessException = new BusinessException(ErrorMessageEnum.SYSTEM_ERROR);
        return businessException;
    }
}

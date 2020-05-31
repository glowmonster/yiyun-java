package top.glowmonster.base.exception;

import top.glowmonster.base.em.ErrorMessageEnum;

public class BusinessException extends RuntimeException {
    private Integer resultCode;
    private String message;

    public BusinessException(ErrorMessageEnum errorMessageEnum) {
        this.resultCode = errorMessageEnum.getCode();
        this.message = errorMessageEnum.getMessage();
    }

    public Integer getResultCode() {
        return resultCode;
    }

    public void setResultCode(Integer resultCode) {
        this.resultCode = resultCode;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

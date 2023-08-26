package com.study.platform.base.exception;

import com.study.platform.base.enums.ResultCode;
import lombok.Getter;
import lombok.Setter;

/**
 * @ClassName: BusinessException
 * @Description: 业务处理异常
 * @author GitEgg
 * @date
 */
@Getter
@Setter
public class BusinessException extends RuntimeException {

    private long code;

    private String msg;

    public BusinessException() {
        this.code = ResultCode.FAILED.getCode();
        this.msg = ResultCode.FAILED.getMessage();
    }

    public BusinessException(String message) {
        this.code = ResultCode.FAILED.getCode();
        this.msg = message;
    }

    public BusinessException(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public BusinessException(Throwable cause) {
        super(cause);
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }

}
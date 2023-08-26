package com.study.platform.base.exception;

import com.study.platform.base.enums.ResultCode;
import lombok.Getter;

/**
 * @ClassName: SystemException
 * @Description: 系统处理异常
 * @author GitEgg
 * @date
 */
@Getter
public class SystemException extends RuntimeException {

    private long code;

    private String msg;

    public SystemException() {
        this.code = ResultCode.ERROR.getCode();
        this.msg = ResultCode.ERROR.getMessage();
    }

    public SystemException(String message) {
        this.code = ResultCode.ERROR.getCode();
        this.msg = message;
    }

    public SystemException(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public SystemException(Throwable cause) {
        super(cause);
    }

    public SystemException(String message, Throwable cause) {
        super(message, cause);
    }

}
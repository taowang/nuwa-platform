package com.study.platform.base.result;

import com.study.platform.base.enums.ResultCode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author GitEgg
 * @ClassName: Result
 * @Description: 自定义通用响应类
 * @date 2020年09月19日 下午9:24:50
 */
@ApiModel(description = "通用响应类")
@Getter
@ToString
@NoArgsConstructor
public class Result<T> {

    @ApiModelProperty(value = "是否成功", required = true)
    private boolean success;

    @ApiModelProperty(value = "响应代码", required = true)
    private long code;

    @ApiModelProperty(value = "提示信息", required = true)
    private String msg;

    @ApiModelProperty(value = "响应数据")
    private T data;

    /**
     * @param code
     * @param data
     * @param msg
     */
    private Result(long code, T data, String msg) {
        this.success = ResultCode.SUCCESS.getCode() == code;
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    /**
     * @param ResultCode
     */
    private Result(ResultCode ResultCode) {
        this(ResultCode.getCode(), null, ResultCode.getMessage());
    }

    /**
     * @param ResultCode
     * @param msg
     */
    private Result(ResultCode ResultCode, String msg) {
        this(ResultCode, null, msg);
    }

    /**
     * @param ResultCode
     * @param data
     */
    private Result(ResultCode ResultCode, T data) {
        this(ResultCode, data, ResultCode.getMessage());
    }

    /**
     * @param ResultCode
     * @param data
     * @param msg
     */
    private Result(ResultCode ResultCode, T data, String msg) {
        this(ResultCode.getCode(), data, msg);
    }

    /**
     * @param data 数据
     * @param <T>  T 响应数据
     * @
     */
    public static <T> Result<T> data(T data) {
        return data(data, ResultCode.SUCCESS.getMessage());
    }

    /**
     * @param data 数据
     * @param msg  消息
     * @param <T>  T 响应数据
     * @
     */
    public static <T> Result<T> data(T data, String msg) {
        return data(ResultCode.SUCCESS.getCode(), data, msg);
    }

    /**
     * @param code 状态码
     * @param data 数据
     * @param msg  消息
     * @param <T>  T 响应数据
     * @
     */
    public static <T> Result<T> data(long code, T data, String msg) {
        return new Result<>(code, data, msg);
    }

    /**
     * 返回Result
     *
     * @param
     * @param <T> T 响应数据
     * @返回Result
     */
    public static <T> Result<T> success() {
        return new Result<>(ResultCode.SUCCESS);
    }

    /**
     * 返回Result
     *
     * @param msg 消息
     * @param <T> T 响应数据
     * @返回Result
     */
    public static <T> Result<T> success(String msg) {
        return new Result<>(ResultCode.SUCCESS, msg);
    }

    /**
     * 返回Result
     *
     * @param
     * @param <T> T 响应数据
     * @返回Result
     */
    public static <T> Result<T> success(ResultCode ResultCode) {
        return new Result<>(ResultCode);
    }

    /**
     * 返回Result
     *
     * @param
     * @param msg 提示信息
     * @param <T> T 响应数据
     * @返回Result
     */
    public static <T> Result<T> success(ResultCode ResultCode, String msg) {
        return new Result<>(ResultCode, msg);
    }

    /**
     * 返回Result
     *
     * @param <T> T 响应数据
     * @返回Result
     */
    public static <T> Result<T> error() {
        return new Result<>(ResultCode.ERROR, ResultCode.ERROR.getMessage());
    }

    /**
     * 返回Result
     *
     * @param msg 消息
     * @param <T> T 响应数据
     * @返回Result
     */
    public static <T> Result<T> error(String msg) {
        return new Result<>(ResultCode.ERROR, msg);
    }


    /**
     * 返回Result
     *
     * @param code 状态码
     * @param msg  消息
     * @param <T>  T 响应数据
     * @返回Result
     */
    public static <T> Result<T> error(long code, String msg) {
        return new Result<>(code, null, msg);
    }

    /**
     * 返回Result
     *
     * @param
     * @param <T> T 响应数据
     * @返回Result
     */
    public static <T> Result<T> error(ResultCode ResultCode) {
        return new Result<>(ResultCode);
    }

    /**
     * 返回Result
     *
     * @param
     * @param msg 提示信息
     * @param <T> T 响应数据
     * @返回Result
     */
    public static <T> Result<T> error(ResultCode ResultCode, String msg) {
        return new Result<>(ResultCode, msg);
    }

    /**
     * @param <T>
     * @param flag
     * @return
     */
    public static <T> Result<T> result(boolean flag) {
        return flag ? Result.success("操作成功") : Result.error("操作失败");
    }

    /**
     * 未授权返回结果
     */
    public static <T> Result<T> forbidden(T data) {
        return new Result<T>(ResultCode.NO_PERMISSION.getCode(), data, ResultCode.NO_PERMISSION.getMessage());
    }

    /**
     * 未登录返回结果
     */
    public static <T> Result<T> unauthorized(T data) {
        return new Result<T>(ResultCode.UNAUTHORIZED.getCode(), data, ResultCode.UNAUTHORIZED.getMessage());
    }

    /**
     * 参数验证失败返回结果6787
     */
    public static <T> Result<T> validateFailed() {
        return error(ResultCode.PARAM_VALID_ERROR);
    }

    /**
     * 参数验证失败返回结果
     *
     * @param message 提示信息
     */
    public static <T> Result<T> validateFailed(String message) {
        return new Result<T>(ResultCode.PARAM_VALID_ERROR.getCode(), null, message);
    }

}
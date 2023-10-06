package com.study.platform.base.result;

import com.study.platform.base.enums.ResultCode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @ClassName: PageResult
 * @Description: 通用分页返回  ，弃用，统一使用Result
 * @author GitEgg
 * @date
 * @param <T>
 */
@Data
public class PageResult<T> {

    @ApiModelProperty(value = "是否成功", required = true)
    private boolean success;

    @ApiModelProperty(value ="响应代码", required = true)
    private long code;

    @ApiModelProperty(value ="提示信息", required = true)
    private String msg;

    @ApiModelProperty(value ="总数量", required = true)
    private long count;

    @ApiModelProperty(value ="分页数据")
    private List<T> data;

    public PageResult(long total, List<T> rows) {
        this.count = total;
        this.data = rows;
        this.code = ResultCode.SUCCESS.getCode();
        this.msg = ResultCode.SUCCESS.getMessage();
    }

    /**
     * 组装数据返回
     *
     * @param count  消息
     * @param data 数据
     * @param <T>  T 响应数据
     * @
     */
    public static <T> PageResult<T> data(long count, List<T> data) {
        return new PageResult(count, data);
    }
}

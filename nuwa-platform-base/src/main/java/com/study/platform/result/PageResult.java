package com.study.platform.result;

import com.study.platform.enums.ResultCode;
import io.swagger.v3.oas.annotations.media.Schema;
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

    @Schema(description = "是否成功")
    private boolean success;

    @Schema(description = "响应代码")
    private long code;

    @Schema(description = "提示信息")
    private String msg;

    @Schema(description = "总数量")
    private long count;

    @Schema(description = "分页数据")
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

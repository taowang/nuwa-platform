package com.study.nuwa.platform.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author nuwa
 * @date 2020/12/14
 **/
@Data
public class BaseEntity implements Serializable {

    @Schema(description = "租户id")
    @TableField(value = "tenant_id")
    private Long tenantId;

    @Schema(description = "创建时间")
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    @Schema(description = "创建者")
    @TableField(value = "creator", fill = FieldFill.INSERT)
    private Long creator;

    @Schema(description = "更新时间")
    @TableField(value = "update_time", fill = FieldFill.UPDATE)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;

    @Schema(description = "更新者")
    @TableField(value = "operator", fill = FieldFill.UPDATE)
    private Long operator;

    @Schema(description = "1:删除 0:不删除")
    @TableField("del_flag")
    @TableLogic
    private Integer delFlag;

}

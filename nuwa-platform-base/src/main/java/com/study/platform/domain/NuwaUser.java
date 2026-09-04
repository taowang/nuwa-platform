
package com.study.platform.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author GitEgg
 */
@Data
@Schema(description = "用户信息")
public class NuwaUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "主键")
    private Long id;

    @Schema(description = "客户端id")
    private String clientId;

    @Schema(description = "租户id")
    private String tenantId;

    @Schema(description = "第三方认证id")
    private String oauthId;

    @Schema(description = "账号")
    private String account;

    @Schema(description = "昵称")
    private String nickname;

    @Schema(description = "姓名")
    private String realName;

    @Schema(description = "1 : 男，0 : 女")
    private String gender;

    @Schema(description = "邮箱")
    private String email;

    @Schema(description = "电话")
    private String mobile;

    @Schema(description = "密码")
    private String password;

    @Schema(description = "'0'禁用,'1' 启用, '2' 密码过期或初次未修改")
    private String status;

    @Schema(description = "头像")
    private String avatar;

    @Schema(description = "省")
    private String province;

    @Schema(description = "市")
    private String city;

    @Schema(description = "区")
    private String area;

    @Schema(description = "街道详细地址")
    private String street;

    @Schema(description = "备注")
    private String comments;

    @Schema(description = "角色id")
    private Long roleId;

    @Schema(description = "角色名称")
    private String roleName;

    @Schema(description = "机构id")
    private Long organizationId;

    @Schema(description = "机构名称")
    private String organizationName;

    @Schema(description = "角色id集合")
    private String roleIds;

    @Schema(description = "角色标识集合")
    private String roleKeys;

    @Schema(description = "角色名称集合")
    private String roleNames;

    @Schema(description = "角色id列表")
    private List<String> roleIdList;

    @Schema(description = "角色key列表")
    private List<String> roleKeyList;

    @Schema(description = "角色数据权限类型列表")
    private List<String> dataPermissionTypeList;

    @Schema(description = "数据权限机构id集合")
    private String organizationIds;

    @Schema(description = "数据权限机构名称集合")
    private String organizationNames;

    @Schema(description = "机构id列表")
    private List<String> organizationIdList;

    @Schema(description = "机构名称列表")
    private List<String> organizationNameList;

    @Schema(description = "资源列表字符串")
    private List<String> resourceKeyList;

    @Schema(description = "资源请求列表")
    private List<String> resourceUrlList;

    @Schema(description = "数据权限")
    private String dataPermission;

}

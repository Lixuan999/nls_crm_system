package com.ckf.crm.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.List;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
/**
 * <p>
 *
 * </p>
 *
 * @author xuan
 * @since 2020-03-23
 */

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("permission")
public class Permission extends BaseEntity implements Serializable {


    @TableId(type = IdType.AUTO)
    private Integer permissionId;

    @NotNull(message = "权限名称不能为空！")
    private String permName;

    @NotNull(message = "权限不能为空！")
    private String permission;

    @NotNull(message = "url不能为空！")
    private String url;

    /**
     * 一个角色有多个权限
     */
    @TableField(exist = false)
    private List<Role> roleList;


}

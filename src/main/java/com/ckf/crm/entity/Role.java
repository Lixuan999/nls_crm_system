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
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.stereotype.Component;

/**
 * <p>
 *
 * </p>
 *
 * @author 安详的苦丁茶
 * @since 2020-03-23
 */

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("role")
public class Role extends BaseEntity {


    @TableId(type = IdType.AUTO)
    private Integer rolId;

    private String rName;

    /**
     * 角色 -- 权限关系：多对多关系
     */
    @TableField(exist = false)
    private List<Permission> permissionList;


    /**
     * 角色 -- 部门关系：多对多关系
     */
    @TableField(exist = false)
    private List<Department> departmentList;


    @Override
    public String toString() {
        return "Role=" + rolId +
                ",  " + rName + '\'' +
                ", \n" + permissionList +
                ", \n" + departmentList +
                '}';
    }
}

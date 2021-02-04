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

/**
 * @author xuan
 * @since 2020-03-23
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("department")
public class Department extends BaseEntity implements Serializable{


    @TableId(type = IdType.AUTO)
    private Integer departmentId;

    private String dName;

    private String dManager;

    private Integer dPopulation;

    private String dRemark;

    /**
     * 一个部门属于多个角色
     */
    @TableField(exist = false)
    private List<Role> roleList;


}

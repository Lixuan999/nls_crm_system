package com.ckf.crm.entity.customerEntity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.List;

import com.baomidou.mybatisplus.annotation.TableName;
import com.ckf.crm.entity.BaseEntity;
import com.ckf.crm.entity.Employee;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * <p>
 *
 * </p>
 *
 * @author 安详的苦丁茶
 * @since 2020-04-05
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("contact")
public class Contact extends BaseEntity {


    @TableId(type = IdType.AUTO)
    private Integer contactId;

    private String way;

    private String content;


    @TableField(exist = false)
    private List<Employee> employeeList;


    @TableField(exist = false)
    private List<Customer> customerList;
}

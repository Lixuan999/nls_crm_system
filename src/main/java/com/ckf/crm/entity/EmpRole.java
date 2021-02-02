package com.ckf.crm.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("emp_role")
public class EmpRole extends BaseEntity implements Serializable {

    @TableId
    private Integer empId;

    private Integer roleId;

}

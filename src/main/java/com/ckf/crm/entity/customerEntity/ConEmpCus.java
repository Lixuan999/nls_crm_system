package com.ckf.crm.entity.customerEntity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.ckf.crm.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author xuan
 * @version 1.0
 * @date 2020/4/6 0:14
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("con_emp_cus")
public class ConEmpCus extends BaseEntity {

    private Integer conId;

    private Integer empId;

    private Integer cusId;
}

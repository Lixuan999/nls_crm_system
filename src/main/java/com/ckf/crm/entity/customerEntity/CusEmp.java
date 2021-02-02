package com.ckf.crm.entity.customerEntity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.ckf.crm.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author xuan
 * @version 1.0
 * @date 2020/4/5 22:18
 */

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("cus_emp")
public class CusEmp extends BaseEntity {

    private Integer cusId;

    private Integer empId;
}

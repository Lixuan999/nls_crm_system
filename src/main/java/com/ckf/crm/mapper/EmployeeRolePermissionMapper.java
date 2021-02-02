package com.ckf.crm.mapper;

import com.ckf.crm.entity.Employee;
import org.springframework.stereotype.Repository;

/**
 * @author xuan
 * @version 1.0
 * @date 2020/3/23 21:14
 */

@Repository
public interface EmployeeRolePermissionMapper {

    /**
     * 根据用户查询 授权
     * 辅助类查询
     *
     * @param userName
     * @return
     */
    Employee selectByEmployeeName(String userName);
}

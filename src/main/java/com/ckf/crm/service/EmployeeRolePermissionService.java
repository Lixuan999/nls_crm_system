package com.ckf.crm.service;

import com.ckf.crm.entity.Employee;

/**
 * @author 安详的苦丁茶
 * @version 1.0
 * @date 2020/3/23 21:15
 */
public interface EmployeeRolePermissionService {

    /**
     * 根据用户查询 授权
     * 辅助类查询
     *
     * @param userName
     * @return
     */
    Employee selectByEmployeeName(String userName);
}

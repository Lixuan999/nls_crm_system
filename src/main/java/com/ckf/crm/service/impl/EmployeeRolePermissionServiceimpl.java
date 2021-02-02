package com.ckf.crm.service.impl;

import com.ckf.crm.entity.Employee;
import com.ckf.crm.mapper.EmployeeRolePermissionMapper;
import com.ckf.crm.service.EmployeeRolePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author 安详的苦丁茶
 * @version 1.0
 * @date 2020/3/23 21:15
 */

@Service
@Transactional
public class EmployeeRolePermissionServiceimpl implements EmployeeRolePermissionService {

    @Autowired
    private EmployeeRolePermissionMapper employeeRolePermissionMapper;


    @Override
    public Employee selectByEmployeeName(String userName) {
        return employeeRolePermissionMapper.selectByEmployeeName(userName);
    }
}

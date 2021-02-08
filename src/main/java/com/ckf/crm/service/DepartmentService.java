package com.ckf.crm.service;

import com.ckf.crm.entity.Department;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ckf.crm.entity.Role;


import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author xuan
 * @since 2021-03-23
 */
public interface DepartmentService extends IService<Department> {

    /**
     * 全查询部门信息
     *
     * @return
     */
    List<Department> selectDepartmentAll();

    /**
     * 条件查询部门
     * @param department
     * @return
     */
    public Department queryRoleInfo(Department department);

    /**
     * 添加部门信息
     *
     * @param department
     * @param roleId
     * @return
     */
    Integer addDepartment(Department department, Integer roleId);


    /**
     * 添加部门和角色关系信息
     * @param role
     * @param departmentId
     * @return
     */
    Integer addDepRole(Role role, Integer departmentId);

    /**
     * 修改部门信息
     *
     * @param department
     * @param roleId
     * @return
     */
    Integer updateDepartment(Department department, Integer roleId);


    /**
     * 模糊查询部门领导
     *
     * @param dName
     * @return
     */
    List<Department> selectDepartmentName(String dName);

}

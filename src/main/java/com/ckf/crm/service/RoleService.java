package com.ckf.crm.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ckf.crm.entity.DeptRole;
import com.ckf.crm.entity.EmpRole;
import com.ckf.crm.entity.Employee;
import com.ckf.crm.entity.Role;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author xuan
 * @since 2021-03-23
 */
public interface RoleService extends IService<Role> {

    /**
     * 查询员工角色
     *
     * @param employeeId
     * @return
     */
    public EmpRole queryUserRoleInfo(Integer employeeId);

    /**
     * 查询部门角色
     * @param departmentId
     * @return
     */
    public DeptRole queryDepRoleInfo(Integer departmentId);

    /**
     * 添加角色信息
     *
     * @param role
     * @param permId
     * @return
     */
    Integer addRole(Role role, Integer permId);


    /**
     * 修改角色信息
     *
     * @param permId
     * @return
     */
    Integer updateRole(Role role, Integer permId);


    /**
     * 分页全查询角色信息
     *
     * @return
     */
    IPage<Role> selectRoleList(Page<Role> page);

    /**
     * 模糊查询
     *
     * @param rName
     * @return
     */
    List<Role> selectRoleNameLike(String rName);



    /**
     * 全查询角色和权限
     *
     * @return
     */

    List<Role> selectRolePermissionAll();

   /* *//**
     * 辅助类全查询角色和权限
     *
     * @return
     *//*
    List<Role> selectRolePermissionAllVo();*/

}

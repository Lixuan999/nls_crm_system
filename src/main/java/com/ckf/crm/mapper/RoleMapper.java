package com.ckf.crm.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ckf.crm.entity.Employee;
import com.ckf.crm.entity.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author PIGS
 * @since 2021-03-23
 */

@Repository
public interface RoleMapper extends BaseMapper<Role> {


    /**
     * 查询管理员
     *
     * @return
     */
    List<Employee> selectAdmin();

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
     * 查询角色与权权限
     *
     * @return
     */
    List<Role> selectRolePermissionAll();


   /* *//**
     * 辅助类全查询角色和权限
     *
     * @return
     *//*
    List<Role> selectRolePermissionAllVo();
*/
}

package com.ckf.crm.service.impl;

import com.baomidou.mybatisplus.core.conditions.AbstractWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ckf.crm.entity.DeptRole;
import com.ckf.crm.entity.EmpRole;
import com.ckf.crm.entity.Role;
import com.ckf.crm.entity.RolePerm;
import com.ckf.crm.mapper.DeptRoleMapper;
import com.ckf.crm.mapper.EmpRoleMapper;
import com.ckf.crm.mapper.RoleMapper;
import com.ckf.crm.mapper.RolePermMapper;
import com.ckf.crm.service.RoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ckf.crm.utils.RedisUtil;
import com.ckf.crm.utils.TimeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xuan
 * @since 2021-03-23
 */
@Service
@Transactional
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {


    private Logger logger = LoggerFactory.getLogger(RoleServiceImpl.class);

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private RolePermMapper rolePermMapper;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private EmpRoleMapper empRoleMapper;

    @Autowired
    private DeptRoleMapper deptRoleMapper;
    /**
     * 条件查员工查询角色信息
     * @param employeeId
     * @return
     */
    @Override
    public EmpRole queryUserRoleInfo(Integer employeeId) {
        /**
         * redis中数据不存在就进入mysql中查询
         */
        if (employeeId != null) {
            AbstractWrapper wrapper = new QueryWrapper();
            wrapper.eq("emp_id", employeeId);
            EmpRole selectById = empRoleMapper.selectOne(wrapper);
            if (selectById != null) {
                redisUtil.set("empRole:" + selectById.getEmpId(), selectById);
                logger.info("selectById={}", selectById);
                return selectById;
            }
        }

        return null;
    }

    /**
     * 条件查询部门角色信息
     * @param departmentId
     * @return
     */
    @Override
    public DeptRole queryDepRoleInfo(Integer departmentId) {
        /**
         * redis中数据不存在就进入mysql中查询
         */
        if (departmentId != null) {
            AbstractWrapper wrapper = new QueryWrapper();
            wrapper.eq("dep_id", departmentId);
            DeptRole selectById = deptRoleMapper.selectOne(wrapper);
            if (selectById != null) {
                redisUtil.set("deptRole:" + selectById.getDepId(), selectById);
                logger.info("selectById={}", selectById);
                return selectById;
            }
        }
        return null;
    }

    /**
     * 添加角色
     *
     * @param role
     * @param permId
     * @return
     */
    @Override
    public Integer addRole(Role role, Integer permId) {
        System.out.println("-------------进入添加角色service实现层---------------");

        int result = roleMapper.insert(role);

        System.out.println("role数据--" + role);
        logger.info("permId={}" + permId);

        int roleId = role.getRolId();
        RolePerm rolePerm = new RolePerm(roleId, permId);

        rolePerm.setCreateTime(TimeUtils.dateTime());
        rolePerm.setUpdateTime(TimeUtils.dateTime());
        rolePerm.setIsDel(0);

        rolePermMapper.insert(rolePerm);
        return result;
    }

    /**
     * 修改角色信息
     *
     * @param role
     * @param permId
     * @return
     */
    @Override
    public Integer updateRole(Role role, Integer permId) {
        System.out.println("---------------进入修改角色service实现层----------------");

        System.out.println("role数据--" + role);
        System.out.println("permId--" + permId);

        role.setCreateTime(role.getCreateTime());
        role.setUpdateTime(TimeUtils.dateTime());

        int result = roleMapper.updateById(role);

        RolePerm rolePerm = new RolePerm(role.getRolId(), permId);

        rolePerm.setUpdateTime(TimeUtils.dateTime());

        AbstractWrapper wrapper = new QueryWrapper();

        //复合主键表 角色的id数据库字段
        wrapper.eq("role_id", role.getRolId());

        System.out.println("role.getRId()--" + role.getRolId());

        rolePermMapper.update(rolePerm, wrapper);
        return result;
    }

    /**
     * 分页查询角色信息
     *
     * @param page
     * @return
     */
    @Override
    public IPage<Role> selectRoleList(Page<Role> page) {
        return roleMapper.selectRoleList(page);
    }


    /**
     * 模糊查询角色名称
     *
     * @param rName
     * @return
     */
    @Override
    public List<Role> selectRoleNameLike(String rName) {
        return roleMapper.selectRoleNameLike(rName);
    }

    /**
     * 全查询加色和权限
     *
     * @return
     */
    @Override
    public List<Role> selectRolePermissionAll() {
        return roleMapper.selectRolePermissionAll();
    }

    /**
     * 辅助类 全查询加色和权限
     *
     * @return
     */
    @Override
    public List<Role> selectRolePermissionAllVo() {
        return roleMapper.selectRolePermissionAllVo();
    }
}

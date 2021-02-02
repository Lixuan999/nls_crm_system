package com.ckf.crm.service.impl;

import com.baomidou.mybatisplus.core.conditions.AbstractWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ckf.crm.entity.Department;
import com.ckf.crm.entity.DeptRole;
import com.ckf.crm.entity.Employee;
import com.ckf.crm.mapper.DepartmentMapper;
import com.ckf.crm.mapper.DeptRoleMapper;
import com.ckf.crm.service.DepartmentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
 * @author 安详的苦丁茶
 * @since 2020-03-23
 */
@Service
@Transactional
public class DepartmentServiceImpl extends ServiceImpl<DepartmentMapper, Department> implements DepartmentService {

    private Logger logger = LoggerFactory.getLogger(RoleServiceImpl.class);


    @Autowired
    private DepartmentMapper departmentMapper;

    @Autowired
    private DeptRoleMapper deptRoleMapper;

    /**
     * 全查询部门信息
     *
     * @return
     */
    @Override
    public List<Department> selectDepartmentAll() {
        return departmentMapper.selectDepartmentAll();
    }

    /**
     * 条件查询部门信息
     * @param department
     * @return
     */
    @Override
    public Department queryRoleInfo(Department department) {
        if (department != null) {
            if (department.getDepartmentId() != null) {
                AbstractWrapper wrapper = new QueryWrapper<>();
                wrapper.eq("department_id", department.getDepartmentId());
                Department selectOne = departmentMapper.selectOne(wrapper);
                logger.info("selectOne={}", selectOne);
                if (selectOne != null) {
                    return selectOne;
                }
            }
        }
        return null;
    }

    /**
     * 添加部门信息
     *
     * @param department
     * @param roleId
     * @return
     */
    @Override
    public Integer addDepartment(Department department, Integer roleId) {
        System.out.println("-------------进入添加部门service实现层---------------");

        System.out.println("role数据--" + department);
        logger.info("roleId={}" + roleId);

        department.setCreateTime(TimeUtils.dateTime());
        department.setUpdateTime(TimeUtils.dateTime());
        department.setIsDel(0);

        int result = departmentMapper.insert(department);

        int departmentId = department.getDepartmentId();
        DeptRole deptRole = new DeptRole(departmentId, roleId);

        deptRole.setCreateTime(TimeUtils.dateTime());
        deptRole.setUpdateTime(TimeUtils.dateTime());

        deptRole.setIsDel(0);

        deptRoleMapper.insert(deptRole);
        return result;
    }

    /**
     * 修改部门信息
     *
     * @param department
     * @param roleId
     * @return
     */
    @Override
    public Integer updateDepartment(Department department, Integer roleId) {
        System.out.println("---------------进入修改部门service实现层----------------");

        System.out.println("department数据--" + department);
        System.out.println("roleId--" + roleId);

        department.setCreateTime(department.getCreateTime());
        department.setUpdateTime(TimeUtils.dateTime());
        department.setIsDel(0);
        int result = departmentMapper.updateById(department);

        DeptRole deptRole = new DeptRole(department.getDepartmentId(), roleId);

        deptRole.setUpdateTime(TimeUtils.dateTime());

        AbstractWrapper wrapper = new QueryWrapper();

        //dep_id  复合主键表部门的数据库字段
        wrapper.eq("dep_id", department.getDepartmentId());

        System.out.println("role.getRId()--" + department.getDepartmentId());

        deptRoleMapper.update(deptRole, wrapper);
        return result;
    }

    /**
     * 模糊查询部门经理
     *
     * @param dName
     * @return
     */
    @Override
    public List<Department> selectDepartmentName(String dName) {
        return departmentMapper.selectDepartmentName(dName);
    }
}

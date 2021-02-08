package com.ckf.crm.service.impl;

import com.baomidou.mybatisplus.core.conditions.AbstractWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ckf.crm.entity.Department;
import com.ckf.crm.entity.DeptRole;
import com.ckf.crm.entity.Employee;
import com.ckf.crm.entity.Role;
import com.ckf.crm.mapper.DepartmentMapper;
import com.ckf.crm.mapper.DeptRoleMapper;
import com.ckf.crm.mapper.RoleMapper;
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
 * @author xuan
 * @since 2021-03-23
 */
@Service
@Transactional
public class DepartmentServiceImpl extends ServiceImpl<DepartmentMapper, Department> implements DepartmentService {

    private Logger logger = LoggerFactory.getLogger(RoleServiceImpl.class);


    @Autowired
    private DepartmentMapper departmentMapper;

    @Autowired
    private DeptRoleMapper deptRoleMapper;

    @Autowired
    private RoleMapper roleMapper;

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
     *
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
        return departmentMapper.insert(department);
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
        return departmentMapper.updateById(department);
    }

    /**
     * 模糊查询部门领导
     *
     * @param dName
     * @return
     */
    @Override
    public List<Department> selectDepartmentName(String dName) {
        return departmentMapper.selectDepartmentName(dName);
    }
}

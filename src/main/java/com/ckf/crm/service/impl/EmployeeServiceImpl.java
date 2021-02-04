package com.ckf.crm.service.impl;

import com.baomidou.mybatisplus.core.conditions.AbstractWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ckf.crm.entity.EmpRole;
import com.ckf.crm.entity.Employee;
import com.ckf.crm.mapper.EmpRoleMapper;
import com.ckf.crm.mapper.EmployeeMapper;
import com.ckf.crm.service.EmployeeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ckf.crm.utils.RedisUtil;
import com.ckf.crm.utils.ShiroUtils;
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
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;

    @Autowired
    private EmpRoleMapper empRoleMapper;

    @Autowired
    private RedisUtil redisUtil;


    private Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class);


    /**
     * 分页全查询
     *
     * @param page
     * @return
     */
    @Override
    public IPage<Employee> selectList(Page<Employee> page) {
        return employeeMapper.selectList(page);
    }


    /**
     * 查询管理员
     *
     * @return
     */
    @Override
    public List<Employee> selectAdmin() {
        return employeeMapper.selectAdmin();
    }


    /**
     * 查询删除员工接口
     *
     * @return
     */
    @Override
    public List<Employee> selectEmployeeDel() {
        return employeeMapper.selectEmployeeDel();
    }


    /**
     * 分页全查询员工信息
     *
     * @return
     */
    @Override
    public List<Employee> selectEmployeeNameLike(String empName) {
        return employeeMapper.selectEmployeeNameLike(empName);
    }


    /**
     * 模糊查询删除员工
     *
     * @param empName
     * @return
     */
    @Override
    public List<Employee> selectEmployeeNameLikeDel(String empName) {
        return employeeMapper.selectEmployeeNameLikeDel(empName);
    }

    /**
     * 根据用户查询 授权 登录
     *
     * @param userName
     * @return
     */
    @Override
    public Employee selectByUserName(String userName) {
        return employeeMapper.selectByUserName(userName);
    }


    /**
     * 查询用户名是否存在
     *
     * @param empName
     * @return
     */
    @Override
    public Employee selectName(String empName) {
        return employeeMapper.selectName(empName);
    }


    /**
     * 条件查询员工角色信息
     * @param employee
     * @return
     */
    @Override
    public Employee queryRoleInfo(Employee employee) {
        if (employee != null) {
            if (employee.getEmployeeId() != null) {
                AbstractWrapper wrapper = new QueryWrapper<>();
                wrapper.eq("employee_id", employee.getEmployeeId());
                Employee selectOne = employeeMapper.selectOne(wrapper);
                logger.info("selectOne={}", selectOne);
                if (selectOne != null) {
                    return selectOne;
                }
            }
        }
        return null;
    }

    /**
     * 添加员工
     *
     * @param employee
     * @param roleId
     * @return
     */
    @Override
    public Integer addEmployee(Employee employee, Integer roleId) {
        System.out.println("-------------service 进入员工添加-------------");
        /**
         * 1.获取盐
         * 2.shiro加盐加密
         * 3.用户信息存入对象，插入数据库，获取到插入的id
         * 4.将empId和roleId插入到emp_role表中
         */
        //从ShiroUtils类中随机生成盐
        employee.setSalt(ShiroUtils.randomSalt());

        //将密码设置为 加密后的密码（由ShiroUtils里面encryptPassword方法实现）
        employee.setEPwd(ShiroUtils.encryptPassword(employee.getEPwd(), employee.getCredentialsSalt()));

        employee.getPhone();
        employee.setCreateTime(TimeUtils.dateTime());
        employee.setUpdateTime(TimeUtils.dateTime());
        employee.setIsDel(0);
        int result = employeeMapper.insert(employee);

        logger.info("roleId={}" + roleId);

        //获取插入自增的id
        int empId = employee.getEmployeeId();

        //将empId和roleId一同插入到  员工与角色关系表
        EmpRole empRole = new EmpRole(empId, roleId);
        empRole.setCreateTime(TimeUtils.dateTime());
        empRole.setUpdateTime(TimeUtils.dateTime());
        empRole.setIsDel(0);

        empRoleMapper.insert(empRole);

        return result;
    }


    /**
     * 修改员工
     *
     * @param employee
     * @param roleId
     * @return
     */
    @Override
    public Integer updateEmployee(Employee employee, Integer roleId) {
        System.out.println("-------------service 进入员工修改-------------");

        //判断用户是否输入密码，如果没有，获取的就是空字符串 ("")  就不修改密码
        if (!"".equals(employee.getEPwd())) {
            //从ShiroUtils类中随机生成盐
            employee.setSalt(ShiroUtils.randomSalt());
            //将密码设置为 加密后的密码（由ShiroUtils里面encryptPassword方法实现）
            employee.setEPwd(ShiroUtils.encryptPassword(employee.getEPwd(), employee.getCredentialsSalt()));
        } else {
            employee.setEPwd(null);
        }


        //设置时间
        employee.setUpdateTime(TimeUtils.dateTime());

        //将信息更新到数据库中（空的属性不修改）
        int result = employeeMapper.updateById(employee);

        EmpRole empRole = new EmpRole(employee.getEmployeeId(), roleId);

        empRole.setUpdateTime(TimeUtils.dateTime());
        AbstractWrapper wrapper = new QueryWrapper();

        //复合主键表 员工的id数据库字段
        wrapper.eq("emp_id", employee.getEmployeeId());
        empRoleMapper.update(empRole, wrapper);

        return result;
    }

    /**
     * 员工注册
     *
     * @param employee
     * @return
     */
    @Override
    public Integer register(Employee employee) {
        AbstractWrapper abstractWrapper = new QueryWrapper<Employee>();
        abstractWrapper.eq("emp_name", employee.getEmpName());
        Employee pgLabelsWrapperGet = employeeMapper.selectOne(abstractWrapper);
        logger.info("pgLabelsWrapperGet={}", pgLabelsWrapperGet);
        if (pgLabelsWrapperGet != null) {
            return 101;
        }

        //从ShiroUtils类中随机生成盐
        employee.setSalt(ShiroUtils.randomSalt());

        //将密码设置为 加密后的密码（由ShiroUtils里面encryptPassword方法实现）
        employee.setEPwd(ShiroUtils.encryptPassword(employee.getEPwd(), employee.getCredentialsSalt()));

        employee.getPhone();
        employee.setCreateTime(TimeUtils.dateTime());
        employee.setUpdateTime(TimeUtils.dateTime());
        employee.setIsDel(0);
        int insert = employeeMapper.insert(employee);
        if (insert > 0) {
            redisUtil.lSet("employeeId:", employee.getEmployeeId());
            redisUtil.set("employee:" + employee.getEmployeeId(), employee);
        }
        return 200;


    }

    /**
     * 查询员工个人信息
     * @param employee
     * @return
     */
    @Override
    public Employee queryUserInfo(Employee employee) {
        logger.info("employee={}", employee);
        if (employee != null) {
            AbstractWrapper wrapper = new QueryWrapper();

            if (employee.getEmployeeId() != null) {
                wrapper.eq("employee_id", employee.getEmployeeId());
                Employee selectOne = employeeMapper.selectOne(wrapper);
                logger.info("selectOne ={}", selectOne);
                if (selectOne != null) {
                    return selectOne;
                }
            }

        }
        return null;
    }


    /**
     * 修改密码
     *
     * @param employee
     * @return
     */
    @Override
    public boolean updatePassword(Employee employee) {
        return employeeMapper.updatePassword(employee);
    }


}

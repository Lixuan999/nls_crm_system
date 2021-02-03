package com.ckf.crm.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ckf.crm.entity.Employee;
import com.baomidou.mybatisplus.extension.service.IService;


import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author xuan
 * @since 2020-03-23
 */
public interface EmployeeService extends IService<Employee> {


    /**
     * 分页全查询员工信息
     *
     * @return
     */
    IPage<Employee> selectList(Page<Employee> page);


    /**
     * 查询管理员
     *
     * @return
     */
    List<Employee> selectAdmin();


    /**
     * 查询删除员工
     *
     * @return
     */
    List<Employee> selectEmployeeDel();


    /**
     * 根据员工姓名模糊查询
     *
     * @param empName
     * @return
     */
    List<Employee> selectEmployeeNameLike(String empName);


    /**
     * 模糊查询已删除员工
     *
     * @param empName
     * @return
     */
    List<Employee> selectEmployeeNameLikeDel(String empName);

    /**
     * 根据用户查询 授权 登录
     *
     * @param userName
     * @return
     */
    Employee selectByUserName(String userName);


    /**
     * 查询用户名是否存在
     *
     * @param empName
     * @return
     */
    Employee selectName(String empName);


    /**
     * 条件查询员工
     *
     * @param employee
     * @return
     */
    Employee queryRoleInfo(Employee employee);


    /**
     * 添加员工
     *
     * @param employee
     * @param rId
     * @return
     */
    Integer addEmployee(Employee employee, Integer rId);


    /**
     * 修改员工
     *
     * @param employee
     * @param rId
     * @return
     */
    Integer updateEmployee(Employee employee, Integer rId);

    /**
     * 员工注册
     *
     * @param employee
     * @param
     * @return
     */
    Integer register(Employee employee);


    /**
     * 查询员工信息
     *
     * @param employee
     * @return
     */
    Employee queryUserInfo(Employee employee);

    /**
     * 修改密码
     *
     * @param employee
     * @return
     */
    boolean updatePassword(Employee employee);


}

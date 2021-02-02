package com.ckf.crm.mapper;

import com.baomidou.mybatisplus.core.conditions.AbstractWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ckf.crm.entity.Employee;
import com.ckf.crm.service.EmployeeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class EmployeeMapperTest {

    /**
     * 日志
     */
    private Logger log = LoggerFactory.getLogger(EmployeeMapperTest.class);


    @Autowired
    private EmployeeMapper employeeMapper;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private Employee employee;

    /**
     * 构造器
     */
    private AbstractWrapper wrapper = new QueryWrapper<Employee>();


    /**
     * 登录
     */
    @Test
    public void loginTest() {
        wrapper.eq("emp_name", "admin");
        wrapper.eq("e_pwd", 123456);

        Employee users = employeeMapper.selectOne(wrapper);
        System.out.println(users);

        if (users != null) {
            System.out.println("登录成功");
        } else {
            System.out.println("登录失败");
        }
    }


    /**
     * 全查询
     */
    @Test
    public void select() {
        List<Employee> list = employeeService.list();
        for (Employee employee : list) {
            System.out.println(employee);
        }
    }

    /**
     * 查询用户名是否存在
     */
    @Test
    public void selectName() {
        employee = employeeService.selectName("admin");

        if (employee != null) {
            log.info("查询成功");
        } else {
            log.info("查询失败");
        }

    }


    /**
     * 注册
     */
    @Test
    public void register() {
        employee.setEmpName("fff");
        employee.setEPwd("fff");
        employee.setIsDel(0);
        Integer flag = employeeService.register(employee);

        if (flag > 0) {
            log.info("注册成功");
        } else {
            log.info("注册失败");
        }
    }


    /**
     * 条件查询
     */
    @Test
    public void selectById() {
        System.out.println(employeeMapper.selectById(1));
    }


    /**
     * 根据用户查询 授权 情况
     */
    @Test
    public void selectByUserName() {
        Employee flag = employeeService.selectByUserName("admin");
        if (flag != null) {
            System.out.println("查询成功");
        } else {
            System.out.println("查询失败");
        }
    }


    /**
     * 模糊查询员工姓名
     */
    @Test
    public void selectEmployeeName() {

        List<Employee> list = employeeService.selectEmployeeNameLike("陈");
        for (Employee employee1 : list) {
            System.out.println(employee1);
        }
        if (list != null) {
            log.info("模糊查询成功");
        } else {
            log.info("模糊查询失败");
        }

    }


    /**
     * 删除用户信息
     */
    @Test
    public void empDelete() {
        boolean flag = employeeService.removeById(6);

        if (flag) {
            System.out.println("删除成功");
        } else {
            System.out.println("删除失败");
        }
    }

    /**
     * 查询管理员信息
     */
    @Test
    public void selectAdmin() {
        List<Employee> list = employeeMapper.selectAdmin();
        for (Employee employee1 : list) {
            System.out.println(employee1);
        }
    }

}
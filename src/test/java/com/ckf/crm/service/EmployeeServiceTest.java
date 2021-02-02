package com.ckf.crm.service;


import com.ckf.crm.entity.Employee;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


/**
 * @author xuan
 * @version 1.0
 * @date 2020/3/25 18:08
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmployeeServiceTest {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private Employee employee;


    /**
     * 添加
     */
    @Test
    public void add() {
        employee.setEmpName("aa");
        employee.setEPwd("123");
        employee.setSalt("1111");
        employee.setAge(12);
        employee.setSex("男");
        employee.setPhone("13018596458");
        employee.setAddress("广州");
        employee.setRoleList(null);
        employeeService.save(employee);
    }


    /**
     * 添加
     */
    @Test
    public void addEmployee() {
        Employee employee =
                new Employee(null, "test", "123", "123", 18, "男", "13533", "天河", null);
        employee.setCreateTime("2020-3-25");
        employee.setUpdateTime("2020-3-25");
        employee.setIsDel(0);
        employeeService.save(employee);
    }

    /**
     * 加密的添加
     */
    @Test
    public void addEmployee1() {
        Employee employee =
                new Employee(null, "陈克丰", "123", "123", 18, "男", "13533", "天河", null);
        employeeService.addEmployee(employee, 2);
    }


    /**
     * 修改
     */
    @Test
    public void updateEmployee() {

        Employee employee = new Employee(2, "陈克丰", "123", "123", 18, "女", "13015866235", "广州天河", null);
        employeeService.updateEmployee(employee, 1);
    }


    /**
     * 修改密码
     */
    @Test
    public void updatePassword() {
        String username = "ss";
        employee.setEmpName(username);

        String password = "1122";
        System.out.println("--密码:" + password);
        employee.setEPwd(password);

        boolean flag = employeeService.updatePassword(employee);
        if (flag) {
            System.out.println("修改成功");
        } else {
            System.out.println("修改失败");
        }
    }
}
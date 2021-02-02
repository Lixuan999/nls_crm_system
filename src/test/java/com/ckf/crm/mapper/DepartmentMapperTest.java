package com.ckf.crm.mapper;

import com.ckf.crm.controller.EmployeeController;
import com.ckf.crm.entity.Department;
import com.ckf.crm.entity.Employee;
import com.ckf.crm.service.DepartmentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;


/**
 * @author 安详的苦丁茶
 * @version 1.0
 * @date 2020/3/24 8:15
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class DepartmentMapperTest {


    private Logger log = LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    private DepartmentService departmentService;


    /**
     * 全查询部门信息
     */
    @Test
    public void select() {
        List<Department> list = departmentService.list();
        for (Department department : list) {
            System.out.println(department);
        }
    }


    /**
     * 模糊查询部门经理
     */
    @Test
    public void selectDepartmentName() {

        List<Department> list = departmentService.selectDepartmentName("小");
        for (Department department : list) {
            System.out.println(department);
        }
        if (list != null) {
            log.info("模糊查询成功");
        } else {
            log.info("模糊查询失败");
        }
    }
}
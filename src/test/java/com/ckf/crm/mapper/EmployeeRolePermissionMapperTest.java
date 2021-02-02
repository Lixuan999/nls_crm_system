package com.ckf.crm.mapper;


import com.ckf.crm.service.EmployeeRolePermissionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;


/**
 * @author 安详的苦丁茶
 * @version 1.0
 * @date 2020/3/23 21:18
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmployeeRolePermissionMapperTest {


    @Autowired
    private EmployeeRolePermissionService employeeRolePermissionService;

    /**
     * 根据用户名查询的 授权情况
     */
    @Test
    public void selectByEmployeeName() {
        employeeRolePermissionService.selectByEmployeeName("ckf");
    }
}
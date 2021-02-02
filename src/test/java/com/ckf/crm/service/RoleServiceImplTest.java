package com.ckf.crm.service;

import com.ckf.crm.entity.Employee;
import com.ckf.crm.entity.Role;
import com.ckf.crm.vo.RolePermissionVo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @author xuan
 * @version 1.0
 * @date 2020/3/28 1:17
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class RoleServiceImplTest {

    @Autowired
    private RoleService roleService;


    /**
     * 辅助类全查询角色信息
     */
    @Test
    public void selectRolePermissionAll() {
        List<Role> list = roleService.selectRolePermissionAll();
        for (Role role : list) {
            System.out.println(role);
        }

    }
}
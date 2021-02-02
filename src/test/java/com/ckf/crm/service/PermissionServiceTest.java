package com.ckf.crm.service;

import com.ckf.crm.entity.Permission;
import com.ckf.crm.mapper.EmployeeMapperTest;
import com.ckf.crm.utils.RedisUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @author xuan
 * @version 1.0
 * @date 2020/4/2 15:32
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class PermissionServiceTest {

    private Logger logger = LoggerFactory.getLogger(EmployeeMapperTest.class);

    @Autowired
    private PermissionService permissionService;

    @Autowired
    private Permission permission;

    @Autowired
    private RedisUtil redisUtil;

    /**
     * 全查询权限
     */
    @Test
    public void selectPermissionAll() {
        List<Permission> list = permissionService.selectPermissionAll();
        for (Permission permission : list) {
            System.out.println(permission);
        }
    }

    /**
     * 查询url是否存在
     */
    @Test
    public void selectPermissionUrl() {

        permission = permissionService.selectPermissionUrl("/ordDelete");

        if (permission != null) {
            logger.info("查询成功");
        } else {
            logger.info("查询失败");
        }
    }


    /**
     * 从reid中获取权限信息
     */
    @Test
    public void getRedisLabelData() {
        /**
         * 查询标签的全部id
         * 然后循环查询出标签全部信息
         */
        List<Object> permissionId = redisUtil.lGet("permissionId", 0, -1);
        logger.info("permissionId={}", permissionId);

        for (Object o : permissionId) {
            Permission permission = (Permission) redisUtil.get("permission:" + o);
            logger.info("permission={}", permission);
        }

        logger.info("--------- 分割线 ={}", "------");

        /**
         * 通过指定的id查询标签信息
         */
        Permission permission = (Permission) redisUtil.get("permission:" + 21);
        logger.info("permission={}", permission);
    }


}
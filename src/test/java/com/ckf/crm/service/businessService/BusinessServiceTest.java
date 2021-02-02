package com.ckf.crm.service.businessService;

import com.ckf.crm.entity.businessEntity.Business;
import com.ckf.crm.service.businessService.BusinessService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;


/**
 * @author 安详的苦丁茶
 * @version 1.0
 * @date 2020/4/5 3:08
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class BusinessServiceTest {

    @Autowired
    private BusinessService businessService;

    /**
     * 查询全部业务
     */
    @Test
    public void selectBusinessAll() {
        List<Business> list = businessService.selectBusinessList();
        for (Business business : list) {
            System.out.println(business);
        }
    }
}
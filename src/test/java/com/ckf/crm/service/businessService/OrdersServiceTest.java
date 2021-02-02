package com.ckf.crm.service.businessService;

import com.ckf.crm.entity.businessEntity.Orders;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;


/**
 * @author 安详的苦丁茶
 * @version 1.0
 * @date 2020/4/5 3:12
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrdersServiceTest {

    @Autowired
    private OrdersService ordersService;


    /**
     * 查询全部订单
     */
    @Test
    public void selectOrdersAll() {
        List<Orders> list = ordersService.selectOrdersList();
        for (Orders orders : list) {
            System.out.println(orders);
        }
    }

}
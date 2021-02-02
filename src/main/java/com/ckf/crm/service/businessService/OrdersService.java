package com.ckf.crm.service.businessService;

import com.ckf.crm.entity.businessEntity.Orders;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author xuan
 * @since 2020-04-05
 */
public interface OrdersService extends IService<Orders> {

    /**
     * 全查询订单信息
     *
     * @return
     */
    List<Orders> selectOrdersList();


    /**
     * 添加订单信息
     *
     * @param orders
     * @param busId
     * @param cusId
     * @return
     */
    Integer addOrders(Orders orders, Integer busId, Integer cusId);


    /**
     * 修改订单信息
     *
     * @param orders
     * @param busId
     * @param cusId
     * @return
     */
    Integer updateOrders(Orders orders, Integer busId, Integer cusId);

}

package com.ckf.crm.service.businessService.impl;

import com.baomidou.mybatisplus.core.conditions.AbstractWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ckf.crm.entity.businessEntity.OrdBusCus;
import com.ckf.crm.entity.businessEntity.Orders;
import com.ckf.crm.entity.customerEntity.CusEmp;
import com.ckf.crm.mapper.businessMapper.OrdBusCusMapper;
import com.ckf.crm.mapper.businessMapper.OrdersMapper;
import com.ckf.crm.service.businessService.OrdersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ckf.crm.service.customerService.impl.CustomerServiceImpl;
import com.ckf.crm.utils.TimeUtils;
import org.omg.CORBA.ORB;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xuan
 * @since 2020-04-05
 */
@Service
@Transactional
public class OrdersServiceImpl extends ServiceImpl<OrdersMapper, Orders> implements OrdersService {


    private Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);


    @Autowired
    private OrdersMapper ordersMapper;

    @Autowired
    private OrdBusCusMapper ordBusCusMapper;

    /**
     * 全查询订单信息
     *
     * @return
     */
    @Override
    public List<Orders> selectOrdersList() {
        return ordersMapper.selectOrdersList();
    }


    /**
     * 添加订单信息
     *
     * @param orders
     * @param busId
     * @param cusId
     * @return
     */
    @Override
    public Integer addOrders(Orders orders, Integer busId, Integer cusId) {
        System.out.println("-------------进入添加订单service实现层---------------");

        System.out.println("busId--" + busId);
        System.out.println("cusId--" + cusId);

        orders.setCreateTime(TimeUtils.dateTime());
        orders.setUpdateTime(TimeUtils.dateTime());
        orders.setIsDel(0);

        int result = ordersMapper.insert(orders);

        System.out.println("ok");

        logger.info("busId={}" + busId);

        //获取订单自增id
        int ordersId = orders.getOrdersId();

        OrdBusCus ordBusCus = new OrdBusCus(ordersId, busId, cusId);

        ordBusCus.setCreateTime(TimeUtils.dateTime());
        ordBusCus.setUpdateTime(TimeUtils.dateTime());
        ordBusCus.setIsDel(0);

        ordBusCusMapper.insert(ordBusCus);
        return result;
    }

    /**
     * 修改订单信息
     *
     * @param orders
     * @param busId
     * @param cusId
     * @return
     */
    @Override
    public Integer updateOrders(Orders orders, Integer busId, Integer cusId) {
        System.out.println("---------------进入修改订单service实现层----------------");

        System.out.println("orders数据--" + orders);
        System.out.println("busId--" + busId);
        System.out.println("cusId--" + cusId);

        orders.setCreateTime(orders.getCreateTime());
        orders.setUpdateTime(TimeUtils.dateTime());
        orders.setIsDel(0);
        int result = ordersMapper.updateById(orders);

        //获取订单自增id
        int ordersId = orders.getOrdersId();

        OrdBusCus ordBusCus = new OrdBusCus(ordersId, busId, cusId);

        ordBusCus.setCreateTime(ordBusCus.getCreateTime());
        ordBusCus.setUpdateTime(TimeUtils.dateTime());

        AbstractWrapper wrapper = new QueryWrapper();

        //复合主键表 订单的id数据库字段
        wrapper.eq("ord_id", orders.getOrdersId());

        System.out.println("orders.getOrdersId()--" + orders.getOrdersId());

        ordBusCusMapper.update(ordBusCus, wrapper);
        return result;
    }
}

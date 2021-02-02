package com.ckf.crm.service.customerService.impl;

import com.baomidou.mybatisplus.core.conditions.AbstractWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ckf.crm.entity.customerEntity.CusEmp;
import com.ckf.crm.entity.customerEntity.Customer;
import com.ckf.crm.mapper.customerMapper.CusEmpMapper;
import com.ckf.crm.mapper.customerMapper.CustomerMapper;
import com.ckf.crm.service.customerService.CustomerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ckf.crm.utils.TimeUtils;
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
public class CustomerServiceImpl extends ServiceImpl<CustomerMapper, Customer> implements CustomerService {


    private Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);

    @Autowired
    private CustomerMapper customerMapper;

    @Autowired
    private CusEmpMapper cusEmpMapper;

    /**
     * 全查询客户信息
     *
     * @return
     */
    @Override
    public List<Customer> selectCustomerList() {
        return customerMapper.selectCustomerList();
    }


    /**
     * 查询正式客户
     *
     * @return
     */
    @Override
    public List<Customer> selectOfficialCustomer() {
        return customerMapper.selectOfficialCustomer();
    }


    /**
     * 添加客户
     *
     * @param customer
     * @param empId
     * @return
     */
    @Override
    public Integer addCustomer(Customer customer, Integer empId) {
        System.out.println("-------------进入添加客户service实现层---------------");

        System.out.println("empId--" + empId);

        customer.setIsOrders(1);
        customer.setCreateTime(TimeUtils.dateTime());
        customer.setUpdateTime(TimeUtils.dateTime());
        customer.setIsDel(0);

        int result = customerMapper.insert(customer);

        logger.info("empId={}" + empId);

        int customerId = customer.getCustomerId();
        CusEmp cusEmp = new CusEmp(customerId, empId);

        cusEmp.setCreateTime(TimeUtils.dateTime());
        cusEmp.setUpdateTime(TimeUtils.dateTime());
        cusEmp.setIsDel(0);

        cusEmpMapper.insert(cusEmp);
        return result;
    }

    /**
     * 修改客户信息
     *
     * @param customer
     * @param empId
     * @return
     */
    @Override
    public Integer updateCustomer(Customer customer, Integer empId) {
        System.out.println("---------------进入修改客户service实现层----------------");

        System.out.println("customer数据--" + customer);
        System.out.println("empId--" + empId);

        customer.setIsOrders(1);
        customer.setCreateTime(customer.getCreateTime());
        customer.setUpdateTime(TimeUtils.dateTime());
        customer.setIsDel(0);
        int result = customerMapper.updateById(customer);

        CusEmp cusEmp = new CusEmp(customer.getCustomerId(), empId);

        cusEmp.setCreateTime(cusEmp.getCreateTime());
        cusEmp.setUpdateTime(TimeUtils.dateTime());

        AbstractWrapper wrapper = new QueryWrapper();

        //复合主键表 客户的id数据库字段
        wrapper.eq("cus_id", customer.getCustomerId());
        System.out.println("customer.getCustomerId()--" + customer.getCustomerId());

        cusEmpMapper.update(cusEmp, wrapper);
        return result;
    }

    /**
     * 模糊查询客户名称
     *
     * @param cName
     * @return
     */
    @Override
    public List<Customer> selectCustomerNameLike(String cName) {
        return customerMapper.selectCustomerNameLike(cName);
    }


    /**
     * 模糊查询正式客户
     *
     * @param cName
     * @return
     */
    @Override
    public List<Customer> selectCustomerOfficialLike(String cName) {
        return customerMapper.selectCustomerOfficialLike(cName);
    }


}



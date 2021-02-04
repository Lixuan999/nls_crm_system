package com.ckf.crm.service.customerService;

import com.ckf.crm.entity.customerEntity.Customer;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author xuan
 * @since 2021-04-05
 */
public interface CustomerService extends IService<Customer> {


    /**
     * 全查询客户
     *
     * @return
     */
    List<Customer> selectCustomerList();


    /**
     * 查询正式客户
     *
     * @return
     */
    List<Customer> selectOfficialCustomer();


    /**
     * 添加客户信息
     *
     * @param customer
     * @param empId
     * @return
     */
    Integer addCustomer(Customer customer, Integer empId);


    /**
     * 修改客户信息
     *
     * @param empId
     * @return
     */
    Integer updateCustomer(Customer customer, Integer empId);

    /**
     * 模糊查询
     *
     * @param cName
     * @return
     */
    List<Customer> selectCustomerNameLike(String cName);


    /**
     * 模糊查询正式客户
     *
     * @param cName
     * @return
     */
    List<Customer> selectCustomerOfficialLike(String cName);
}

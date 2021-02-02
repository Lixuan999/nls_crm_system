package com.ckf.crm.mapper.customerMapper;

import com.ckf.crm.entity.customerEntity.Customer;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author 安详的苦丁茶
 * @since 2020-04-05
 */
@Repository
public interface CustomerMapper extends BaseMapper<Customer> {

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

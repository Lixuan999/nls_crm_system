package com.ckf.crm.service.customerService;

import com.ckf.crm.entity.customerEntity.Contact;
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
public interface ContactService extends IService<Contact> {

    /**
     * 全查询客户跟踪信息
     *
     * @return
     */
    List<Contact> selectContactList();

    /**
     * 添加客户跟踪信息
     *
     * @param contact
     * @param empId
     * @param cusId
     * @return
     */
    Integer addContact(Contact contact, Integer empId, Integer cusId);

    /**
     * 修改客户跟踪信息
     *
     * @param contact
     * @param empId
     * @param cusId
     * @return
     */
    Integer updateContact(Contact contact, Integer empId, Integer cusId);

}

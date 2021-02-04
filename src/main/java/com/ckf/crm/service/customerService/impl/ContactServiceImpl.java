package com.ckf.crm.service.customerService.impl;

import com.baomidou.mybatisplus.core.conditions.AbstractWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ckf.crm.entity.businessEntity.OrdBusCus;
import com.ckf.crm.entity.customerEntity.ConEmpCus;
import com.ckf.crm.entity.customerEntity.Contact;
import com.ckf.crm.mapper.customerMapper.ConEmpCusMapper;
import com.ckf.crm.mapper.customerMapper.ContactMapper;
import com.ckf.crm.service.customerService.ContactService;
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
 * @since 2021-04-05
 */
@Service
@Transactional
public class ContactServiceImpl extends ServiceImpl<ContactMapper, Contact> implements ContactService {

    private Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);


    @Autowired
    private ContactMapper contactMapper;


    @Autowired
    private ConEmpCusMapper conEmpCusMapper;

    @Override
    public List<Contact> selectContactList() {
        return contactMapper.selectContactList();
    }

    /**
     * 添加客户跟踪信息
     *
     * @param contact
     * @param empId
     * @param cusId
     * @return
     */
    @Override
    public Integer addContact(Contact contact, Integer empId, Integer cusId) {
        System.out.println("-------------进入添加客户跟踪信息service实现层---------------");

        System.out.println("empId--" + empId);
        System.out.println("cusId--" + cusId);

        contact.setCreateTime(TimeUtils.dateTime());
        contact.setUpdateTime(TimeUtils.dateTime());
        contact.setIsDel(0);

        int result = contactMapper.insert(contact);

        System.out.println("ok");


        //获取订单自增id
        int contactId = contact.getContactId();

        ConEmpCus conEmpCus = new ConEmpCus(contactId, empId, cusId);

        conEmpCus.setCreateTime(TimeUtils.dateTime());
        conEmpCus.setUpdateTime(TimeUtils.dateTime());
        conEmpCus.setIsDel(0);

        conEmpCusMapper.insert(conEmpCus);
        return result;
    }


    /**
     * 修改客户跟踪信息
     *
     * @param contact
     * @param empId
     * @param cusId
     * @return
     */
    @Override
    public Integer updateContact(Contact contact, Integer empId, Integer cusId) {
        System.out.println("---------------进入修改客户跟踪信息service实现层----------------");

        System.out.println("contact数据--" + contact);
        System.out.println("empId--" + empId);
        System.out.println("cusId--" + cusId);

        contact.setCreateTime(contact.getCreateTime());
        contact.setUpdateTime(TimeUtils.dateTime());
        contact.setIsDel(0);
        int result = contactMapper.updateById(contact);

        //获取订单自增id
        int contactId = contact.getContactId();

        ConEmpCus conEmpCus = new ConEmpCus(contactId, empId, cusId);

        conEmpCus.setCreateTime(conEmpCus.getCreateTime());
        conEmpCus.setUpdateTime(TimeUtils.dateTime());

        AbstractWrapper wrapper = new QueryWrapper();

        //复合主键表 客户跟踪表的id数据库字段
        wrapper.eq("con_id", contact.getContactId());

        System.out.println("contact.getContactId()--" + contact.getContactId());

        conEmpCusMapper.update(conEmpCus, wrapper);
        return result;
    }
}

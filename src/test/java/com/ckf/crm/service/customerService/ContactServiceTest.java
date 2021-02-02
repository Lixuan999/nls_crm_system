package com.ckf.crm.service.customerService;

import com.ckf.crm.entity.customerEntity.Contact;
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
public class ContactServiceTest {

    @Autowired
    private ContactService contactService;


    /**
     * 查询全部客户跟踪信息
     */
    @Test
    public void selectContactAll() {
        List<Contact> list = contactService.selectContactList();
        for (Contact contact : list) {
            System.out.println(contact);
        }
    }

}
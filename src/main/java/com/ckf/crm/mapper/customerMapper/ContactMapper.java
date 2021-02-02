package com.ckf.crm.mapper.customerMapper;

import com.ckf.crm.entity.customerEntity.Contact;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author xuan
 * @since 2020-04-05
 */
@Repository
public interface ContactMapper extends BaseMapper<Contact> {

    /**
     * 全查询客户跟踪信息
     *
     * @return
     */
    List<Contact> selectContactList();

}

package com.ckf.crm.service.businessService.impl;

import com.ckf.crm.entity.businessEntity.Business;
import com.ckf.crm.mapper.businessMapper.BusinessMapper;
import com.ckf.crm.service.businessService.BusinessService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
public class BusinessServiceImpl extends ServiceImpl<BusinessMapper, Business> implements BusinessService {

    @Autowired
    private BusinessMapper businessMapper;

    /**
     * 全查询业务信息
     *
     * @return
     */
    @Override
    public List<Business> selectBusinessList() {
        return businessMapper.selectBusinessList();
    }
}

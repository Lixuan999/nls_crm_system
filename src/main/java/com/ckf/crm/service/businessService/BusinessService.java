package com.ckf.crm.service.businessService;

import com.ckf.crm.entity.businessEntity.Business;
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
public interface BusinessService extends IService<Business> {

    /**
     * 全查询业务信息
     *
     * @return
     */
    List<Business> selectBusinessList();


}

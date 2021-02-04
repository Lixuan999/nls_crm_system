package com.ckf.crm.mapper.businessMapper;

import com.ckf.crm.entity.businessEntity.Business;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author xuan
 * @since 2021-04-05
 */
@Repository
public interface BusinessMapper extends BaseMapper<Business> {

    /**
     * 全查询业务信息
     *
     * @return
     */
    List<Business> selectBusinessList();

}

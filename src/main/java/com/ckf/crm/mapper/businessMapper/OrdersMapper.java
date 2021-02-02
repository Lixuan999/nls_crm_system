package com.ckf.crm.mapper.businessMapper;

import com.ckf.crm.entity.businessEntity.Orders;
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
public interface OrdersMapper extends BaseMapper<Orders> {

    /**
     * 全查询订单信息
     *
     * @return
     */
    List<Orders> selectOrdersList();

}

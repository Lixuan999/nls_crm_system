package com.ckf.crm.entity.businessEntity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.List;

import com.baomidou.mybatisplus.annotation.TableName;
import com.ckf.crm.entity.BaseEntity;
import com.ckf.crm.entity.customerEntity.Customer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * <p>
 *
 * </p>
 *
 * @author xuan
 * @since 2020-04-05
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("orders")
public class Orders extends BaseEntity {


    @TableId(type = IdType.AUTO)
    private Integer ordersId;

    private Double totalPrice;


    @TableField(exist = false)
    private List<Business> businessList;


    @TableField(exist = false)
    private List<Customer> customerList;


}

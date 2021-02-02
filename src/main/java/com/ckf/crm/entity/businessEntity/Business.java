package com.ckf.crm.entity.businessEntity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.time.LocalDateTime;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableName;
import com.ckf.crm.entity.BaseEntity;
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
 * @author 安详的苦丁茶
 * @since 2020-04-05
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("business")
public class Business extends BaseEntity {

    @TableId(type = IdType.AUTO)
    private Integer businessId;

    private String bName;

    private String head;

    private String phone;

    private String description;


}

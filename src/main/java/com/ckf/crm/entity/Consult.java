package com.ckf.crm.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableName;
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
 * @since 2021-03-27
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("consult")
public class Consult extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    private Integer consultId;

    private String cConsultContent;

    private String cName;

    private String cSex;

    private String cPhone;


}

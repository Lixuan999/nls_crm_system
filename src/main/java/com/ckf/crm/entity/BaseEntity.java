package com.ckf.crm.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 基类 存相同的属性
 *
 * @author 安详的苦丁茶
 * @version 1.0
 * @date 2020/3/23 15:53
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseEntity implements Serializable {

    private String createTime;
    private String updateTime;
    private Integer isDel;
}

package com.ckf.crm.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author 安详的苦丁茶
 * @version 1.0
 * @date 2020/4/6 12:02
 */

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("dept_role")
public class DeptRole extends BaseEntity {

    @TableId
    private Integer depId;

    private Integer roleId;
}

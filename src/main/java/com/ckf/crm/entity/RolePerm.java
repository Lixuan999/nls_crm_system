package com.ckf.crm.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;


/**
 * @author 安详的苦丁茶
 * @version 1.0
 * @date 2020/3/28 14:38
 */

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("role_perm")
public class RolePerm extends BaseEntity {

    private Integer roleId;

    private Integer permId;

}

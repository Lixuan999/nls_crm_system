package com.ckf.crm.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 安详的苦丁茶
 * @version 1.0
 * @date 2020/3/28 1:09
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RolePermissionVo {

    @TableId(type = IdType.AUTO)
    private Integer rId;

    private String rName;


    private String createTime;
    private String updateTime;
    private Integer isDel;

    @TableId(type = IdType.AUTO)
    private Integer pId;

    private String permName;

    private String permission;

    private String url;

}

package com.ckf.crm.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author xuan
 * @version 1.0
 * @date 2020/3/23 20:34
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeRolePermissionVo {

    private Integer eId;
    private String eName;
    private String ePwd;
    private String salt;
    /*private Integer age;
    private String sex;
    private String phone;
    private String address;*/

    /*private Integer rId;*/
    private String rName;

    /*private Integer pId;*/
    private String permName;
    /*private Integer parentId;*/
    private String permission;
    private String url;


}

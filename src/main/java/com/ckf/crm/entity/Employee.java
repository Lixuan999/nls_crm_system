package com.ckf.crm.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

/**
 * @author xuan
 * @version 1.0
 * @date 2020/3/23 15:53
 */

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("employee")
public class Employee extends BaseEntity implements Serializable {

    @TableId(type = IdType.AUTO)
    private Integer employeeId;
    private String accountName;
    private String empName;
    private String ePwd;
    private String salt;
    private Integer age;
    private String sex;
    private String phone;
    private String address;

    /**
     * 让你的真实盐 更加安全一点   真实盐 =用户名 + 盐
     */
    public String getCredentialsSalt() {
        return this.empName + this.salt;
    }

    /**
     * 一个用户具有多个角色
     */
    @TableField(exist = false)
    private List<Role> roleList;


    @Override
    public String toString() {
        return "Employee" + employeeId +
                ", " + empName + '\'' +
                ", " + ePwd + '\'' +
                ", " + salt + '\'' +
                ", " + age +
                ", " + sex + '\'' +
                ", " + phone + '\'' +
                ", " + address + '\'' +
                ",\n" + roleList +
                '}';
    }
}

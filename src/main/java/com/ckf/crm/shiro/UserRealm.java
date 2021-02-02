package com.ckf.crm.shiro;


import com.ckf.crm.entity.Employee;
import com.ckf.crm.entity.Permission;
import com.ckf.crm.entity.Role;
import com.ckf.crm.service.EmployeeService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * shiro工具类
 *
 * @author xuan
 * @version 1.0
 * @date 2020/3/18 15:35
 */


public class UserRealm extends AuthorizingRealm {


    @Autowired
    private EmployeeService employeeService;


    /**
     * 设置返回授权信息的方法
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        //获取当前主体，赋予权限 然后我们在过滤器做多个过滤，符合权限就可以访问
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        //将用户的角色和权限都交给SimpleAuthorizationInfo对象
        //通过principalCollection获取认证方法中传过来的user对象 【SimpleAuthenticationInfo传过来的】
        Employee user = (Employee) principalCollection.getPrimaryPrincipal();

        //通过遍历，将user对象中的角色和权限都打包到info对象中
        for (Role role : user.getRoleList()) {
            //将角色名存到角色中
            info.addRole(role.getRName());

            for (Permission p : role.getPermissionList()) {
                //将数据表中的权限“user:add”存到info中
                info.addStringPermission(p.getPermission());
            }
        }

        return info;
    }


    /**
     * 设置认证信息的方法
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        System.out.println("-----------------进入认证信息模式-------------");

        //拿到前端传过来的用户名
        String empName = (String) authenticationToken.getPrincipal();

        //从数据库中获取用户名密码，用于判断用户信息   【根据用户名，获取user对象】
        Employee employee = employeeService.selectByUserName(empName);

        //判断用户名是否存在 如果存在，直接通过，将密码的判断交给我们的安全管理器
        if (employee == null) {
            System.out.println("用户不存在!");
            throw new UnknownAccountException("用户不存在");
        }

        //打印信息
        System.out.println(ByteSource.Util.bytes("加盐--" + employee.getCredentialsSalt()));

        //将用户对象，密码，realm打包到SimpleAuthenticationInfo对象中
        // （授权方法中还需要user对象中的角色和权限）
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(
                employee,                           //用户对象
                employee.getEPwd(),             //用户密码
                ByteSource.Util.bytes(employee.getCredentialsSalt()),   //使用了加强盐  ，可以看user类
                getName()                        //Realm的名称
        );

        return info;
    }

}

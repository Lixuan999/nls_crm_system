package com.ckf.crm.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import com.ckf.crm.shiro.UserRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author 安详的苦丁茶
 * @version 1.0
 * @date 2020/3/18 15:35
 * @Bean 注解在方法上面，注解的方法是返回一个实例
 */

@Configuration
public class ShiroConfig {

    /**
     * 返回安全管理器实例
     *
     * @return
     */
    @Bean
    public DefaultWebSecurityManager securityManager(@Qualifier("userRealm") UserRealm userRealm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(userRealm);
        return securityManager;
    }

    /**
     * Shiro 过滤器 ：需要配置过滤规则
     *
     * @return
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilter(@Qualifier("securityManager") SecurityManager securityManager) {
        //实例化一个过滤器bean，设置他的安全管理器
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        bean.setSecurityManager(securityManager);

        //实例与一个map对象用于存储过滤资源
        Map<String, String> map = new LinkedHashMap<>();

        /**
         * anon:允许未授权(匿名) 可访问
         * 过滤链定义，从上向下顺序执行，一般将/**放在最为下边  从上向下顺序执行
         */
        //设置无需权限就能访问的url
        map.put("/doLogin", "anon");
        map.put("/empRegister", "anon");
        map.put("/403", "anon");
        map.put("/rec", "anon");
        map.put("/public/special.html", "anon");
        map.put("/public/error.html", "anon");

        map.put("/static/css/**", "anon");
        map.put("/static/image/**", "anon");
        map.put("/static/font/**", "anon");
        map.put("/static/fonts/**", "anon");
        map.put("/static/js/**", "anon");
        map.put("/static/lib/layui/**", "anon");
        map.put("/static/layui/**", "anon");

        //配置退出 过滤器,其中的具体的退出代码Shiro已经替我们实现了
        map.put("/logout", "logout");

        /**给url设置权限  perms
         * user：必须拥有 记住我 功能才能用
         * perms：拥有对某个资源的权限才能访问
         * role：拥有权限才能访问
         */
        map.put("/emp/employee", "perms[emp:list]");
        map.put("/emp/empAdd", "perms[emp:add]");
        map.put("/emp/empUpdate", "perms[emp:update]");
        map.put("/emp/empDelete", "perms[emp:delete]");

        map.put("/rol/role", "perms[rol:list]");
        map.put("/rol/rolAdd", "perms[rol:add]");
        map.put("/rol/rolUpdate", "perms[rol:update]");
        map.put("/rol/rolDelete", "perms[rol:delete]");

        map.put("/per/permission", "perms[per:list]");
        map.put("/per/perAdd", "perms[per:add]");
        map.put("/per/perUpdate", "perms[per:update]");
        map.put("/per/perDelete", "perms[per:delete]");

        map.put("/dep/department", "perms[dep:list]");
        map.put("/dep/depAdd", "perms[dep:add]");
        map.put("/dep/depUpdate", "perms[dep:update]");
        map.put("/dep/depDelete", "perms[dep:delete]");

        map.put("/cus/customer", "perms[cus:list]");
        map.put("/cus/conAdd", "perms[cus:add]");
        map.put("/cus/conUpdate", "perms[cus:update]");
        map.put("/cus/conDelete", "perms[cus:delete]");

        map.put("/cont/contact", "perms[cont:list]");
        map.put("/cont/conAdd", "perms[cont:add]");
        map.put("/cont/conUpdate", "perms[cont:update]");
        map.put("/cont/conDelete", "perms[cont:delete]");

        map.put("/bus/business", "perms[bus:list]");
        map.put("/bus/conAdd", "perms[bus:add]");
        map.put("/bus/conUpdate", "perms[bus:update]");
        map.put("/bus/conDelete", "perms[bus:delete]");

        map.put("/ord/orders", "perms[ord:list]");
        map.put("/ord/conAdd", "perms[ord:add]");
        map.put("/ord/conUpdate", "perms[ord:update]");
        map.put("/ord/conDelete", "perms[ord:delete]");

        map.put("/gam/gambit", "perms[gam:list]");
        map.put("/gam/depAdd", "perms[gam:add]");
        map.put("/gam/depUpdate", "perms[gam:update]");
        map.put("/gam/depDelete", "perms[gam:delete]");

        map.put("/con/consult", "perms[con:list]");
        map.put("/con/conAdd", "perms[con:add]");
        map.put("/con/conUpdate", "perms[con:update]");
        map.put("/con/conDelete", "perms[con:delete]");

        map.put("/emp/employeeDelList", "perms[offc:list]");
        map.put("/cus/offCustomer", "perms[offc:list]");
        map.put("/emp/selectAdmin", "perms[adm:list]");


        /**
         * authc: 授权的前缀
         * authc:所有url都必须认证通过才可以访问
         */
        map.put("/**", "authc");
        bean.setFilterChainDefinitionMap(map);


        //设置登录的url  不需要权限的url
        //不设置  默认跳到login.jsp
        // setLoginUrl 设置能访问的url
        bean.setLoginUrl("/login");

        //登录成功后要跳转的链接
        bean.setSuccessUrl("/index");

        //未授权界面
        bean.setUnauthorizedUrl("/error.html");

        //将定义的过滤器mapper放到过滤器对象中，由安全管理器来管理
        bean.setFilterChainDefinitionMap(map);

        return bean;
    }


    /**
     * 在ioc容器中实例一个userRealm的spring bean对象
     *
     * @param hashedCredentialsMatcher
     * @return
     */
    @Bean
    public UserRealm userRealm(HashedCredentialsMatcher hashedCredentialsMatcher) {
        UserRealm userRealm = new UserRealm();
        userRealm.setCredentialsMatcher(hashedCredentialsMatcher);
        return userRealm;
    }


    /**
     * 凭证匹配器
     * （由于我们的密码校验交给Shiro的SimpleAuthenticationInfo进行处理了）
     *
     * @return
     */
    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName("md5");//散列算法:这里使用MD5算法;
        hashedCredentialsMatcher.setHashIterations(2);//散列的次数，比如散列两次，相当于 md5(md5(""));
        return hashedCredentialsMatcher;
    }


    /**
     * 配置 ShiroDialect，用于thymeleaf和shiro标签配合使用
     *
     * @return
     */
    @Bean
    public ShiroDialect shiroDialect() {
        return new ShiroDialect();
    }

}

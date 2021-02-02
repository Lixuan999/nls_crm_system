package com.ckf.crm.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ckf.crm.entity.Employee;
import com.ckf.crm.service.EmployeeService;
import com.ckf.crm.utils.TimeUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * @author xuan
 * @version 1.0
 * @date 2020/3/23 23:23
 */
@Api(tags = "用户登录注册管理")
@RestController
public class LoginRegisterController {

    /**
     * 日志
     */
    private Logger log = LoggerFactory.getLogger(LoginRegisterController.class);

    Map<String, Object> map = new HashMap<String, Object>();

    @Autowired
    private EmployeeService employeeService;


    @Autowired
    private Employee employee;


    /**
     * 员工登录
     *
     * @param userName
     * @param password
     */
    @ApiOperation("用户登录接口")
    @PostMapping("/doLogin")
    public Map<String, Object> empLogin(String userName, String password, HttpSession session) {
        System.out.println("----------------进入controller 登录模式--------------");

        QueryWrapper<Employee> queryWrapper = new QueryWrapper<>();

        //column 数据库的字段值
        queryWrapper.eq("emp_name", userName);
        queryWrapper.eq("e_pwd", password);

        System.out.println("用户名--" + userName);
        System.out.println("密码--" + password);

        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(userName, password);

        try {
            subject.login(token);
            session.setAttribute("userName", userName);
            System.out.println("对象--" + employee);

            employee.setUpdateTime(TimeUtils.dateTime());
            employeeService.updateById(employee);
            map.put("code", 200);
            log.info("登录成功");

        } catch (UnknownAccountException uae) {
            map.put("code", 404);
            map.put("nameNonentity", 404);
            map.put("code", "用户名不存在");
            log.info("用户名不存在");

        } catch (IncorrectCredentialsException ice) {
            map.put("code", "密码错误");
            map.put("code", 403);
            map.put("wrongPassword", 403);
            log.info("密码错误");
        }
        return map;
    }


    /**
     * 用户注册
     *
     * @param
     * @return
     */
    @ApiOperation("用户注册接口")
    @PostMapping(path = "/empRegister")
    @ResponseBody
    public Map<String, Object> empRegister(Employee employee) {
        System.out.println("------------进入员工添加信息模式--------------");
        log.info("employee={}", employee);

        Integer flag = employeeService.register(employee);

        if (flag > 0) {
            log.info("注册成功");
            map.put("registerSucceed", "注册成功");
            map.put("code", 200);
            return map;
        } else {
            log.info("注册失败");
            map.put("registerDefeated", "注册失败");
            map.put("code", 100);
        }
        return map;
    }


    /**
     * 修改密码
     *
     * @param request
     * @param session
     * @return
     */
    @PutMapping("/updatePass")
    @ResponseBody
    public Map<String, Object> empUpdatePass(HttpServletRequest request, HttpSession session) {

        System.out.println("-----------------进入修改密码模式-----------------");
        Map<String, Object> ouMap = new HashMap<String, Object>();

        //当前账户名字
        String eName = (String) session.getAttribute("e_name");
        //旧密码
        String oldPassword = request.getParameter("oldPassword");
        //新密码
        String newPassword = request.getParameter("newPassword");

        //打印参数数据
        System.out.println("当前用户:" + eName + "--旧密码:" + oldPassword + "--新密码:--" + newPassword);


        //获取当前账号名字
        employee = employeeService.selectName(eName);

        /* session.setAttribute("userName", eName);*/
        System.out.println("当前用户--" + eName);

        if (!oldPassword.equals(employee.getEPwd())) {
            System.out.println("输入旧密码跟原密码不一致");
            ouMap.put("password_unlike", 100);
            ouMap.put("code", 100);
            return ouMap;
        }

        employee.setEPwd(newPassword);

        boolean flag = employeeService.updatePassword(employee);
        System.out.println(flag);


        if (flag) {
            System.out.println("修改密码成功");
            ouMap.put("modify_successfully", 200);
            ouMap.put("code", 200);
            return ouMap;
        } else {
            System.out.println("修改密码失败");
            ouMap.put("change_failed", 100);
            ouMap.put("code", 100);
        }

        return ouMap;
    }

}

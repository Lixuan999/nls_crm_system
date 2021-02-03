package com.ckf.crm.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ckf.crm.entity.Employee;
import com.ckf.crm.entity.Gambit;
import com.ckf.crm.model.ResultFormat;
import com.ckf.crm.service.EmployeeService;
import com.ckf.crm.utils.ResultUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author xuan
 * @since 2020-03-23
 */
@Api(tags = "员工信息管理")
@RestController
@RequestMapping("emp")
public class EmployeeController {

    /**
     * 日志
     */
    private Logger log = LoggerFactory.getLogger(EmployeeController.class);

    Map<String, Object> outMap = new HashMap<String, Object>();

    @Autowired
    private EmployeeService employeeService;

    /**
     * 查询角色
     *
     * @return
     */
    @GetMapping("/goEmployee")
    public List<Employee> roles() {

        return employeeService.list();
    }


    /**
     * 全查询员工信息
     *
     * @param page
     * @param limit
     * @return
     */
    @ApiOperation(value = "员工查询接口")
    @GetMapping("/employee")
    public Map<String, Object> goEmployee(Integer page, Integer limit) {
        System.out.println("---------------进入员工信息全查询模式------------------");

        Map<String, Object> map = new HashMap<String, Object>();

        //设置mybatisPlus分页
        Page<Employee> p = new Page<Employee>();
        p.setSize(limit);
        p.setCurrent(page);

        IPage<Employee> iPage = employeeService.selectList(p);

        map.put("msg", "查询情况");
        map.put("count", iPage.getTotal());
        map.put("data", iPage.getRecords());
        map.put("code", 0);

        return map;
    }


    /**
     * 条件查询员工信息（数据回显）
     *
     * @param employee
     * @return
     * @throws Exception
     */
    @PostMapping("queryEmpInfo")
    @ApiOperation(value = "条件查询员工信息")
    public ResultFormat queryRoleInfo(Employee employee) throws Exception {
        Employee queryRoleInfo = employeeService.queryRoleInfo(employee);
        if (queryRoleInfo != null) {
            return ResultUtil.success(queryRoleInfo);
        }
        return ResultUtil.success(100, "暂无数据");
    }

    /**
     * 查询管理员信息
     *
     * @param page
     * @param limit
     * @return
     */
    @GetMapping("/adminList")
    public Map<String, Object> selectAdmin(@RequestParam("page") Integer page, @RequestParam("limit") Integer limit) {

        System.out.println("---------------进入查询管理员信息模式------------------");

        Map<String, Object> map = new HashMap<String, Object>();

        PageHelper.startPage(page, limit);

        List<Employee> list = employeeService.selectAdmin();

        if (list != null) {
            map.put("msg", "查询成功");
            map.put("code", 0);
            log.info("查询成功");

            PageInfo pageInfo = new PageInfo(list);
            map.put("data", pageInfo.getList());
            map.put("count", pageInfo.getTotal());
        } else {
            map.put("code", 100);
            map.put("msg", "查询失败");
            log.info("查询失败");
        }

        return map;
    }


    /**
     * 从shiro中获取员工信息
     *
     * @return
     */
    @GetMapping("getShiroUserInfo")
    @ApiOperation(value = "从shiro中获取用户信息")
    public ResultFormat getShiroUserInfo() {
        Subject subject = SecurityUtils.getSubject();
        Employee pgUsersSubject = (Employee) subject.getPrincipal();
        Employee employee = employeeService.queryUserInfo(pgUsersSubject);
        log.info("employee ={}", employee);
        if (employee != null) {
            return ResultUtil.success(employee);
        }
        return ResultUtil.error(100, "请重新登录！");
    }


    /**
     * 查询已删除员工
     *
     * @param page
     * @param limit
     * @return
     */
    @ApiOperation(value = "查询员工删除接口")
    @GetMapping("/employeeDelList")
    public Map<String, Object> goDepartment(@RequestParam("page") Integer page, @RequestParam("limit") Integer limit) {

        System.out.println("---------------进入查询员工删除接口模式------------------");

        PageHelper.startPage(page, limit);

        List<Employee> list = employeeService.selectEmployeeDel();

        if (list != null) {
            log.info("查询成功");
            outMap.put("code", 0);
            outMap.put("msg", "查询成功");

            PageInfo pageInfo = new PageInfo(list);
            outMap.put("data", pageInfo.getList());
            outMap.put("count", pageInfo.getTotal());
        } else {
            log.info("查询失败");
            outMap.put("code", 100);
            outMap.put("msg", "查询失败");
        }

        return outMap;
    }

    /**
     * 模糊查询员姓名
     *
     * @param page
     * @param limit
     * @param request
     * @return
     */
    @ApiOperation(value = "模糊查询员工姓名接口")
    @GetMapping("/empSearchName")
    @ResponseBody
    public Map<String, Object> searchName(@RequestParam("page") Integer page, @RequestParam("limit") Integer limit, HttpServletRequest request) {

        System.out.println("-------------------进入模糊查询员工姓名模式------------------");

        //分页
        PageHelper.startPage(page, limit);

        //拿到输入的关键字
        String keyword = request.getParameter("nameSearch");
        System.out.println("关键字:" + keyword);

        List<Employee> list = employeeService.selectEmployeeNameLike(keyword);

        if (list != null) {
            log.info("模糊查询成功");
            outMap.put("code", 0);
            outMap.put("EmployeeName", list);
            outMap.put("fuzzyQuerySucceed ", "模糊查询成功");

            PageInfo pageInfo = new PageInfo(list);
            System.out.println("数据-- " + pageInfo);

            outMap.put("data", pageInfo.getList());
            outMap.put("count", pageInfo.getTotal());

            return outMap;
        } else {
            outMap.put("code", 100);
            log.info("模糊查询失败");
            outMap.put("fuzzyQueryDefeated", "模糊查询失败");
        }
        return outMap;
    }


    /**
     * 模糊查询已删除员工
     *
     * @param page
     * @param limit
     * @param request
     * @return
     */
    @ApiOperation(value = "模糊查询已删除员工接口")
    @GetMapping("/empSearchNameDel")
    @ResponseBody
    public Map<String, Object> searchNameDel(@RequestParam("page") Integer page, @RequestParam("limit") Integer limit, HttpServletRequest request) {

        System.out.println("-------------------进入模糊查询已删除员工模式------------------");

        System.out.println(page + " -- " + limit);
        PageHelper.startPage(page, limit);

        String eName = request.getParameter("nameSearch");
        System.out.println("关键字:" + eName);

        List<Employee> list = employeeService.selectEmployeeNameLikeDel(eName);

        if (list != null) {
            log.info("模糊查询成功");
            outMap.put("Employee", list);
            outMap.put("code", 0);
            outMap.put("fuzzyQuerySucceed ", "模糊查询成功");

            PageInfo pageInfo = new PageInfo(list);
            System.out.println("数据-- " + pageInfo);

            outMap.put("data", pageInfo.getList());
            outMap.put("count", pageInfo.getTotal());

            return outMap;
        } else {
            outMap.put("code", 100);
            log.info("模糊查询失败");
            outMap.put("fuzzyQueryDefeated", "模糊查询失败");
        }
        return outMap;
    }

    /**
     * 修改员工状态
     *
     * @param employee
     * @return
     */
    @ApiOperation("修改员工状态接口")
    @PutMapping("/delete")
    public Map<String, Object> deleteEmployee(Employee employee) {
        Map<String, Object> outMap = new HashMap<String, Object>();
        log.info("Employee=={}", employee);

        boolean flag = employeeService.updateById(employee);

        if (flag) {
            log.info("禁用成功");
            return outMap;
        } else {
            log.info("禁用失败");
            return outMap;
        }
    }


    /**
     * 恢复员工接口
     *
     * @param employeeId
     * @return
     */
    @ApiOperation(value = "恢复员工接口")
    @ApiImplicitParam(name = "eId", value = "用户ID", dataType = "int")
    @DeleteMapping("/empResume")
    public Map<String, Object> empResume(Integer employeeId) {
        System.out.println("----------------进入恢复员工信息模式-----------------");

        Employee employee = new Employee();
        employee.setEmployeeId(employeeId);
        employee.setIsDel(0);

        boolean flag = employeeService.updateById(employee);

        if (flag) {
            outMap.put("code", "200");
            System.out.println("id--" + employeeId);
            outMap.put("msg", "恢复成功");
            log.info("恢复成功");
            return outMap;

        } else {
            outMap.put("code", "100");
            outMap.put("msg", "恢复失败");
            log.info("恢复失败");
        }
        return outMap;
    }


    /**
     * 删除员工信息
     *
     * @param employeeId
     * @return
     */
    @ApiOperation(value = "根据ID删除用户的接口")
    @ApiImplicitParam(name = "eId", value = "用户ID", dataType = "int")
    @DeleteMapping("/empDelete")
    public Map<String, Object> delete(Integer employeeId) {
        System.out.println("----------------进入删除员工信息模式-----------------");

        Employee employee = new Employee();
        employee.setEmployeeId(employeeId);
        employee.setIsDel(1);

        boolean flag = employeeService.updateById(employee);

        if (flag) {
            outMap.put("code", "200");
            System.out.println("id--" + employeeId);
            outMap.put("msg", "删除成功");
            log.info("删除成功");
            return outMap;

        } else {
            outMap.put("code", "100");
            outMap.put("msg", "删除失败");
            log.info("删除失败");
        }
        return outMap;
    }

    /**
     * 添加员工
     *
     * @param employee
     * @return
     */
    @ApiOperation(value = "添加员工信息接口")
    @PostMapping(path = "/empAdd")
    @ResponseBody
    public Map<String, Object> add(Employee employee, Integer roleId) {
        System.out.println("------------进入员工添加信息模式--------------");
        log.info("employee={}", employee);
        log.info("roleId={}", roleId);

        Integer flag = employeeService.addEmployee(employee, roleId);

        if (flag > 0) {
            outMap.put("code", "200");
            log.info("添加成功");
            outMap.put("msg", "添加成功");

        } else {
            log.info("添加失败");
            outMap.put("code", "100");
            outMap.put("msg", "添加失败");
        }
        return outMap;
    }


    /**
     * 修改员工
     *
     * @param employee
     * @return
     */
    @ApiOperation(value = "根据ID修改员工信息接口")
    @PutMapping("/empUpdate")
    @ResponseBody
    public Map<String, Object> update(Employee employee, Integer roleId) {
        System.out.println("----------------进入员工修改模式------------------");

        Integer flag = employeeService.updateEmployee(employee, roleId);

        if (flag > 0) {
            outMap.put("code", "200");
            outMap.put("msg", "修改成功");
            log.info("修改成功");
        } else {
            outMap.put("code", "100");
            outMap.put("msg", "修改失败");
            log.info("修改失败");
        }
        return outMap;
    }

    /**
     * 批量删除员工信息
     * 状态为1 :已删除
     *
     * @param isDel
     * @param employeeId
     * @return
     */
    @PutMapping("delBatch")
    @ApiOperation(value = "批量删除员工信息")
    public ResultFormat delBatchLink(Integer isDel, @RequestParam(value = "employeeId[]") Integer[] employeeId) {
        log.info("IsDel={}", isDel);

        Employee employee = new Employee();
        employee.setIsDel(1);
        boolean updateById = false;
        for (Integer integer : employeeId) {
            employee.setEmployeeId(integer);
            updateById = employeeService.updateById(employee);
        }
        if (updateById) {
            return ResultUtil.success();
        }
        return ResultUtil.error(100, "修改失败");
    }

    /**
     * 批量恢复员工信息
     * 状态为0 :已恢复
     *
     * @param isDel
     * @param employeeId
     * @return
     */
    @PutMapping("recoverBatch")
    @ApiOperation(value = "批量删除员工信息")
    public ResultFormat recoverBatch(Integer isDel, @RequestParam(value = "employeeId[]") Integer[] employeeId) {
        log.info("IsDel={}", isDel);

        Employee employee = new Employee();
        employee.setIsDel(0);
        boolean updateById = false;
        for (Integer integer : employeeId) {
            employee.setEmployeeId(integer);
            updateById = employeeService.updateById(employee);
        }
        if (updateById) {
            return ResultUtil.success();
        }
        return ResultUtil.error(100, "修改失败");
    }


}

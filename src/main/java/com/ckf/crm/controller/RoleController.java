package com.ckf.crm.controller;


import com.baomidou.mybatisplus.core.conditions.AbstractWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ckf.crm.entity.EmpRole;
import com.ckf.crm.entity.Role;
import com.ckf.crm.model.ResultFormat;
import com.ckf.crm.service.RoleService;
import com.ckf.crm.utils.RedisUtil;
import com.ckf.crm.utils.ResultUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
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
@Api(tags = "角色信息管理")
@RestController
@RequestMapping("/rol")
public class RoleController {

    /**
     * 日志
     */
    private Logger log = LoggerFactory.getLogger(EmployeeController.class);

    Map<String, Object> outMap = new HashMap<String, Object>();

    @Autowired
    private RoleService roleService;


    /**
     * 查询角色
     *
     * @return
     */
    @GetMapping("/goRole")
    public List<Role> goRole() {

        return roleService.selectRolePermissionAll();
    }

    /**
     * 通过id查询员工角色信息
     *
     * @param employeeId
     * @return
     */
    @PostMapping("queryEmpRoleInfo")
    @ApiOperation(value = "通过id查询员工角色信息")
    public ResultFormat queryEmpRoleInfo(Integer employeeId) {
        log.info("employeeId={}", employeeId);

        EmpRole pgEmpRoleRef = roleService.queryUserRoleInfo(employeeId);
        if (pgEmpRoleRef != null) {
            return ResultUtil.success(pgEmpRoleRef);
        }
        return ResultUtil.success(100, "暂无数据");
    }


    /**
     * 通过id查询部门角色信息
     *
     * @param departmentId
     * @return
     */
    @PostMapping("queryDepRoleInfo")
    @ApiOperation(value = "通过id查询员工角色信息")
    public ResultFormat queryDepRoleInfo(Integer departmentId) {
        log.info("employeeId={}", departmentId);

        EmpRole pgDepRoleRef = roleService.queryUserRoleInfo(departmentId);
        if (pgDepRoleRef != null) {
            return ResultUtil.success(pgDepRoleRef);
        }
        return ResultUtil.success(100, "暂无数据");
    }

    /**
     * 执行角色全查询
     *
     * @param page
     * @param limit
     * @return
     */
    @ApiOperation("查询角色信息接口")
    @GetMapping("/role")
    public Map<String, Object> role(@RequestParam("page") Integer page, @RequestParam("limit") Integer limit) {

        System.out.println("---------------进入角色信息全查询模式------------------");

        PageHelper.startPage(page, limit);

        List<Role> list = roleService.selectRolePermissionAll();

        if (list != null) {
            outMap.put("code", 0);
            outMap.put("msg", "查询成功");
            log.info("查询成功");

            PageInfo pageInfo = new PageInfo(list);
            outMap.put("data", pageInfo.getList());
            outMap.put("count", pageInfo.getTotal());
        } else {
            outMap.put("code", 100);
            outMap.put("msg", "查询失败");
            log.info("查询失败");
        }

        return outMap;
    }

    /**
     * 模糊查询角色名
     *
     * @param page
     * @param limit
     * @param request
     * @return
     */
    @ApiOperation(value = "模糊查询角色名接口")
    @GetMapping("/rolSearchName")
    @ResponseBody
    public Map<String, Object> searchName(@RequestParam("page") Integer page, @RequestParam("limit") Integer limit, HttpServletRequest request) {

        System.out.println("-------------------进入模糊查询角色名模式------------------");

        System.out.println(page + " -- " + limit);
        PageHelper.startPage(page, limit);

        String name = request.getParameter("nameSearch");
        System.out.println("关键字:" + name);

        List<Role> list = roleService.selectRoleNameLike(name);

        if (list != null) {
            log.info("模糊查询成功");
            outMap.put("Role", list);
            outMap.put("code", 0);
            outMap.put("fuzzyQuerySucceed ", "模糊查询成功");

            PageInfo pageInfo = new PageInfo(list);
            log.info("数据-- " + pageInfo);

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
     * 修改角色状态
     *
     * @param role
     * @return
     */
    @ApiOperation("修改角色状态接口")
    @PutMapping("/delete")
    public Map<String, Object> deleteEmployee(Role role) {
        Map<String, Object> outMap = new HashMap<String, Object>();
        log.info("role=={}", role);

        boolean flag = roleService.updateById(role);

        if (flag) {
            log.info("禁用成功");
            return outMap;
        } else {
            log.info("禁用失败");
            return outMap;
        }
    }

    /**
     * 删除角色信息
     *
     * @param rolId
     * @return
     */
    @ApiOperation("根据ID删除角色信息接口")
    @ApiImplicitParam(name = "dId", value = "ID", dataType = "int")
    @DeleteMapping("/rolDelete")
    public Map<String, Object> delete(Integer rolId) {
        System.out.println("----------------进入删除角色信息模式-----------------");

        //获取对象修改是否删除状态
        Role role = new Role();
        role.setRolId(rolId);
        role.setIsDel(1);

        boolean flag = roleService.updateById(role);

        if (flag) {
            log.info("删除成功");
            outMap.put("code", "200");
            System.out.println("id--" + rolId);
            outMap.put("msg", "删除成功");
            return outMap;

        } else {
            outMap.put("code", "100");
            outMap.put("msg", "删除失败");
            log.info("删除失败");
        }
        return outMap;
    }


    /**
     * 添加角色信息
     *
     * @param role
     * @return
     */
    @ApiOperation("根据ID添加角色接口")
    @PostMapping(path = "/rolAdd")
    @ResponseBody
    public Map<String, Object> add(Role role, Integer permId) {
        System.out.println("------------进入添加角色信息模式--------------");

        System.out.println("role--" + role);
        System.out.println("permId--" + permId);

        Integer flag = roleService.addRole(role, permId);

        if (flag > 0) {
            log.info("添加成功");
            outMap.put("code", "200");
            outMap.put("msg", "添加成功");
        } else {
            outMap.put("code", "100");
            outMap.put("msg", "添加失败");
            log.info("添加失败");
        }
        return outMap;
    }


    /**
     * 修改角色信息
     *
     * @param role
     * @return
     */
    @ApiOperation(value = "根据id修改角色接口")
    @PutMapping("/rolUpdate")
    @ResponseBody
    public Map<String, Object> update(Role role, Integer permId) {
        System.out.println("----------------进入修改角色信息模式------------------");

        Integer flag = roleService.updateRole(role, permId);

        if (flag > 0) {
            log.info("修改成功");
            outMap.put("code", "200");
            outMap.put("msg", "修改成功");
        } else {
            outMap.put("code", "100");
            outMap.put("msg", "修改失败");
            log.info("修改失败");
        }
        return outMap;
    }


}

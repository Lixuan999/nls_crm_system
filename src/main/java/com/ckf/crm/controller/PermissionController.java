package com.ckf.crm.controller;

import com.ckf.crm.entity.Permission;
import com.ckf.crm.entity.businessEntity.Orders;
import com.ckf.crm.model.ResultFormat;
import com.ckf.crm.service.PermissionService;
import com.ckf.crm.utils.ResultUtil;
import com.ckf.crm.utils.TimeUtils;
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
 * @authorxuan
 * @since 2021-03-23
 */
@Api(tags = "权限信息管理")
@RestController
@RequestMapping("/per")
public class PermissionController {

    private Logger log = LoggerFactory.getLogger(LoginRegisterController.class);

    @Autowired
    private PermissionService permissionService;

    @Autowired
    private Permission permission;

    Map<String, Object> outMap = new HashMap<String, Object>();


    /**
     * 查询权限
     *
     * @return
     */
    @GetMapping("/goPerm")
    public List<Permission> goPerm() {

        return permissionService.selectPermissionAll();
    }


    /**
     * 查询权限信息
     *
     * @param page
     * @param limit
     * @return
     */
    @GetMapping("/permission")
    public Map<String, Object> goDepartment(@RequestParam("page") Integer page, @RequestParam("limit") Integer limit) {

        System.out.println("---------------进入权限信息全查询模式------------------");

        PageHelper.startPage(page, limit);

        List<Permission> list = permissionService.selectPermissionAll();

        if (list != null) {
            log.info("查询成功");
            outMap.put("code", 0);
            outMap.put("msg", "查询成功");

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
     * 模糊查询权限名
     *
     * @param page
     * @param limit
     * @param request
     * @return
     */
    @ApiOperation(value = "模糊查询权限名接口")
    @GetMapping("/perSearchName")
    @ResponseBody
    public Map<String, Object> searchName(@RequestParam("page") Integer page, @RequestParam("limit") Integer limit, HttpServletRequest request) {

        System.out.println("-------------------进入模糊查询权限名模式------------------");

        System.out.println(page + " -- " + limit);
        PageHelper.startPage(page, limit);

        String name = request.getParameter("nameSearch");
        System.out.println("关键字:" + name);

        List<Permission> list = permissionService.selectPermissionNameLike(name);

        if (list != null) {
            log.info("模糊查询成功");
            outMap.put("Permission", list);
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
     * 修改权限状态
     *
     * @param permission
     * @return
     */
    @ApiOperation("修改权限状态接口")
    @PutMapping("/delete")
    public Map<String, Object> deleteBusiness(Permission permission) {
        Map<String, Object> outMap = new HashMap<String, Object>();
        log.info("permission=={}", permission);

        boolean flag = permissionService.updateById(permission);

        if (flag) {
            log.info("禁用成功");
            return outMap;
        } else {
            log.info("禁用失败");
            return outMap;
        }
    }


    /**
     * 删除权限信息
     *
     * @param permissionId
     * @return
     */
    @ApiOperation("根据ID删除部门接口")
    @ApiImplicitParam(name = "pId", value = "ID", dataType = "int")
    @DeleteMapping("/perDelete")
    public Map<String, Object> delete(Integer permissionId) {
        System.out.println("----------------进入删除权限信息模式-----------------");

        //获取对象修改是否删除状态
        Permission permission = new Permission();
        permission.setPermissionId(permissionId);
        permission.setIsDel(1);

        boolean flag = permissionService.updateById(permission);

        if (flag) {
            log.info("id--" + permissionId);
            outMap.put("code", "200");
            log.info("删除成功");
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
     * 添加权限信息
     *
     * @param permission
     * @return
     */
    @ApiOperation("添加权限信息接口")
    @PostMapping("/perAdd")
    @ResponseBody
    public ResultFormat savePgSorts(Permission permission) {
        System.out.println("----------------进入添加权限信息模式------------------");

        ResultFormat insert = permissionService.savePermission(permission);

        if (insert.getCode() == 200) {
            log.info("添加成功");
            return ResultUtil.success();
        }
        if (insert.getCode() == 101) {
            log.info("url已存在");
            return ResultUtil.error(insert.getCode(), insert.getMsg());
        }

        return ResultUtil.error(100, "添加失败");
    }

    /**
     * 修改权限信息
     *
     * @param
     * @return
     */
    @ApiOperation(value = "根据id修改权限接口")
    @PutMapping("/perUpdate")
    @ResponseBody
    public ResultFormat update(Permission permission) {
        System.out.println("----------------进入修改权限信息模式------------------");

        Integer updatePermission = permissionService.updatePermission(permission);

        if (updatePermission == 101) {
            log.info("url已存在");
            return ResultUtil.error(101, "url已经存在");

        }
        if (updatePermission == 200) {
            log.info("修改成功");
            return ResultUtil.success();
        }

        return ResultUtil.error(100, "修改失败");
    }

    /**
     * 批量删除权限信息
     * 状态为1 :已删除
     *
     * @param isDel
     * @param permissionId
     * @return
     */
    @PutMapping("delBatch")
    @ApiOperation(value = "批量删除权限信息")
    public ResultFormat delBatchLink(Integer isDel, @RequestParam(value = "permissionId[]") Integer[] permissionId) {
        log.info("isDel={}", isDel);

        Permission permission = new Permission();
        permission.setIsDel(1);
        boolean updateById = false;
        for (Integer integer : permissionId) {
            permission.setPermissionId(integer);
            updateById = permissionService.updateById(permission);
        }
        if (updateById) {
            return ResultUtil.success();
        }
        return ResultUtil.error(100, "修改失败");
    }

}

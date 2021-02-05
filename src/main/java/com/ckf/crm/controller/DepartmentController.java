package com.ckf.crm.controller;


import com.ckf.crm.entity.Department;
import com.ckf.crm.entity.Employee;
import com.ckf.crm.entity.Permission;
import com.ckf.crm.model.ResultFormat;
import com.ckf.crm.service.DepartmentService;
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

@Api(tags = "部门信息管理")
@RestController
@RequestMapping("/dep")
public class DepartmentController {

    /**
     * 日志
     */
    private Logger log = LoggerFactory.getLogger(EmployeeController.class);

    Map<String, Object> outMap = new HashMap<String, Object>();


    @Autowired
    private DepartmentService departmentService;


    /**
     * 查询部门
     *
     * @return
     */
    @GetMapping("/goDepartment")
    public List<Department> goDepartment() {
        return departmentService.list();
    }

    /**
     * 查询部门信息
     *
     * @param page
     * @param limit
     * @return
     */
    @ApiOperation("查询部门信息接口")
    @GetMapping("/department")
    public Map<String, Object> goDepartment(@RequestParam("page") Integer page, @RequestParam("limit") Integer limit) {

        System.out.println("---------------进入部门信息全查询模式------------------");

        PageHelper.startPage(page, limit);

        List<Department> list = departmentService.selectDepartmentAll();

        if (list != null) {
            outMap.put("code", 0);
            log.info("查询成功");
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
     * 条件查询部门信息（数据回显）
     *
     * @param department
     * @return
     * @throws Exception
     */
    @PostMapping("queryDepInfo")
    @ApiOperation(value = "条件查询部门信息")
    public ResultFormat queryDepartmentInfo(Department department) throws Exception {
        Department queryDepartmentInfo = departmentService.queryRoleInfo(department);
        if (queryDepartmentInfo != null) {
            return ResultUtil.success(queryDepartmentInfo);
        }
        return ResultUtil.success(100, "暂无数据");
    }

    /**
     * 修改部门状态
     *
     * @param department
     * @return
     */
    @ApiOperation("修改部门状态接口")
    @PutMapping("/delete")
    public Map<String, Object> deleteEmployee(Department department) {
        log.info("role=={}", department);

        boolean flag = departmentService.updateById(department);

        if (flag) {
            log.info("禁用成功");
            return outMap;
        } else {
            log.info("禁用失败");
            return outMap;
        }
    }

    /**
     * 模糊查询部门领导姓名
     *
     * @param page
     * @param limit
     * @param request
     * @return
     */
    @ApiOperation(value = "模糊查询部门领导姓名接口")
    @GetMapping("/depSearchName")
    @ResponseBody
    public Map<String, Object> searchName(@RequestParam("page") Integer page, @RequestParam("limit") Integer limit, HttpServletRequest request) {

        System.out.println("-------------------进入模糊查询部门领导姓名模式------------------");

        System.out.println(page + " -- " + limit);
        PageHelper.startPage(page, limit);

        String dName = request.getParameter("nameSearch");
        System.out.println("关键字:" + dName);

        List<Department> list = departmentService.selectDepartmentName(dName);

        if (list != null) {
            outMap.put("Department", list);
            outMap.put("code", 0);
            log.info("模糊查询成功");
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
     * 删除部门信息
     *
     * @param departmentId
     * @return
     */
    @ApiOperation("根据ID删除部门接口")
    @ApiImplicitParam(name = "dId", value = "ID", dataType = "int")
    @DeleteMapping("/depDelete")
    public Map<String, Object> delete(Integer departmentId) {
        System.out.println("----------------进入删除部门信息模式-----------------");

        Department department = new Department();
        department.setDepartmentId(departmentId);
        department.setIsDel(1);

        boolean flag = departmentService.updateById(department);

        if (flag) {
            outMap.put("code", "200");
            log.info("id--" + departmentId);
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
     * 添加部门信息
     *
     * @param department
     * @return
     */
    @ApiOperation("根据id添加部门接口")
    @PostMapping(path = "/depAdd")
    @ResponseBody
    public Map<String, Object> add(Department department, Integer roleId) {
        System.out.println("------------进入添加部门信息模式--------------");

        department.setCreateTime(TimeUtils.dateTime());
        department.setUpdateTime(TimeUtils.dateTime());
        department.setIsDel(0);

        Integer flag = departmentService.addDepartment(department, roleId);

        if (flag > 0) {
            log.info("添加成功");
            outMap.put("code", "200");
            outMap.put("msg", "添加成功");
        } else {
            outMap.put("code", "100");
            log.info("添加失败");
            outMap.put("msg", "添加失败");
        }
        return outMap;
    }


    /**
     * 修改部门信息
     *
     * @param department
     * @return
     */
    @ApiOperation(value = "根据ID修改部门接口")
    @PutMapping("/depUpdate")
    @ResponseBody
    public Map<String, Object> update(Department department, Integer roleId) {
        System.out.println("----------------进入修改部门信息模式------------------");

        department.setUpdateTime(TimeUtils.dateTime());

        Integer flag = departmentService.updateDepartment(department, roleId);

        if (flag > 0) {
            log.info("修改成功");
            outMap.put("code", "200");
            outMap.put("msg", "修改成功");
        } else {
            outMap.put("code", "100");
            log.info("修改失败");
            outMap.put("msg", "修改失败");
        }
        return outMap;
    }


    /**
     * 批量删除部门信息
     * 状态为1 :已删除
     *
     * @param isDel
     * @param departmentId
     * @return
     */
    @PutMapping("delBatch")
    @ApiOperation(value = "批量删除部门信息")
    public ResultFormat delBatchLink(Integer isDel, @RequestParam(value = "departmentId[]") Integer[] departmentId) {
        log.info("isDel={}", isDel);

        Department department = new Department();
        department.setIsDel(1);
        boolean updateById = false;
        for (Integer integer : departmentId) {
            department.setDepartmentId(integer);
            updateById = departmentService.updateById(department);
        }
        if (updateById) {
            return ResultUtil.success();
        }
        return ResultUtil.error(100, "修改失败");
    }


}

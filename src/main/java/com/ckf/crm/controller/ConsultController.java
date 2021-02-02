package com.ckf.crm.controller;


import com.ckf.crm.entity.Consult;
import com.ckf.crm.entity.Gambit;
import com.ckf.crm.model.ResultFormat;
import com.ckf.crm.service.ConsultService;
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
 * @author 安详的苦丁茶
 * @since 2020-03-27
 */

@Api(tags = "咨询信息管理")
@RestController
@RequestMapping("/con")
public class ConsultController {

    /**
     * 日志
     */
    private Logger log = LoggerFactory.getLogger(EmployeeController.class);


    Map<String, Object> outMap = new HashMap<String, Object>();


    @Autowired
    private ConsultService consultService;


    /**
     * 全查询咨询管理信息
     *
     * @param page
     * @param limit
     * @return
     */
    @ApiOperation("查询咨询信息接口")
    @GetMapping("/consult")
    public Map<String, Object> goDepartment(@RequestParam("page") Integer page, @RequestParam("limit") Integer limit) {

        System.out.println("---------------进入咨询信息全查询模式------------------");

        PageHelper.startPage(page, limit);

        List<Consult> list = consultService.selectConsultAll();

        if (list != null) {

            log.info("查询成功");
            outMap.put("msg", "查询成功");
            outMap.put("code", 0);

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
     * 模糊查询咨询人姓名
     *
     * @param page
     * @param limit
     * @param request
     * @return
     */
    @ApiOperation(value = "模糊查询咨询姓名接口")
    @GetMapping("/conSearchName")
    @ResponseBody
    public Map<String, Object> searchName(@RequestParam("page") Integer page, @RequestParam("limit") Integer limit, HttpServletRequest request) {

        System.out.println("-------------------进入模糊查询咨询姓名模式------------------");

        System.out.println(page + " -- " + limit);
        PageHelper.startPage(page, limit);

        String cName = request.getParameter("nameSearch");
        System.out.println("关键字:" + cName);

        List<Consult> list = consultService.selectConsultName(cName);

        if (list != null) {
            outMap.put("Consult", list);
            outMap.put("fuzzyQuerySucceed ", "模糊查询成功");
            outMap.put("code", 0);
            log.info("模糊查询成功");

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
     * 删除咨询信息
     *
     * @param consultId
     * @return
     */
    @ApiOperation("根据ID删除咨询信息接口")
    @ApiImplicitParam(name = "cId", value = "ID", dataType = "int")
    @DeleteMapping("/conDelete")
    public Map<String, Object> delete(Integer consultId) {
        System.out.println("----------------进入咨询信息删除信息模式-----------------");

        Consult consult = new Consult();
        consult.setConsultId(consultId);
        consult.setIsDel(1);

        boolean flag = consultService.updateById(consult);

        if (flag) {
            log.info("consultId--" + consultId);
            outMap.put("code", "200");
            log.info("删除成功");
            outMap.put("msg", "删除成功");
            return outMap;

        } else {
            log.info("删除失败");
            outMap.put("code", "100");
            outMap.put("msg", "删除失败");
        }
        return outMap;
    }


    /**
     * 添加咨询信息
     *
     * @param consult
     * @return
     */
    @ApiOperation("根据id添加咨询信息接口")
    @PostMapping(path = "/conAdd")
    @ResponseBody
    public Map<String, Object> add(Consult consult) {
        System.out.println("------------进入咨询信息添加信息模式--------------");

        consult.setCreateTime(TimeUtils.dateTime());
        consult.setUpdateTime(TimeUtils.dateTime());
        consult.setIsDel(0);

        boolean flag = consultService.save(consult);

        if (flag) {
            outMap.put("code", "200");
            outMap.put("msg", "添加成功");
            log.info("添加成功");
        } else {
            outMap.put("msg", "添加失败");
            outMap.put("code", "100");
            log.info("添加失败");
        }
        return outMap;
    }


    /**
     * 修改咨询信息
     *
     * @param consult
     * @return
     */
    @ApiOperation(value = "根据id修改咨询信息接口")
    @PutMapping("/conUpdate")
    @ResponseBody
    public Map<String, Object> update(Consult consult) {
        System.out.println("----------------进入咨询信息修改信息模式------------------");
        consult.setUpdateTime(TimeUtils.dateTime());
        consult.setIsDel(0);

        boolean flag = consultService.updateById(consult);

        if (flag) {
            outMap.put("code", "200");
            outMap.put("msg", "修改成功");
            log.info("修改成功");
        } else {
            log.info("修改失败");
            outMap.put("code", "100");
            outMap.put("msg", "修改失败");
        }
        return outMap;
    }

    /**
     * 批量删除咨询信息
     * 状态为1 :已删除
     *
     * @param isDel
     * @param consultId
     * @return
     */
    @PutMapping("delBatch")
    @ApiOperation(value = "批量删除咨询信息")
    public ResultFormat delBatchLink(Integer isDel, @RequestParam(value = "consultId[]") Integer[] consultId) {
        log.info("isDel={}", isDel);

        Consult consult = new Consult();
        consult.setIsDel(1);
        boolean updateById = false;
        for (Integer integer : consultId) {
            consult.setConsultId(integer);
            updateById = consultService.updateById(consult);
        }
        if (updateById) {
            return ResultUtil.success();
        }
        return ResultUtil.error(100, "修改失败");
    }


}

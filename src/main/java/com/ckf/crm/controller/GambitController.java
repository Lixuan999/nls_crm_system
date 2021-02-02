package com.ckf.crm.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ckf.crm.entity.Gambit;
import com.ckf.crm.model.ResultFormat;
import com.ckf.crm.model.ResultFormatPaging;
import com.ckf.crm.service.GambitService;
import com.ckf.crm.utils.ResultPagingUtil;
import com.ckf.crm.utils.ResultUtil;
import com.ckf.crm.utils.TimeUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
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

@Api(tags = "话题信息管理")
@RestController
@RequestMapping("/gam")
public class GambitController {

    /**
     * 日志
     */
    private Logger log = LoggerFactory.getLogger(EmployeeController.class);

    Map<String, Object> outMap = new HashMap<String, Object>();

    @Autowired
    private GambitService gambitService;

    /**
     * 分页全查询文章信息
     *
     * @return
     */
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "page", value = "当前页", required = true, dataType = "Integer", defaultValue = "1"),
            @ApiImplicitParam(name = "limit", value = "每页记录数", required = true, dataType = "Integer", defaultValue = "5")
    })

    /**
     * 全查询文章信息
     *
     * @return
     */
    @ApiOperation("查询话题信息接口")
    @GetMapping("/gambit")
    public ResultFormatPaging goDepartment(Gambit gambit, Integer page, Integer limit) {
        log.info("gambit={}", gambit);
        IPage<Gambit> pgSortsListIPage = gambitService.selectGambitAll(gambit, page, limit);
        List<Gambit> pgLabelsList = pgSortsListIPage.getRecords();
        log.info("pgLabelsList={}", pgLabelsList);
        return ResultPagingUtil.pagingSuccess(0, pgSortsListIPage.getTotal(), pgLabelsList);
    }


    /**
     * 模糊查询话题创建人姓名
     *
     * @param page
     * @param limit
     * @param request
     * @return
     */
    @ApiOperation(value = "模糊查询话题创建人接口")
    @GetMapping("/gamSearchName")
    @ResponseBody
    public Map<String, Object> searchName(@RequestParam("page") Integer page, @RequestParam("limit") Integer limit, HttpServletRequest request) {

        System.out.println("-------------------进入模糊查询话题创建人姓名模式------------------");

        System.out.println(page + " -- " + limit);
        PageHelper.startPage(page, limit);

        String gName = request.getParameter("nameSearch");
        System.out.println("关键字:" + gName);

        List<Gambit> list = gambitService.selectGambitName(gName);

        if (list != null) {
            log.info("模糊查询成功");
            outMap.put("code", 0);
            outMap.put("Gambit", list);
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
     * 删除话题信息
     *
     * @param gambitId
     * @return
     */
    @ApiOperation("根据ID删除话题信息接口")
    @ApiImplicitParam(name = "gId", value = "ID", dataType = "int")
    @DeleteMapping("/gamDelete")
    public Map<String, Object> delete(Integer gambitId) {
        System.out.println("----------------进入删除话题信息模式-----------------");

        Gambit gambit = new Gambit();
        gambit.setGambitId(gambitId);
        gambit.setIsDel(1);

        boolean flag = gambitService.updateById(gambit);

        if (flag) {
            log.info("删除成功");
            outMap.put("code", "200");
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
     * 添加文章信息
     *
     * @param gambit
     * @return
     */
    @ApiOperation("根据id添加文章信息接口")
    @PostMapping(path = "/gamAdd")
    @ResponseBody
    public ResultFormat add(Gambit gambit) {
        System.out.println("------------进入添加话题信息模式--------------");

        gambit.setCreateTime(TimeUtils.dateTime());
        gambit.setUpdateTime(TimeUtils.dateTime());
        gambit.setIsDel(0);

        boolean insert = gambitService.save(gambit);
        if (insert) {
            log.info("添加成功");
            return ResultUtil.success();
        } else {
            log.info("添加失败");
            return ResultUtil.error(100, "添加失败");
        }
    }


    /**
     * 修改文章信息
     *
     * @param gambit
     * @return
     */
    @ApiOperation(value = "根据ID修改文章信息接口")
    @PutMapping("/gamUpdate")
    @ResponseBody
    public ResultFormat update(Gambit gambit) {
        System.out.println("----------------进入修改话题信息模式------------------");

        Integer flag = gambitService.updateGambit(gambit);
        if (flag>0) {
            log.info("修改成功");
            return ResultUtil.success();
        } else {
            log.info("修改失败");
            return ResultUtil.error(100, "修改失败");
        }

    }

    /**
     * 修改文章状态
     *
     * @param gambit
     * @return
     */
    @ApiOperation("修改文章状态接口")
    @PutMapping("/delete")
    public ResultFormat deletePgSorts(Gambit gambit) {

        log.info("gambit=={}", gambit);

        boolean flag = gambitService.updateById(gambit);

        if (flag) {
            log.info("禁用成功");
            return ResultUtil.success();
        } else {
            log.info("修改失败");
            return ResultUtil.error(100, "禁用失败");
        }
    }

    /**
     * 批量删除话题信息
     * 状态为1 :已删除
     *
     * @param IsDel
     * @param gambitId
     * @return
     */
    @PutMapping("/delBatchGambit")
    @ApiOperation(value = "批量删除话题信息")
    public ResultFormat delBatchLink(Integer IsDel, @RequestParam(value = "gambitId[]") Integer[] gambitId) {
        log.info("IsDel={}", IsDel);

        Gambit gambit = new Gambit();
        gambit.setIsDel(1);
        boolean updateById = false;

        for (Integer integer : gambitId) {
            gambit.setGambitId(integer);
            updateById = gambitService.updateById(gambit);
        }
        if (updateById) {
            return ResultUtil.success();
        }
        return ResultUtil.error(100, "修改失败");
    }


}

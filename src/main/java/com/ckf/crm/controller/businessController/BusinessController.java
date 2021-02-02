package com.ckf.crm.controller.businessController;


import com.ckf.crm.controller.EmployeeController;
import com.ckf.crm.entity.Department;
import com.ckf.crm.entity.businessEntity.Business;
import com.ckf.crm.service.businessService.BusinessService;
import com.ckf.crm.utils.TimeUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author xuan
 * @since 2020-04-05
 */
@RestController
@RequestMapping("/bus")
public class BusinessController {

    /**
     * 日志
     */
    private Logger log = LoggerFactory.getLogger(EmployeeController.class);


    @Autowired
    private BusinessService businessService;


    /**
     * 查询业务信息
     *
     * @return
     */
    @GetMapping("/goBusiness")
    public List<Business> goBusiness() {

        return businessService.selectBusinessList();
    }


    /**
     * 全查询业务信息
     *
     * @param page
     * @param limit
     * @return
     */
    @ApiOperation("查询业务信息接口")
    @GetMapping("/business")
    public Map<String, Object> list(@RequestParam("page") Integer page, @RequestParam("limit") Integer limit) {

        System.out.println("---------------进入业务信息全查询模式------------------");
        Map<String, Object> outMap = new HashMap<String, Object>();

        PageHelper.startPage(page, limit);

        List<Business> list = businessService.selectBusinessList();

        if (list != null) {
            outMap.put("code", 0);
            log.info("查询成功");
            outMap.put("msg", "查询成功");

            PageInfo pageInfo = new PageInfo(list);
            outMap.put("count", pageInfo.getTotal());
            outMap.put("data", pageInfo.getList());
        } else {
            outMap.put("code", 100);
            outMap.put("msg", "查询失败");
            log.info("查询失败");
        }

        return outMap;
    }

    /**
     * 修改业务状态
     *
     * @param business
     * @return
     */
    @ApiOperation("修改业务状态接口")
    @PutMapping("/delete")
    public Map<String, Object> deleteBusiness(Business business) {
        Map<String, Object> outMap = new HashMap<String, Object>();
        log.info("business=={}", business);

        boolean flag = businessService.updateById(business);

        if (flag) {
            log.info("禁用成功");
        } else {
            log.info("禁用失败");
        }
        return outMap;
    }

    /**
     * 删除业务信息(修改状态)
     *
     * @param businessId
     * @return
     */
    @ApiOperation("根据ID删除业务信息接口")
    @ApiImplicitParam(name = "bId", value = "ID", dataType = "int")
    @DeleteMapping("/busDelete")
    public Map<String, Object> delete(Integer businessId) {
        System.out.println("----------------进入删除业务信息模式-----------------");
        Map<String, Object> outMap = new HashMap<String, Object>();

        //获取对象修改是否删除状态
        Business business = new Business();
        business.setBusinessId(businessId);
        business.setIsDel(1);

        boolean flag = businessService.updateById(business);

        if (flag) {
            outMap.put("code", "200");
            log.info("删除成功");
            System.out.println("id--" + businessId);
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
     * 添加业务信息
     *
     * @param business
     * @return
     */
    @ApiOperation("根据ID添加业务接口")
    @PostMapping(path = "/busAdd")
    @ResponseBody
    public Map<String, Object> add(Business business) {
        System.out.println("------------进入添加业务信息模式--------------");
        Map<String, Object> outMap = new HashMap<String, Object>();

        business.setCreateTime(TimeUtils.dateTime());
        business.setUpdateTime(TimeUtils.dateTime());
        business.setIsDel(0);

        boolean flag = businessService.save(business);

        if (flag) {
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
     * 修改业务信息
     *
     * @param business
     * @return
     */
    @ApiOperation(value = "根据ID修改业务信息接口")
    @PutMapping("/busUpdate")
    @ResponseBody
    public Map<String, Object> update(Business business) {
        System.out.println("----------------进入修改业务信息模式------------------");
        Map<String, Object> outMap = new HashMap<String, Object>();

        business.setUpdateTime(TimeUtils.dateTime());

        boolean flag = businessService.updateById(business);

        if (flag) {
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

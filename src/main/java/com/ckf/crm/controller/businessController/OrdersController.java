package com.ckf.crm.controller.businessController;


import com.ckf.crm.controller.EmployeeController;
import com.ckf.crm.entity.Consult;
import com.ckf.crm.entity.businessEntity.Orders;
import com.ckf.crm.entity.customerEntity.Customer;
import com.ckf.crm.model.ResultFormat;
import com.ckf.crm.service.businessService.OrdersService;
import com.ckf.crm.utils.ResultUtil;
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
 * @since 2021-04-05
 */
@RestController
@RequestMapping("/ord")
public class OrdersController {

    private Logger log = LoggerFactory.getLogger(EmployeeController.class);


    @Autowired
    private OrdersService ordersService;


    /**
     * 全查询订单信息
     *
     * @param page
     * @param limit
     * @return
     */
    @ApiOperation("查询订单信息接口")
    @GetMapping("/orders")
    public Map<String, Object> list(@RequestParam("page") Integer page, @RequestParam("limit") Integer limit) {
        System.out.println("---------------进入订单信息全查询模式------------------");
        Map<String, Object> outMap = new HashMap<String, Object>();

        PageHelper.startPage(page, limit);

        List<Orders> list = ordersService.selectOrdersList();

        if (list != null) {
            outMap.put("code", 0);
            outMap.put("msg", "查询成功");
            log.info("查询成功");

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
     * 修改订单状态
     *
     * @param orders
     * @return
     */
    @ApiOperation("修改订单状态接口")
    @PutMapping("/delete")
    public Map<String, Object> deleteBusiness(Orders orders) {
        log.info("business=={}", orders);
        Map<String, Object> outMap = new HashMap<String, Object>();

        boolean flag = ordersService.updateById(orders);

        if (flag) {
            log.info("修改成功");
            return outMap;
        } else {
            log.info("修改失败");
            return outMap;
        }
    }

    /**
     * 删除订单信息(修改状态)
     *
     * @param ordersId
     * @return
     */
    @ApiOperation("根据ID删除订单信息接口")
    @ApiImplicitParam(name = "bId", value = "ID", dataType = "int")
    @DeleteMapping("/ordDelete")
    public Map<String, Object> delete(Integer ordersId) {
        System.out.println("----------------进入删除订单信息模式-----------------");
        Map<String, Object> outMap = new HashMap<String, Object>();

        //获取对象修改是否删除状态
        Orders orders = new Orders();
        orders.setOrdersId(ordersId);
        orders.setIsDel(1);

        boolean flag = ordersService.updateById(orders);

        if (flag) {
            outMap.put("code", "200");
            System.out.println("id--" + ordersId);
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
     * 添加订单信息
     *
     * @param orders
     * @param busId
     * @param cusId
     * @return
     */
    @ApiOperation("根据添加订单信息接口")
    @PostMapping(path = "/ordAdd")
    @ResponseBody
    public Map<String, Object> add(Orders orders, Integer busId, Integer cusId) {
        System.out.println("------------进入添加订单信息模式--------------");
        Map<String, Object> outMap = new HashMap<String, Object>();

        System.out.println("busId--" + busId);
        System.out.println("cusId--" + cusId);

        Integer flag = ordersService.addOrders(orders, busId, cusId);

        if (flag > 0) {
            log.info("添加成功");
            outMap.put("code", "200");
            outMap.put("msg", "添加成功");
        } else {
            log.info("添加失败");
            outMap.put("code", "100");
            outMap.put("msg", "添加失败");
        }
        return outMap;
    }


    /**
     * 修改订单信息
     *
     * @param orders
     * @param busId
     * @param cusId
     * @return
     */
    @ApiOperation(value = "根据ID修改订单接口")
    @PutMapping("/ordUpdate")
    @ResponseBody
    public Map<String, Object> update(Orders orders, Integer busId, Integer cusId) {
        System.out.println("----------------进入修改订单信息模式------------------");
        Map<String, Object> outMap = new HashMap<String, Object>();

        System.out.println("busId--" + busId);
        System.out.println("cusId--" + cusId);

        orders.setUpdateTime(TimeUtils.dateTime());

        Integer flag = ordersService.updateOrders(orders, busId, cusId);

        if (flag > 0) {
            log.info("修改成功");
            outMap.put("code", "200");
            outMap.put("msg", "修改成功");
        } else {
            log.info("修改失败");
            outMap.put("code", "100");
            outMap.put("msg", "修改失败");
        }
        return outMap;
    }

    /**
     * 批量删除订单信息
     * 状态为1 :已删除
     *
     * @param isDel
     * @param ordersId
     * @return
     */
    @PutMapping("delBatch")
    @ApiOperation(value = "批量删除订单信息")
    public ResultFormat delBatchLink(Integer isDel, @RequestParam(value = "ordersId[]") Integer[] ordersId) {
        log.info("isDel={}", isDel);

        Orders orders = new Orders();
        orders.setIsDel(1);
        boolean updateById = false;
        for (Integer integer : ordersId) {
            orders.setOrdersId(integer);
            updateById = ordersService.updateById(orders);
        }
        if (updateById) {
            return ResultUtil.success();
        }
        return ResultUtil.error(100, "修改失败");
    }

}

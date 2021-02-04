package com.ckf.crm.controller.customerController;


import com.ckf.crm.controller.EmployeeController;
import com.ckf.crm.entity.Department;
import com.ckf.crm.entity.Permission;
import com.ckf.crm.entity.Role;
import com.ckf.crm.entity.businessEntity.Business;
import com.ckf.crm.entity.customerEntity.Contact;
import com.ckf.crm.entity.customerEntity.Customer;
import com.ckf.crm.model.ResultFormat;
import com.ckf.crm.service.customerService.ContactService;
import com.ckf.crm.service.customerService.CustomerService;
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
 * @since 2021-04-05
 */
@RestController
@RequestMapping("/cus")
public class CustomerController {

    private Logger log = LoggerFactory.getLogger(EmployeeController.class);


    @Autowired
    private CustomerService customerService;


    /**
     * 查询客户信息
     *
     * @return
     */
    @GetMapping("/goCustomer")
    public List<Customer> goCustomer() {

        return customerService.selectCustomerList();
    }

    /**
     * 全查询客户信息
     *
     * @param page
     * @param limit
     * @return
     */
    @ApiOperation("查询客户信息接口")
    @GetMapping("/customer")
    public Map<String, Object> list(@RequestParam("page") Integer page, @RequestParam("limit") Integer limit) {
        System.out.println("---------------进入客户全查询模式------------------");
        Map<String, Object> outMap = new HashMap<String, Object>();

        PageHelper.startPage(page, limit);

        List<Customer> list = customerService.selectCustomerList();

        if (list != null) {
            log.info("查询成功");
            outMap.put("code", 0);
            outMap.put("msg", "查询成功");

            PageInfo pageInfo = new PageInfo(list);
            outMap.put("count", pageInfo.getTotal());
            outMap.put("data", pageInfo.getList());
        } else {
            outMap.put("code", 100);
            log.info("查询失败");
            outMap.put("msg", "查询失败");

        }

        return outMap;
    }


    /**
     * 模糊查询客户名称
     *
     * @param page
     * @param limit
     * @param request
     * @return
     */
    @ApiOperation(value = "模糊查询客户名称接口")
    @GetMapping("/cusSearchName")
    @ResponseBody
    public Map<String, Object> searchName(@RequestParam("page") Integer page, @RequestParam("limit") Integer limit, HttpServletRequest request) {

        System.out.println("-------------------进入模糊查询客户名称模式------------------");

        Map<String, Object> outMap = new HashMap<String, Object>();
        System.out.println(page + " -- " + limit);
        PageHelper.startPage(page, limit);

        String name = request.getParameter("nameSearch");
        System.out.println("关键字:" + name);

        List<Customer> list = customerService.selectCustomerNameLike(name);

        if (list != null) {
            log.info("模糊查询成功");
            outMap.put("Customer", list);
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
     * 模糊查询正式客户名称
     *
     * @param page
     * @param limit
     * @param request
     * @return
     */
    @ApiOperation(value = "模糊查询正式客户名称接口")
    @GetMapping("/cusSearchNameOfficial")
    @ResponseBody
    public Map<String, Object> searchNameOfficial(@RequestParam("page") Integer page, @RequestParam("limit") Integer limit, HttpServletRequest request) {

        System.out.println("-------------------进入模糊查询正式客户名称模式------------------");

        Map<String, Object> outMap = new HashMap<String, Object>();
        System.out.println(page + " -- " + limit);
        PageHelper.startPage(page, limit);

        String name = request.getParameter("nameSearch");
        System.out.println("关键字:" + name);

        List<Customer> list = customerService.selectCustomerOfficialLike(name);

        if (list != null) {
            log.info("模糊查询成功");
            outMap.put("Customer", list);
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
     * 查询正式客户
     *
     * @param page
     * @param limit
     * @return
     */
    @ApiOperation("查询正式客户信息接口")
    @GetMapping("/offCustomerList")
    public Map<String, Object> offCustomer(@RequestParam("page") Integer page, @RequestParam("limit") Integer limit) {
        System.out.println("---------------进入正式客户全查询模式------------------");
        Map<String, Object> outMap = new HashMap<String, Object>();

        PageHelper.startPage(page, limit);

        List<Customer> list = customerService.selectOfficialCustomer();

        if (list != null) {
            outMap.put("code", 0);
            outMap.put("msg", "查询成功");
            log.info("查询成功");

            PageInfo pageInfo = new PageInfo(list);
            outMap.put("count", pageInfo.getTotal());
            outMap.put("data", pageInfo.getList());
        } else {
            outMap.put("code", 100);
            log.info("查询失败");
            outMap.put("msg", "查询失败");

        }
        return outMap;
    }


    /**
     * 修改客户状态
     *
     * @param customer
     * @return
     */
    @ApiOperation("修改客户状态接口")
    @PutMapping("/delete")
    public Map<String, Object> deleteBusiness(Customer customer) {
        Map<String, Object> outMap = new HashMap<String, Object>();
        log.info("customer=={}", customer);

        boolean flag = customerService.updateById(customer);

        if (flag) {
            log.info("禁用成功");
            return outMap;
        } else {
            log.info("禁用失败");
            return outMap;
        }
    }


    /**
     * 删除客户信息(修改状态)
     *
     * @param customerId
     * @return
     */
    @ApiOperation("根据ID删除客户信息接口")
    @ApiImplicitParam(name = "bId", value = "ID", dataType = "int")
    @DeleteMapping("/cusDelete")
    public Map<String, Object> delete(Integer customerId) {
        System.out.println("----------------进入删除客户信息模式-----------------");
        Map<String, Object> outMap = new HashMap<String, Object>();

        //获取对象修改是否删除状态
        Customer customer = new Customer();
        customer.setCustomerId(customerId);
        customer.setIsDel(1);

        boolean flag = customerService.updateById(customer);

        if (flag) {
            outMap.put("code", "200");
            log.info("删除成功");
            System.out.println("id--" + customerId);
            outMap.put("msg", "删除成功");
            return outMap;

        } else {
            outMap.put("msg", "删除失败");
            outMap.put("code", "100");
            log.info("删除失败");
        }
        return outMap;
    }

    /**
     * 删除客户信息(修改状态)
     *
     * @param customerId
     * @return
     */
    @ApiOperation("根据ID删除客户信息接口")
    @ApiImplicitParam(name = "bId", value = "ID", dataType = "int")
    @DeleteMapping("/cusDeleteOff")
    public Map<String, Object> deleteOfficial(Integer customerId) {
        System.out.println("----------------进入删除客户信息模式-----------------");
        Map<String, Object> outMap = new HashMap<String, Object>();

        //获取对象修改是否删除状态
        Customer customer = new Customer();
        customer.setCustomerId(customerId);
        customer.setIsOrders(0);

        boolean flag = customerService.updateById(customer);

        if (flag) {
            outMap.put("code", "200");
            log.info("删除成功");
            outMap.put("msg", "删除成功");
            return outMap;
        }
        outMap.put("msg", "删除失败");
        outMap.put("code", "100");
        log.info("删除失败");

        return outMap;
    }

    /**
     * 添加客户信息
     *
     * @param customer
     * @param empId
     * @return
     */
    @ApiOperation("添加客户信息接口")
    @PostMapping(path = "/cusAdd")
    @ResponseBody
    public Map<String, Object> add(Customer customer, Integer empId) {
        System.out.println("------------进入添加客户信息模式--------------");
        Map<String, Object> outMap = new HashMap<String, Object>();

        System.out.println("customer--" + customer);
        System.out.println("empId--" + empId);

        Integer flag = customerService.addCustomer(customer, empId);

        if (flag > 0) {
            outMap.put("code", "200");
            outMap.put("msg", "添加成功");
            log.info("添加成功");
        } else {
            outMap.put("code", "100");
            outMap.put("msg", "添加失败");
            log.info("添加失败");
        }
        return outMap;
    }


    /**
     * 修改客户信息
     *
     * @param empId
     * @return
     */
    @ApiOperation(value = "根据ID修改客户接口")
    @PutMapping("/cusUpdate")
    @ResponseBody
    public Map<String, Object> update(Customer customer, Integer empId) {
        System.out.println("----------------进入修改客户信息模式------------------");
        Map<String, Object> outMap = new HashMap<String, Object>();

        System.out.println("customer--" + customer);
        System.out.println("empId--" + empId);

        customer.setUpdateTime(TimeUtils.dateTime());

        Integer flag = customerService.updateCustomer(customer, empId);

        if (flag > 0) {
            outMap.put("code", "200");
            log.info("修改成功");
            outMap.put("msg", "修改成功");
        } else {
            outMap.put("code", "100");
            outMap.put("msg", "修改失败");
            log.info("修改失败");
        }
        return outMap;
    }


    /**
     * 批量删除客户信息
     * 状态为1 :已删除
     *
     * @param isDel
     * @param customerId
     * @return
     */
    @PutMapping("delBatch")
    @ApiOperation(value = "批量删除客户信息")
    public ResultFormat delBatchLink(Integer isDel, @RequestParam(value = "customerId[]") Integer[] customerId) {
        log.info("isDel={}", isDel);

        Customer customer = new Customer();
        customer.setIsDel(1);
        boolean updateById = false;
        for (Integer integer : customerId) {
            customer.setCustomerId(integer);
            updateById = customerService.updateById(customer);
        }
        if (updateById) {
            return ResultUtil.success();
        }
        return ResultUtil.error(100, "修改失败");
    }

}

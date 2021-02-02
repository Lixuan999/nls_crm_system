package com.ckf.crm.controller.customerController;


import com.ckf.crm.controller.EmployeeController;
import com.ckf.crm.entity.customerEntity.Contact;
import com.ckf.crm.entity.customerEntity.Customer;
import com.ckf.crm.model.ResultFormat;
import com.ckf.crm.service.customerService.ContactService;
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
 * @since 2020-04-05
 */
@RestController
@RequestMapping("/cont")
public class ContactController {

    private Logger log = LoggerFactory.getLogger(EmployeeController.class);


    @Autowired
    private ContactService contactService;


    /**
     * 全查询客户跟踪信息
     *
     * @param page
     * @param limit
     * @return
     */
    @ApiOperation("查询客户跟踪信息接口")
    @GetMapping("/contact")
    public Map<String, Object> list(@RequestParam("page") Integer page, @RequestParam("limit") Integer limit) {
        System.out.println("---------------进入客户跟踪全查询模式------------------");
        Map<String, Object> outMap = new HashMap<String, Object>();

        PageHelper.startPage(page, limit);

        List<Contact> list = contactService.selectContactList();

        if (list != null) {
            outMap.put("code", 0);
            log.info("查询成功");
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
     * 删除客户跟踪信息(修改状态)
     *
     * @param contactId
     * @return
     */
    @ApiOperation("根据ID删除客户跟踪信息接口")
    @ApiImplicitParam(name = "bId", value = "ID", dataType = "int")
    @DeleteMapping("/contDelete")
    public Map<String, Object> delete(Integer contactId) {
        System.out.println("----------------进入删除客户跟踪信息模式-----------------");
        Map<String, Object> outMap = new HashMap<String, Object>();

        //获取对象修改是否删除状态
        Contact contact = new Contact();
        contact.setContactId(contactId);
        contact.setIsDel(1);

        boolean flag = contactService.updateById(contact);

        if (flag) {
            outMap.put("code", "200");
            System.out.println("id--" + contactId);
            outMap.put("msg", "删除成功");
            log.info("删除成功");
            return outMap;

        } else {
            outMap.put("msg", "删除失败");
            outMap.put("code", "100");
            log.info("删除失败");
        }
        return outMap;
    }


    /**
     * 添加客户跟踪信息
     *
     * @param contact
     * @param empId
     * @param cusId
     * @return
     */
    @ApiOperation("根据添加客户跟踪信息接口")
    @PostMapping(path = "/contAdd")
    @ResponseBody
    public Map<String, Object> add(Contact contact, Integer empId, Integer cusId) {
        System.out.println("------------进入添加客户跟踪信息模式--------------");
        Map<String, Object> outMap = new HashMap<String, Object>();

        System.out.println("empId--" + empId);
        System.out.println("cusId--" + cusId);

        Integer flag = contactService.addContact(contact, empId, cusId);

        if (flag > 0) {
            outMap.put("code", "200");
            outMap.put("msg", "添加成功");
            log.info("添加成功");
        } else {
            log.info("添加失败");
            outMap.put("code", "100");
            outMap.put("msg", "添加失败");
        }
        return outMap;
    }


    /**
     * 修改客户跟踪信息
     *
     * @param contact
     * @param empId
     * @param cusId
     * @return
     */
    @ApiOperation(value = "根据ID修改客户跟踪信息接口")
    @PutMapping("/contUpdate")
    @ResponseBody
    public Map<String, Object> update(Contact contact, Integer empId, Integer cusId) {
        System.out.println("----------------进入修改客户跟踪信息模式------------------");
        Map<String, Object> outMap = new HashMap<String, Object>();

        System.out.println("empId--" + empId);
        System.out.println("cusId--" + cusId);

        contact.setUpdateTime(TimeUtils.dateTime());

        Integer flag = contactService.updateContact(contact, empId, cusId);

        if (flag > 0) {
            outMap.put("code", "200");
            log.info("修改成功");
            outMap.put("msg", "修改成功");
        } else {
            log.info("修改失败");
            outMap.put("code", "100");
            outMap.put("msg", "修改失败");
        }
        return outMap;
    }


    /**
     * 批量删除客户跟踪信息
     * 状态为1 :已删除
     *
     * @param isDel
     * @param contactId
     * @return
     */
    @PutMapping("delBatch")
    @ApiOperation(value = "批量删除客户跟踪信息")
    public ResultFormat delBatchLink(Integer isDel, @RequestParam(value = "contactId[]") Integer[] contactId) {
        log.info("isDel={}", isDel);

        Contact contact = new Contact();
        contact.setIsDel(1);
        boolean updateById = false;
        for (Integer integer : contactId) {
            contact.setContactId(integer);
            updateById = contactService.updateById(contact);
        }
        if (updateById) {
            return ResultUtil.success();
        }
        return ResultUtil.error(100, "修改失败");
    }


}

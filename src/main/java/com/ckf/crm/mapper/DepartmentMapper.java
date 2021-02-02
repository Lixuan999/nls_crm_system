package com.ckf.crm.mapper;

import com.ckf.crm.entity.Department;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface DepartmentMapper extends BaseMapper<Department> {

    /**
     * 全查询部门信息
     *
     * @return
     */
    List<Department> selectDepartmentAll();

    /**
     * 模糊查询部门经理
     *
     * @param dName
     * @return
     */
    List<Department> selectDepartmentName(String dName);


    /**
     * 批量删除
     * @param dids
     */
    /*  public void deleteDepartmentAll(List<String> dids);
     */
}

package com.ckf.crm.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ckf.crm.entity.Employee;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface EmployeeMapper extends BaseMapper<Employee> {


    /**
     * 分页全查询
     *
     * @return
     */
    IPage<Employee> selectList(Page<Employee> page);

    /**
     * 查询已删除员工
     *
     * @return
     */
    List<Employee> selectEmployeeDel();

    /**
     * 查询管理员
     *
     * @return
     */
    List<Employee> selectAdmin();

    /**
     * 根据员工姓名模糊查询
     *
     * @param empName
     * @return
     */
    List<Employee> selectEmployeeNameLike(String empName);


    /**
     * 模糊查询已删除员工
     *
     * @param empName
     * @return
     */
    List<Employee> selectEmployeeNameLikeDel(String empName);

    /**
     * 根据用户查询 授权
     *
     * @param userName
     * @return
     */
    Employee selectByUserName(String userName);


    /**
     * 查询用户名是否存在
     *
     * @param empName
     * @return
     */
    Employee selectName(@Param("empName") String empName);

    /**
     * 修改密码
     *
     * @param employee
     * @return
     */
    boolean updatePassword(Employee employee);


}

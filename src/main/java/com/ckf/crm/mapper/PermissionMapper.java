package com.ckf.crm.mapper;

import com.ckf.crm.entity.Permission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ckf.crm.model.ResultFormat;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author PIGS
 * @since 2020-03-23
 */

@Repository
public interface PermissionMapper extends BaseMapper<Permission> {

    /**
     * 全查询权限信息
     *
     * @return
     */
    List<Permission> selectPermissionAll();

    /**
     * 查询url是否存在
     *
     * @param url
     * @return
     */
    Permission selectPermissionUrl(@Param("url") String url);

    /**
     * 添加权限
     *
     * @param permission
     * @return
     */
    ResultFormat savePermission(Permission permission);

    /**
     * 修改权限
     *
     * @param permission
     * @return
     */
    Integer updatePermission(Permission permission);

    /**
     * 模糊查询
     *
     * @param pName
     * @return
     */
    List<Permission> selectPermissionNameLike(String pName);


}

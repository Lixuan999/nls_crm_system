package com.ckf.crm.service;

import com.ckf.crm.entity.Permission;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ckf.crm.model.ResultFormat;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author xuan
 * @since 2020-03-23
 */
public interface PermissionService extends IService<Permission> {

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

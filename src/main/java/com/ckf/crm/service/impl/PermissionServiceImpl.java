package com.ckf.crm.service.impl;

import com.baomidou.mybatisplus.core.conditions.AbstractWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ckf.crm.entity.Permission;
import com.ckf.crm.mapper.PermissionMapper;
import com.ckf.crm.model.ResultFormat;
import com.ckf.crm.service.PermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ckf.crm.utils.RedisUtil;
import com.ckf.crm.utils.ResultUtil;
import com.ckf.crm.utils.TimeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author 安详的苦丁茶
 * @since 2020-03-23
 */
@Service
@Transactional
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService {

    private Logger logger = LoggerFactory.getLogger(PermissionService.class);

    @Autowired
    private PermissionMapper permissionMapper;

    @Autowired
    private RedisUtil redisUtil;

    /**
     * 全查询权限信息
     *
     * @return
     */
    @Override
    public List<Permission> selectPermissionAll() {
        return permissionMapper.selectPermissionAll();
    }

    /**
     * 查询权限url是否存在
     *
     * @param url
     * @return
     */
    @Override
    public Permission selectPermissionUrl(String url) {
        return permissionMapper.selectPermissionUrl(url);
    }

    /**
     * 添加权限
     *
     * @param permission
     * @return
     */
    @Override
    public ResultFormat savePermission(Permission permission) {
        System.out.println("--------------进入service实现层 添加权限----------------");
        /**
         * 查url是否存在
         * 如果存在那就返回提示已经存在
         * 如果不存在就执行添加
         */
        AbstractWrapper abstractWrapper = new QueryWrapper<Permission>();
        abstractWrapper.eq("url", permission.getUrl());
        Permission UsersWrapperGet = permissionMapper.selectOne(abstractWrapper);
        logger.info("pgLabelsWrapperGet={}", UsersWrapperGet);

        if (UsersWrapperGet != null) {
            return ResultUtil.error(101, "url已存在");
        }
        permission.setCreateTime(TimeUtils.dateTime());
        permission.setUpdateTime(TimeUtils.dateTime());
        permission.setIsDel(0);

        Integer insert = permissionMapper.insert(permission);

        if (insert > 0) {
            redisUtil.lSet("permissionId", permission.getPermissionId());
            redisUtil.set("permission:" + permission.getPermissionId(), permission);
        }
        return ResultUtil.success();
    }


    /**
     * 修改权限,判断url是否存在，如果存在，url不能改为与数据库一样的数据
     * 在判断url是否存在的同时，在查询url与数据库数据不一致的时，可以修改其它的信息
     *
     * @param permission
     * @return
     */
    @Override
    public Integer updatePermission(Permission permission) {
        System.out.println("--------------进入service实现层 添加权限----------------");
        /**
         * 查询url名称是否存在
         * 如果存在那就返回提示已经存在
         * 如果不存在那就修改
         */
        if (permission != null) {
            if (permission.getPermissionId() != null && !permission.getUrl().isEmpty()) {
                Permission PermissionByIdInfo = permissionMapper.selectById(permission.getPermissionId());
                AbstractWrapper wrapper = new QueryWrapper();
                wrapper.eq("url", permission.getUrl());
                Permission permissionUrlInfo = permissionMapper.selectOne(wrapper);

                /**
                 * 如果通过id查询的标签信息与前台传过来的名称一致
                 * 可为修改
                 */
                if (PermissionByIdInfo != null) {
                    logger.info("PermissionByIdInfo={}", PermissionByIdInfo);
                    if (PermissionByIdInfo.getUrl().equals(permission.getUrl())) {

                        permission.getCreateTime();
                        permission.setUpdateTime(TimeUtils.dateTime());

                        Integer updateById = permissionMapper.updateById(permission);
                        if (updateById > 0) {
                            redisUtil.set("permission:" + permission.getPermissionId(), permission);
                            return 200;
                        }
                    }
                }

                /**
                 * 从前台传过来的url名称与id查询的名称不一致
                 * 再通过名称查询的数据为空就可以修改
                 */
                if (permissionUrlInfo != null) {
                    logger.info("permissionUrlInfo={}", permissionUrlInfo);
                    if (!permissionUrlInfo.getUrl().equals(PermissionByIdInfo.getUrl())) {
                        return 101;
                    }
                }
            }
        }

        /**
         * 如果url名称不一致就修改成 不存在的url名称
         */
        Integer updateById = permissionMapper.updateById(permission);
        if (updateById > 0) {
            redisUtil.set("permission:" + permission.getPermissionId(), permission);
            return 200;
        }

        return 100;
    }


    /**
     * 模糊查询权限名称
     *
     * @param pName
     * @return
     */
    @Override
    public List<Permission> selectPermissionNameLike(String pName) {
        return permissionMapper.selectPermissionNameLike(pName);
    }


}

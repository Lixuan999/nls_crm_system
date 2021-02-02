package com.ckf.crm.service.impl;


import com.baomidou.mybatisplus.core.conditions.AbstractWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ckf.crm.entity.Gambit;
import com.ckf.crm.mapper.GambitMapper;
import com.ckf.crm.service.GambitService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ckf.crm.utils.RedisUtil;
import com.ckf.crm.utils.TimeUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xuan
 * @since 2020-03-27
 */
@Service
@Transactional
public class GambitServiceImpl extends ServiceImpl<GambitMapper, Gambit> implements GambitService {


    @Autowired
    private GambitMapper gambitMapper;

    @Autowired
    private RedisUtil redisUtil;

    /**
     * 全查询文章信息
     *
     * @return
     */
    @Override
    public IPage<Gambit> selectGambitAll(Gambit gambit, Integer page, Integer limit) {
        AbstractWrapper<Gambit, String, QueryWrapper<Gambit>> wrapper = new QueryWrapper<Gambit>();

        if (gambit != null) {
            if (!StringUtils.isEmpty(gambit.getGFounder())) {
                wrapper.like("g_founder", gambit.getGFounder());
            }
        }
        wrapper.eq("is_del", gambit.getIsDel());
        //根据id查询最新数据 降序
        wrapper.orderByDesc("gambit_id");
        Page<Gambit> iPage = new Page<Gambit>(page, limit);
        Page<Gambit> pgRolePage = (Page<Gambit>) gambitMapper.selectPage(iPage, wrapper);
        return pgRolePage;
    }

    /**
     * 模糊查询话题创建人姓名
     *
     * @param gName
     * @return
     */
    @Override
    public List<Gambit> selectGambitName(String gName) {
        return gambitMapper.selectGambitName(gName);
    }

    /**
     * 添加文章
     *
     * @param gambit
     * @return
     */
    @Override
    public Integer saveGambit(Gambit gambit) {
        System.out.println("--------------进入service实现层 添加文章----------------");

        gambit.setCreateTime(TimeUtils.dateTime());
        gambit.setUpdateTime(TimeUtils.dateTime());
        gambit.setIsDel(0);
        Integer insert = gambitMapper.insert(gambit);

        if (insert > 0) {
            redisUtil.lSet("gambitId:", gambit.getGambitId());
            redisUtil.set("gambit:" + gambit.getGambitId(), gambit);
        }
        return insert;
    }

    /**
     * 修改文章
     *
     * @param gambit
     * @return
     */
    @Override
    public Integer updateGambit(Gambit gambit) {
        System.out.println("--------------进入service实现层 修改文章----------------");

        gambit.getCreateTime();
        gambit.setUpdateTime(TimeUtils.dateTime());

        Integer updateById = gambitMapper.updateById(gambit);

        if (updateById > 0) {
            redisUtil.set("gambit:" + gambit.getGambitId(), gambit);
        }
        return updateById;
    }

    /**
     * 删除文章
     *
     * @param gId
     * @return
     */
    @Override
    public Integer deleteGambitId(Integer gId) {
        Integer deleteById = gambitMapper.deleteById(gId);

        if (deleteById > 0) {
            Long delete = redisUtil.delete("gambit:" + String.valueOf(gId));
            System.out.println("delete--" + delete);
        }
        return deleteById;
    }

}

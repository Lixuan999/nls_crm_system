package com.ckf.crm.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ckf.crm.entity.Gambit;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ckf.crm.model.ResultFormat;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author 安详的苦丁茶
 * @since 2020-03-27
 */
public interface GambitService extends IService<Gambit> {

    /**
     * 全查询文章信息
     *
     * @return
     */
    IPage<Gambit> selectGambitAll(Gambit gambit, Integer page, Integer limit);


    /**
     * 模糊查询话题创建人姓名
     *
     * @param gName
     * @return
     */
    List<Gambit> selectGambitName(String gName);

    /**
     * 添加文章
     *
     * @param gambit
     * @return
     */
    Integer saveGambit(Gambit gambit);

    /**
     * 修改文章
     *
     * @param gambit
     * @return
     */
    Integer updateGambit(Gambit gambit);


    /**
     * 删除文章
     *
     * @param gId
     * @return
     */
    Integer deleteGambitId(Integer gId);

}

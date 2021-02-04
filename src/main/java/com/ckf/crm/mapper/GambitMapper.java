package com.ckf.crm.mapper;

import com.ckf.crm.entity.Department;
import com.ckf.crm.entity.Gambit;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ckf.crm.model.ResultFormat;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author xuan
 * @since 2021-03-27
 */

@Repository
public interface GambitMapper extends BaseMapper<Gambit> {


    /**
     * 全查询文章信息
     *
     * @return
     */
    List<Gambit> selectGambitAll();


    /**
     * 模糊查询话题创建人姓名
     *
     * @param gName
     * @return
     */
    List<Gambit> selectGambitName(String gName);


}

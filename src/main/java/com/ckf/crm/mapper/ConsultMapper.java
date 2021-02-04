package com.ckf.crm.mapper;

import com.ckf.crm.entity.Consult;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ckf.crm.entity.Department;
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
public interface ConsultMapper extends BaseMapper<Consult> {


    /**
     * 全查询咨询信息
     *
     * @return
     */
    List<Consult> selectConsultAll();

    /**
     * 模糊查询咨询人姓名
     *
     * @param cName
     * @return
     */
    List<Consult> selectConsultName(String cName);

}

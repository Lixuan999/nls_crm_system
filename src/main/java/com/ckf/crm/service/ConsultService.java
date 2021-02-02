package com.ckf.crm.service;

import com.ckf.crm.entity.Consult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author xuan
 * @since 2020-03-27
 */
public interface ConsultService extends IService<Consult> {


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

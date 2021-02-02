package com.ckf.crm.service.impl;

import com.ckf.crm.entity.Consult;
import com.ckf.crm.mapper.ConsultMapper;
import com.ckf.crm.service.ConsultService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
 * @since 2020-03-27
 */

@Service
@Transactional
public class ConsultServiceImpl extends ServiceImpl<ConsultMapper, Consult> implements ConsultService {


    @Autowired
    private ConsultMapper consultMapper;


    /**
     * 全查询咨询信息
     *
     * @return
     */
    @Override
    public List<Consult> selectConsultAll() {
        return consultMapper.selectConsultAll();
    }

    /**
     * 模糊查询咨询人姓名
     *
     * @param cName
     * @return
     */
    @Override
    public List<Consult> selectConsultName(String cName) {
        return consultMapper.selectConsultName(cName);
    }
}

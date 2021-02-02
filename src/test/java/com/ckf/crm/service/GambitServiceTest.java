package com.ckf.crm.service;

import com.ckf.crm.entity.Gambit;
import com.ckf.crm.mapper.EmployeeMapperTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @author xuan
 * @version 1.0
 * @date 2020/3/27 18:48
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class GambitServiceTest {

    private Logger log = LoggerFactory.getLogger(EmployeeMapperTest.class);

    @Autowired
    private GambitService gambitService;


    /**
     * 模糊查询话题创建人姓名
     */
    @Test
    public void selectGambitName() {
        List<Gambit> list = gambitService.selectGambitName("ck");

        for (Gambit gambit : list) {
            System.out.println(gambit);
        }

        if (list != null) {
            log.info("模糊查询成功");
        } else {
            log.info("模糊查询失败");
        }

    }

    @Test
    public void updateGambit() {

        Gambit gambit=new Gambit();

        int id=3;
        gambit.setGambitId(id);
        gambit.setGHeadline("aa");
        gambit.setGAuditor("bb");
        gambit.setGAudiTime("200");
        gambit.setGFounder("gg");
        gambit.setGCheckState("aa");
        gambit.setCreateTime("2020");
        gambit.setUpdateTime("2001");
        gambit.setIsDel(0);

        Integer flag=gambitService.updateGambit(gambit);
        if (flag>0){
            System.out.println("修改成功");
        }else {
            System.out.println("修改失败");
        }
    }
}
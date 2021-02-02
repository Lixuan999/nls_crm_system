package com.ckf.crm.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author 安详的苦丁茶
 * @version 1.0
 * @date 2020/3/9 11:30
 */


@EnableTransactionManagement
@Configuration
@MapperScan(" com.ckf.crm.mapper*")
public class MybatisPlusConfig {


    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        //设置方言类型
        paginationInterceptor.setDialectType("mysql");
        return paginationInterceptor;
    }

}

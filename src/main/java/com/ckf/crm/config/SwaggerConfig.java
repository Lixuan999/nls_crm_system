package com.ckf.crm.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author 安详的苦丁茶
 * @version 1.0
 * @date 2020/3/13 9:50
 */

@Configuration
@EnableSwagger2      //开启swagger
public class SwaggerConfig {


    /**
     *  配置Swagger信息
     *  http://localhost:8080/swagger-ui.html  请求路径
     */
    /**
     * 扫描控制器的包
     *
     * @return
     */
    @Bean
    public Docket createDocketApi() {
        return new Docket(DocumentationType.SWAGGER_2).pathMapping("/").select().apis(RequestHandlerSelectors.basePackage("com.ckf"))
                .paths(PathSelectors.any())
                .build().apiInfo(new ApiInfoBuilder()
                        .title("员工管理系统 项目 API 文档")
                        .description("演示SpringBoot整合Swagger...")
                        .version("2.2.3")
                        .contact(new Contact("CKF", "https://www.cnblogs.com/zhunong/", "3121831267@qq.com"))
                        .license("The Apache License")
                        .licenseUrl("https://www.cnblogs.com/zhunong/")
                        .build());
    }
}
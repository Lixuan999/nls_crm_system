package com.ckf.crm;

import eu.bitwalker.useragentutils.Application;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;


import java.net.InetAddress;
import java.util.Optional;

@SpringBootApplication
@MapperScan("com.ckf.crm.mapper")
public class CkfCrmSystemApplication {

    private static Logger logger = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) throws Exception {
        ConfigurableApplicationContext application = SpringApplication.run(CkfCrmSystemApplication.class, args);
        Environment env = application.getEnvironment();
        String contextPath = env.getProperty("server.servlet.context-path");
        contextPath = Optional.ofNullable(contextPath).orElse("").replaceFirst("/", "");
        contextPath = (contextPath.length() <= 0 || contextPath.endsWith("/")) ? contextPath : contextPath + "/";
        String hostAddress = InetAddress.getLocalHost().getHostAddress();
        String serverPort = env.getProperty("server.port");
        String urlCtx = hostAddress + ":" + serverPort + "/" + contextPath;
        logger.info("\n----------------------------------------------------------\n\t" +
                        "\t\t地址列表\n\t" +
                        "管理地址：http://{}\n" +
                        "----------------------------------------------------------",
                urlCtx + "login"
        );
    }

}

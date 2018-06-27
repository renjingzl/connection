package com.baojia.connection;


import springfox.documentation.swagger2.annotations.EnableSwagger2;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;

/**
 * @Auther: YUANEL
 * @Date: 2018/6/22 11:35
 * @Description:
 */
@SpringBootApplication
@MapperScan(basePackages = {"com.baojia.connection.dao"})
@EnableDubboConfiguration
@EnableSwagger2
public class AdminServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(AdminServiceApplication.class);
    }
}


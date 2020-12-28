package com.cyw.info_submit;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication()
//扫描mapper接口
@MapperScan(value = "com.cyw.info_submit.dao")
@EnableTransactionManagement
public class InfoSubmitApplication {

    public static void main(String[] args) {
        SpringApplication.run(InfoSubmitApplication.class, args);
    }

}

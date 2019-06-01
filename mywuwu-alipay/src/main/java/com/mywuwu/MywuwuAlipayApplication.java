package com.mywuwu;

import com.mywuwu.common.ds.DynamicDataSourceRegister;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("com.mywuwu.dao")
//@ComponentScan(basePackages={"com.mywuwu.controller"})
@Import(DynamicDataSourceRegister.class)
public class MywuwuAlipayApplication {

    public static void main(String[] args) {
        SpringApplication.run(MywuwuAlipayApplication.class, args);
    }
}

package com.ben;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@Configurable
@EnableScheduling // 启用定时任务

public class Application {

    public static void main(String[] args){
        SpringApplication.run(Application.class);
    }

}

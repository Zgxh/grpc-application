package com.zgxh.web;

import com.zgxh.web.grpc.server.GrpcServer;
import com.zgxh.web.utils.SpringUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class WebApplication {

    public static void main(String[] args) throws IOException {
        // 启动spring+tomcat
        SpringApplication.run(WebApplication.class, args);

        // 启动GrpcServer
        SpringUtil.getBean("grpcServer", GrpcServer.class).startServer();
    }
}

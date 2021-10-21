package com.zgxh.web;

import com.zgxh.web.grpc.server.GrpcServer;
import com.zgxh.web.utils.SpringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.Resource;
import java.io.IOException;

@SpringBootApplication
public class WebApplication {

    public static void main(String[] args) throws IOException {
        SpringApplication.run(WebApplication.class, args);
        SpringUtil.getBean("grpcServer", GrpcServer.class).startServer();
    }
}

package com.zgxh.web;

import com.zgxh.web.grpc.server.GrpcServer;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class WebApplicationTests {

    @Resource
    private GrpcServer grpcServer;

    @Test
    void contextLoads() {
        // System.out.println(grpcServer.getGrpcServices());
    }
}

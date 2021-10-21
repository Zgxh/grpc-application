package com.zgxh.web.grpc.server;

import com.zgxh.grpc.hello.HelloServiceGrpc;
import com.zgxh.web.grpc.annotation.GrpcService;
import com.zgxh.web.utils.SpringUtil;
import io.grpc.BindableService;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.ServerServiceDefinition;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * Grpc 服务器
 *
 * @author Yu Yang
 */
@Component
@Slf4j
public class GrpcServer {

    private Server server;

    @Value("${grpc.server.port}")
    private String port;

    /**
     * 启动 grpc 服务
     *
     * @throws IOException
     */
    public void startServer() throws IOException {
        List<BindableService> grpcServices = getGrpcServices();
        ServerBuilder<?> serverBuilder = ServerBuilder.forPort(Integer.parseInt(port));
        grpcServices.forEach(serverBuilder::addService);
        server = serverBuilder.build().start();
        log.info("Grpc server start! Listening on port " + port);

        // 在spring停止运行的时候关闭 grpc 服务
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            log.info("try to shutdown the grpc server....");
            try {
                GrpcServer.this.stopServer();
            } catch (Exception e) {
                log.error("failed to stop the grpc server!");
                e.printStackTrace();
            }
            log.info("The grpc server has been stopped!");
        }));
    }

    /**
     * 获取容器中的所有GrpcService
     *
     * @return
     */
    private List<BindableService> getGrpcServices() {
        Map<String, Object> grpcBeans = SpringUtil.getBeansWithAnnotation(GrpcService.class);
        List<BindableService> grpcServices = grpcBeans.values().stream().filter(item -> item instanceof BindableService)
                .map(item -> (BindableService) item).collect(Collectors.toList());

        return grpcServices;
    }

    /**
     * 停止 grpc 服务
     *
     * @throws InterruptedException
     */
    private void stopServer() throws InterruptedException {
        if (server != null) {
            server.shutdown().awaitTermination(30, TimeUnit.SECONDS);
        }
    }
}

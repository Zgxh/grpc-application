package com.zgxh.web.grpc.server;

import com.zgxh.web.grpc.annotation.GrpcService;
import com.zgxh.web.utils.SpringUtil;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * @author Yu Yang
 */
@Component
public class GrpcServer {

    private Server server;

    @Value("grpc.server.port")
    private String port;

    // public void startServer() {
    //     List<Object> grpcServices = getGrpcServices();
    //     this.server = ServerBuilder.forPort(port)
    //             .addServices(grpcServices)
    //             .
    // }

    /**
     * 获取容器中的所有GrpcService
     *
     * @return
     */
    public List<Object> getGrpcServices() {
        Map<String, Object> grpcBeans = SpringUtil.getBeansWithAnnotation(GrpcService.class);

        return new ArrayList<>(grpcBeans.values());
    }
}

package com.zgxh.web.grpc.service;

import com.zgxh.grpc.hello.HelloRequest;
import com.zgxh.grpc.hello.HelloResponse;
import com.zgxh.grpc.hello.HelloServiceGrpc;
import com.zgxh.grpcserver.annotation.GrpcService;
import io.grpc.stub.StreamObserver;

/**
 * hello grpc 服务
 *
 * @author Yu Yang
 */
@GrpcService
public class HelloGrpcService extends HelloServiceGrpc.HelloServiceImplBase {

    @Override
    public void sayHello(HelloRequest request, StreamObserver<HelloResponse> responseObserver) {
        String name = request.getName();
        HelloResponse response = HelloResponse.newBuilder()
                .setCode(200)
                .setMessage("hello, " + name)
                .build();
        responseObserver.onNext(response);
    }
}

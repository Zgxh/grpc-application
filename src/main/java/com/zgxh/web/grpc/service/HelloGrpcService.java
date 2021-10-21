package com.zgxh.web.grpc.service;

import com.zgxh.grpc.hello.HelloRequest;
import com.zgxh.grpc.hello.HelloResponse;
import com.zgxh.grpc.hello.HelloServiceGrpc;
import com.zgxh.web.grpc.annotation.GrpcService;
import com.zgxh.web.grpc.common.PostProcessGrpcMethod;
import io.grpc.stub.StreamObserver;

/**
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
        PostProcessGrpcMethod.postProcessAfterResponse(responseObserver, response);
    }
}

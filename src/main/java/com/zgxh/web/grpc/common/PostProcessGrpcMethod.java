package com.zgxh.web.grpc.common;

import io.grpc.stub.StreamObserver;

/**
 * gprc调用逻辑结束后的后置处理器
 *
 * @author Yu Yang
 */
public class PostProcessGrpcMethod {

    /**
     * 在得到响应后统一设置响应体
     *
     * @param responseObserver
     * @param response
     * @param <T>
     */
    public static <T> void postProcessAfterResponse(StreamObserver<T> responseObserver, T response) {
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}

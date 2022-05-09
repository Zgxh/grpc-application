package com.zgxh.web.grpc.aspect;

import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * grpc 服务统一处理的切面，保证无论正常还是出错都能正常结束grpc请求。
 *
 * @author Yu Yang
 */
@Aspect
@Component
@Slf4j
public class GrpcServiceBeforeReturnAspect {

    /**
     * 匹配所有grpc方法
     */
    @Pointcut("execution(* com.zgxh.web.grpc.service..*.*(.., io.grpc.stub.StreamObserver))")
    public void pointcut() {}

    /**
     * 处理请求正常结束的情况
     * @param joinPoint
     */
    @SuppressWarnings("rawtypes")
    @AfterReturning("pointcut()")
    public void postProcessGrpcServiceMethodWhenGetResponse(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        try {
            StreamObserver streamObserver = ((StreamObserver) args[1]);
            streamObserver.onCompleted();
            log.info("Grpc service [{}] succeed with args: [{}]", joinPoint.getSignature().getName(), args[0]);
        } catch (Exception e) {
            log.error("GrpcServiceOnCompletedAspect >> postProcessGrpcServiceMethodWhenGetResponse. failed to cast args", e);
        }
    }

    /**
     * 处理请求异常的情况
     *
     * @param joinPoint
     * @param error
     */
    @SuppressWarnings("rawtypes")
    @AfterThrowing(pointcut = "pointcut()", throwing = "error")
    public void postProcessGrpcServiceMethodWhenGetError(JoinPoint joinPoint, Throwable error) {
        Object[] args = joinPoint.getArgs();
        try {
            StreamObserver streamObserver = ((StreamObserver) args[1]);
            streamObserver.onError(error);
        } catch (Exception e) {
            log.error("GrpcServiceOnCompletedAspect >> postProcessGrpcServiceMethodWhenGetError. failed to cast args", e);
        }
    }
}
package com.zgxh.web;

import com.google.protobuf.InvalidProtocolBufferException;
import com.zgxh.web.protobase.helloworld.HelloRequest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

// @SpringBootTest
class WebApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    void simpleTest() {
        HelloRequest gangReq = HelloRequest.newBuilder().setName("ligang").build();
        byte[] gangBytes = gangReq.toByteArray();
        System.out.println("serialized data: " + Arrays.toString(gangBytes));

        try {
            HelloRequest helloRequest = HelloRequest.parseFrom(gangBytes);
            System.out.println("parsed data: " + helloRequest.getName());
        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
        }
    }

}

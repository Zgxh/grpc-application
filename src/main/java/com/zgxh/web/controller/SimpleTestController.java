package com.zgxh.web.controller;

import com.zgxh.web.dto.BaseResp;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Yu Yang
 */
@RestController
public class SimpleTestController {

    @GetMapping("/")
    public BaseResp sayHello() {
        return BaseResp.builder().code(200).message("hello!").build();
    }
}

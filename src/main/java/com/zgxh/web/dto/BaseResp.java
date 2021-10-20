package com.zgxh.web.dto;

import lombok.Builder;
import lombok.Data;

/**
 * @author Yu Yang
 */
@Data
@Builder
public class BaseResp<T> {

    private Integer code;

    private String message;

    private T data;
}

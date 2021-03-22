package com.toy.blogcode.springbootaws.common.response;

import lombok.Data;

@Data
public class CommonResult {
    private boolean success;
    private int resultCode;
    private String resultMsg;
}

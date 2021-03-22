package com.toy.blogcode.springbootaws.common.response;

import lombok.Data;

import java.util.List;

@Data
public class CommonListResult<T> extends CommonResult {
    private List<T> listResult;
}

package com.toy.blogcode.springbootaws.common.domain;

import lombok.Data;

import java.util.List;

@Data
public class CommonListResult<T> extends CommonResult {
    private List<T> listResult;
}

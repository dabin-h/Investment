package com.toy.blogcode.springbootaws.common.domain;

import lombok.Data;

@Data
public class CommonSingleResult<T> extends CommonResult {
    public T data;
}

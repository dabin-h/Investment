package com.toy.blogcode.springbootaws.common.response;

import lombok.Data;

@Data
public class CommonSingleResult<T> extends CommonResult {
    public T data;
}

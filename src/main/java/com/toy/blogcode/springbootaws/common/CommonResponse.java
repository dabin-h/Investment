package com.toy.blogcode.springbootaws.common;

import com.toy.blogcode.springbootaws.common.response.CommonListResult;
import com.toy.blogcode.springbootaws.common.response.CommonResult;
import com.toy.blogcode.springbootaws.common.response.CommonSingleResult;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommonResponse {
    public CommonResult normalResult(){
        CommonResult result = new CommonResult();
        result.setResultCode(0);
        result.setResultMsg("Success");
        return result;
    }

    public <T> CommonListResult <T> listResult(List<T> list){
        CommonListResult<T> result = new CommonListResult<>();
        result.setListResult(list);
        result.setResultCode(0);
        result.setResultMsg("Success");
        return result;
    }
    public <T> CommonSingleResult<T> singleResult(T data){
        CommonSingleResult result = new CommonSingleResult();
        result.setData(data);
        result.setResultCode(0);
        result.setResultMsg("Success");
        return result;
    }
}



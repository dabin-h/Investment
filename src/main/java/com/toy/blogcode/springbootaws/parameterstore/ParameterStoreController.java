package com.toy.blogcode.springbootaws.parameterstore;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by toy@gmail.com on 01/02/2021
 */

@RequiredArgsConstructor
@RestController
public class ParameterStoreController {
    private final ParameterStoreProperties properties;

    @GetMapping("/parameter-store/key")
    public String getKey() {
        return properties.getEncryptKey();
    }
}


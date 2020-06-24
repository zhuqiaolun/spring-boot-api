package com.demon.springbootapi.controller;

import com.demon.springbootapi.aop.CustomVerify;
import org.apache.commons.codec.binary.Hex;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @ClassName: TestController
 * @Description:
 * @Author: Demon
 * @Date: 2020/6/3 22:44
 */
@RestController
@RequestMapping(value = "test")
public class TestController {

    @PostMapping
    @CustomVerify
    public Object getMd5() throws NoSuchAlgorithmException {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        byte[] bs = md5.digest("spring-boot-api".getBytes());
        return new String(new Hex().encode(bs));
    }

}

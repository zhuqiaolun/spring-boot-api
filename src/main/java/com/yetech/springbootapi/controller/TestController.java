package com.yetech.springbootapi.controller;

import com.yetech.springbootapi.aop.CustomVerify;
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
 * @Copyright: 2020 www.yetech.com.cn Inc. All rights reserved.
 * 注意：本内容仅限于元镁信息科技（上海）有限公司内部传阅，禁止外泄以及用于其他的商业目
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

package com.demon.springbootapi.controller;

import com.demon.springbootapi.util.ResponseBean;
import org.apache.commons.codec.binary.Hex;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.MessageDigest;

/**
 * @ClassName: TestController
 * @Description: 测试
 * @Author: Demon
 * @Date: 2020/6/3 22:44
 */
@RestController
@RequestMapping(value = "test")
public class TestController {

    @GetMapping
    public ResponseBean<String> getMd5(){
        ResponseBean<String> objectResponseBean = new ResponseBean<>();
        try{
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] bs = md5.digest("spring-boot-api".getBytes());
            objectResponseBean.setSuccess(true).setData(new String(new Hex().encode(bs)));
        }catch (Exception e){
            e.printStackTrace();
            objectResponseBean.setErrMsg(e.getMessage());
        }
        return objectResponseBean;
    }

}

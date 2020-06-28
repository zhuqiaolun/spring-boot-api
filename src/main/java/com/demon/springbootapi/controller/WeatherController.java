package com.demon.springbootapi.controller;

import com.alibaba.fastjson.JSONObject;
import com.demon.springbootapi.aop.CustomVerify;
import com.demon.springbootapi.util.ResponseBean;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @ClassName: WeatherController
 * @Description: 天气
 * @Author: Demon
 * @Date: 2020/6/1 19:16
 */
@RestController
@RequestMapping(value = "weather")
public class WeatherController {

    @Resource
    private RestTemplate restTemplate;

    @CustomVerify
    @PostMapping(value = "getToDayInfo")
    public ResponseBean<JSONObject> getToDayInfo(){
        ResponseBean<JSONObject> objectResponseBean = new ResponseBean<>();
        try{
            String url = "https://free-api.heweather.com/v5/forecast?city=CN101020600&key=5c043b56de9f4371b0c7f8bee8f5b75e";
            JSONObject resp = restTemplate.getForObject(url, JSONObject.class);
            objectResponseBean.setSuccess(true).setData(resp);
        }catch (Exception e){
            e.printStackTrace();
            objectResponseBean.setErrMsg(e.getMessage());
        }
        return objectResponseBean;
    }
}

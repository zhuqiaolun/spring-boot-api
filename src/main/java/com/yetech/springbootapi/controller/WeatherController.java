package com.yetech.springbootapi.controller;

import com.yetech.springbootapi.util.ResponseBean;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @ClassName: WeatherController
 * @Description:
 * @Author: Demon
 * @Date: 2020/6/1 19:16
 * @Copyright: 2020 www.yetech.com.cn Inc. All rights reserved.
 * 注意：本内容仅限于元镁信息科技（上海）有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
@RestController
@RequestMapping(value = "weather")
public class WeatherController {

    @Resource
    private RestTemplate restTemplate;

    @PostMapping(value = "getToDayInfo")
    public ResponseBean<Object> getToDayInfo(){
        ResponseBean<Object> objectResponseBean = new ResponseBean<>();
        try{
            String url = "https://free-api.heweather.com/v5/forecast?city=CN101020600&key=5c043b56de9f4371b0c7f8bee8f5b75e";
            String resp = restTemplate.getForObject(url, String.class);
            objectResponseBean.setSuccess(true).setData(resp);
        }catch (Exception e){
            e.printStackTrace();
            objectResponseBean.setErrMsg(e.getMessage());
        }
        return objectResponseBean;
    }
}

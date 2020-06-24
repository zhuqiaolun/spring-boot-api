package com.demon.springbootapi.controller;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @ClassName: TimeController
 * @Description: 时间
 * @Author: Demon
 * @Date: 2020/6/3 22:44
 */
@RestController
@RequestMapping(value = "time")
public class TimeController {

    @PostMapping
    public Object getTime(){
        return System.currentTimeMillis();
    }

    @PostMapping(value = "/date")
    public Object getDate(){
        return DateFormatUtils.format(new Date(),"yyyy-MM-dd HH:mm:ss");
    }

}

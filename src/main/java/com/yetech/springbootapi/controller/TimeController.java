package com.yetech.springbootapi.controller;

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
 * @Copyright: 2020 www.yetech.com.cn Inc. All rights reserved.
 * 注意：本内容仅限于元镁信息科技（上海）有限公司内部传阅，禁止外泄以及用于其他的商业目
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

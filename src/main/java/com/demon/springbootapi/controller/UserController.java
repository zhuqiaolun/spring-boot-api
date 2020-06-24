package com.demon.springbootapi.controller;

import com.demon.springbootapi.database.service.SystemUserService;
import com.demon.springbootapi.support.BaseController;
import com.demon.springbootapi.util.ResponseBean;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @ClassName: UserController
 * @Description: 用户
 * @Author: Demon
 * @Date: 2020/6/1 14:06
 */
@RestController
@RequestMapping(value = "user")
public class UserController extends BaseController {

    @Resource
    private SystemUserService systemUserService;

    @PostMapping(value = "getBaseUserInfo")
    public ResponseBean<Object> getBaseUserInfo(){
        ResponseBean<Object> objectResponseBean = new ResponseBean<>();
        try{
            Map<String, Object> baseUserInfo = systemUserService.getBaseUserInfo(this.getAppId());
            objectResponseBean.setSuccess(true).setData(baseUserInfo);
        }catch (Exception e){
            e.printStackTrace();
            objectResponseBean.setErrMsg(e.getMessage());
        }
        return objectResponseBean;
    }

    @PostMapping(value = "getQueryUserInfo")
    public ResponseBean<Object> getQueryUserInfo(){
        ResponseBean<Object> objectResponseBean = new ResponseBean<>();
        try{
            Map<String, Object> queryUserInfo = systemUserService.getQueryUserInfo(this.getAppId());
            objectResponseBean.setSuccess(true).setData(queryUserInfo);
        }catch (Exception e){
            e.printStackTrace();
            objectResponseBean.setErrMsg(e.getMessage());
        }
        return objectResponseBean;
    }

}

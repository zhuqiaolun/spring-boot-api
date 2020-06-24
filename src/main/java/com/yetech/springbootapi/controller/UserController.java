package com.yetech.springbootapi.controller;

import com.yetech.springbootapi.database.service.SystemUserService;
import com.yetech.springbootapi.support.BaseController;
import com.yetech.springbootapi.util.ResponseBean;
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
 * @Copyright: 2020 www.yetech.com.cn Inc. All rights reserved.
 * 注意：本内容仅限于元镁信息科技（上海）有限公司内部传阅，禁止外泄以及用于其他的商业目
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

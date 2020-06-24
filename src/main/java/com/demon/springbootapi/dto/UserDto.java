package com.demon.springbootapi.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName: UserDto
 * @Description: 用户 数据
 * @Author: Demon
 * @Date: 2020/6/8 19:11
 */
@Data
@Accessors(chain = true)
public class UserDto implements Serializable {

    private String appId;
    private String appKey;
    private String nickName;
    private String userName;
    private Date createTime;
}

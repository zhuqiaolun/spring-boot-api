package com.yetech.springbootapi.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName: UserDto
 * @Description: 用户 数据
 * @Author: Demon
 * @Date: 2020/6/8 19:11
 * @Copyright: 2020 www.yetech.com.cn Inc. All rights reserved.
 * 注意：本内容仅限于元镁信息科技（上海）有限公司内部传阅，禁止外泄以及用于其他的商业目
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

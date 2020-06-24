package com.yetech.springbootapi.util;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @ClassName: ResponseBean
 * @Description:
 * @Author: Demon
 * @Date: 2020/1/17 14:41
 * @Copyright: 2020 www.yetech.com.cn Inc. All rights reserved.
 * 注意：本内容仅限于元镁信息科技（上海）有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
@Data
@Accessors(chain = true)
public class ResponseBean<T> implements Serializable {

    private boolean success = false;
    private T data;
    private String errCode;
    private String errMsg;

}

package com.demon.springbootapi.util;

import com.alibaba.fastjson.annotation.JSONField;
import com.demon.springbootapi.support.ObjectToJsonSerializer;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @ClassName: ResponseBean
 * @Description:
 * @Author: Demon
 * @Date: 2020/1/17 14:41
 */
@Data
@Accessors(chain = true)
public class ResponseBean<T> implements Serializable {

    private boolean success = false;

    @JSONField(serializeUsing = ObjectToJsonSerializer.class)
    private T data;

    private String errCode;

    private String errMsg;

}

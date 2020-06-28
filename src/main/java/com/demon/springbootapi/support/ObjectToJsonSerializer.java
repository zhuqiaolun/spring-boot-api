package com.demon.springbootapi.support;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.ObjectSerializer;

import java.lang.reflect.Type;

/**
 * @ClassName: StringToJsonSerializer
 * @Description: 去掉json格式数据中含有的转义字符
 * @Author: Demon
 * @Date: 2020/6/28 14:48
 */
public class ObjectToJsonSerializer implements ObjectSerializer {

    @Override
    public void write(JSONSerializer jsonSerializer, Object o, Object o1, Type type, int i){
        try{
            jsonSerializer.write(JSONObject.parseObject(String.valueOf(o)));
        }catch (Exception e){
            jsonSerializer.write(o.toString());
        }
    }
}

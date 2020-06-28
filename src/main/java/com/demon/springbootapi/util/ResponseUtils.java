package com.demon.springbootapi.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @ClassName: ResponseUtils.java
 * @Description:  使用response输出JSON
 * @Author: Demon
 * @Date: 2020/6/3 20:16
 */
@Slf4j
public class ResponseUtils {

    /**
     * 使用response输出JSON
     * @param response response
     * @param responseBean responseBean
     */
    public static void out(ServletResponse response, ResponseBean responseBean) {
        PrintWriter out = null;
        try {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json");
            out = response.getWriter();
            out.println(JSON.toJSONString(responseBean));
        } catch (Exception e) {
            log.error(e + "输出JSON出错");
        } finally {
            if (out != null) {
                out.flush();
                out.close();
            }
        }
    }

    /**
     * 响应内容
     * @param httpServletResponse httpServletResponse
     * @param errCode errCode
     * @param errMsg errMsg
     */
    public static void getErrResponse(HttpServletResponse httpServletResponse, int errCode,String errMsg){
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.setContentType("application/json; charset=utf-8");
        try (PrintWriter writer = httpServletResponse.getWriter()) {
            ResponseBean responseBean = new ResponseBean()
                .setErrCode(String.valueOf(errCode))
                .setErrMsg(errMsg);
            writer.print(JSONObject.toJSONString(responseBean));
        } catch (IOException e) {
            log.error("响应报错", e.getMessage());
        }
    }

}

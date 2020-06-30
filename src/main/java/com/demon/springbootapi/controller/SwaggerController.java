package com.demon.springbootapi.controller;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @ClassName: SwaggerController
 * @Description: swagger请求
 * @Author: Demon
 * @Date: 2020/6/30 11:23
 * @Copyright: 2020 www.yetech.com.cn Inc. All rights reserved.
 * 注意：本内容仅限于元镁信息科技（上海）有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
@Controller
@RequestMapping(value = "swagger")
public class SwaggerController {

    @Resource
    private Environment environment;

    @GetMapping
    public String index() {
        return "swagger-ui/swagger";
    }

    /**
     * swagger 数据
     * @return 返回
     * @throws UnknownHostException 异常
     */
    @GetMapping(value = "data.json")
    public @ResponseBody
    Object getJsonData() throws UnknownHostException {
        JsonNodeFactory jsonNodeFactory = JsonNodeFactory.instance;
        ObjectNode dataJson = jsonNodeFactory.objectNode();
        dataJson.put("swagger", "2.0");
        dataJson.putObject("info")
                .put("title", "spring-boot-api")
                .put("version ", "1.0")
                .put("description", "后台简易API测试")
                .putObject("contact").put("email", "")
        ;
        String localhost = InetAddress.getLocalHost().getHostAddress();
        dataJson.put("host", localhost + ":" + environment.getProperty("server.port"));
        dataJson.put("basePath", environment.getProperty("server.servlet.context-path"));
        dataJson.putArray("tags").addAll(getTags());
        dataJson.putArray("schemes").add("http");
        dataJson.putObject("paths")
                .putObject("/test").putObject("get").setAll(getTest())
        ;
        return dataJson;
    }

    /**
     * 添加 swagger 标签
     * @return 返回
     */
    private ArrayNode getTags() {
        JsonNodeFactory jsonNodeFactory = JsonNodeFactory.instance;
        ArrayNode arrayNode = jsonNodeFactory.arrayNode();
        ObjectNode testTag = jsonNodeFactory.objectNode();
        testTag.put("name", "Test")
                .put("description", "测试");
        arrayNode.add(testTag);
        ObjectNode userTag = jsonNodeFactory.objectNode();
        userTag.put("name", "User")
                .put("description", "用户");
        arrayNode.add(userTag);
        ObjectNode weatherTag = jsonNodeFactory.objectNode();
        weatherTag.put("name", "Weather")
                .put("description", "天气");
        arrayNode.add(weatherTag);
        return arrayNode;
    }

//======================================= 下面配置请求  ===============================================================

    /**
     * 添加 请求
     * @return 返回
     */
    private ObjectNode getTest() {
        JsonNodeFactory jsonNodeFactory = JsonNodeFactory.instance;
        ObjectNode jsonNodes = jsonNodeFactory.objectNode();
        jsonNodes.putArray("tags").add("Test");
        jsonNodes.put("summary", "测试获取MD5值");
        jsonNodes.put("description", "通过MessageDigest.getInstance(\"MD5\")执行返回结果");
        jsonNodes.put("operationId", "getMd5");
        jsonNodes.putArray("consumes");
        jsonNodes.putArray("produces").add("application/json");
        jsonNodes.putArray("parameters");
        ObjectNode responses = jsonNodes.putObject("responses");
        responses.putObject("200")
                .put("description", "successful operation")
                .putObject("schema").put("type", "string")
        ;
        return jsonNodes;
    }

}

package com.demon.springbootapi.controller;

import com.demon.springbootapi.database.entity.SystemSwaggerInfo;
import com.demon.springbootapi.database.entity.SystemSwaggerTags;
import com.demon.springbootapi.database.entity.SystemSwaggerUrl;
import com.demon.springbootapi.database.service.SystemSwaggerInfoService;
import com.demon.springbootapi.database.service.SystemSwaggerTagsService;
import com.demon.springbootapi.database.service.SystemSwaggerUrlService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

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

    @Resource
    private SystemSwaggerInfoService systemSwaggerInfoService;

    @Resource
    private SystemSwaggerTagsService systemSwaggerTagsService;

    @Resource
    private SystemSwaggerUrlService systemSwaggerUrlService;

    @GetMapping
    public String index() {
        return "swagger-ui/swagger";
    }

    /**
     * swagger 加载初始数据
     * @return 返回
     */
    @GetMapping(value = "data.json")
    public @ResponseBody
    Object getJsonData() throws JsonProcessingException {
        SystemSwaggerInfo systemSwaggerInfo = systemSwaggerInfoService.list().get(0);
        JsonNodeFactory jsonNodeFactory = JsonNodeFactory.instance;
        ObjectNode dataJson = jsonNodeFactory.objectNode();
        dataJson.put("swagger", "2.0");
        ObjectNode info = dataJson.putObject("info");
        info.put("termsOfService", "http://swagger.io/terms/")
            .put("title", systemSwaggerInfo.getSiTitle())
            .put("version ", systemSwaggerInfo.getSiVersion())
            .put("description", systemSwaggerInfo.getSiDescription())
            .putObject("contact")
                .put("name",systemSwaggerInfo.getSiContactName())
                .put("url",systemSwaggerInfo.getSiContactUrl())
                .put("email", systemSwaggerInfo.getSiContactEmail());
        info.putObject("license")
            .put("name","Apache 2.0")
            .put("url","http://www.apache.org/licenses/LICENSE-2.0.html")
        ;
        dataJson.put("host", systemSwaggerInfo.getSiServerhost() + ":" + environment.getProperty("server.port"));
        dataJson.put("basePath", environment.getProperty("server.servlet.context-path"));
        dataJson.putArray("tags").addAll(getTags());
        dataJson.set("schemes",new ObjectMapper().readValue("[\"http\",\"https\"]", ArrayNode.class));
        dataJson.putObject("paths").setAll(getPaths());
        ObjectNode securityDefinitions = dataJson.putObject("securityDefinitions");
        securityDefinitions.putObject("api_key").put("type", "apiKey").put("name", "api_key").put("in", "header");
        return dataJson;
    }

    /**
     * 添加 swagger 标签
     */
    private ArrayNode getTags() {
        JsonNodeFactory jsonNodeFactory = JsonNodeFactory.instance;
        ArrayNode arrayNode = jsonNodeFactory.arrayNode();
        List<SystemSwaggerTags> systemSwaggerTagsList = systemSwaggerTagsService.list();
        if(systemSwaggerTagsList != null && systemSwaggerTagsList.size() > 0){
            systemSwaggerTagsList.forEach(systemSwaggerTags ->{
                ObjectNode objectNode = jsonNodeFactory.objectNode();
                objectNode.put("name", systemSwaggerTags.getStName()).put("description", systemSwaggerTags.getStDescription());
                arrayNode.add(objectNode);
            });
        }
        return arrayNode;
    }

    /**
     * 添加 swagger 请求
     */
    private ObjectNode getPaths() {
        JsonNodeFactory jsonNodeFactory = JsonNodeFactory.instance;
        ObjectNode paths = jsonNodeFactory.objectNode();
        List<SystemSwaggerUrl> systemSwaggerUrls = systemSwaggerUrlService.list();
        if (systemSwaggerUrls != null && systemSwaggerUrls.size() > 0) {
            systemSwaggerUrls.forEach(systemSwaggerUrl -> {
                try {
                    ObjectNode jsonNodes = paths.putObject(systemSwaggerUrl.getSuUrl()).putObject(systemSwaggerUrl.getSuMethod());
                    jsonNodes.putArray("tags").add(systemSwaggerUrl.getSuTags());
                    jsonNodes.put("summary", systemSwaggerUrl.getSuSummary());
                    jsonNodes.put("description", systemSwaggerUrl.getSuDescription());
                    jsonNodes.put("operationId", systemSwaggerUrl.getSuOperationid());
                    if (StringUtils.isNotBlank(systemSwaggerUrl.getSuConsumes())) {
                        jsonNodes.set("consumes",new ObjectMapper().readValue(systemSwaggerUrl.getSuConsumes(), ArrayNode.class));
                    }
                    if (StringUtils.isNotBlank(systemSwaggerUrl.getSuProduces())) {
                        jsonNodes.set("produces",new ObjectMapper().readValue(systemSwaggerUrl.getSuProduces(), ArrayNode.class));
                    }
                    if (StringUtils.isNotBlank(systemSwaggerUrl.getSuParameters())) {
                        jsonNodes.putArray("parameters").addAll(new ObjectMapper().readValue(systemSwaggerUrl.getSuParameters(), ArrayNode.class));
                    }
                    jsonNodes.set("responses", new ObjectMapper().readTree(systemSwaggerUrl.getSuResponses()));
                    jsonNodes.put("deprecated", systemSwaggerUrl.getSuDeprecated());
                    if (StringUtils.isNotBlank(systemSwaggerUrl.getSuSecurity())) {
                        jsonNodes.putArray("security").addAll(new ObjectMapper().readValue(systemSwaggerUrl.getSuSecurity(), ArrayNode.class));
                    }
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
            });
        }
        return paths;
    }

}

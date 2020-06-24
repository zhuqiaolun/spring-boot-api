package com.demon.springbootapi;

import com.demon.springbootapi.util.JwtTokenUtil;
import io.jsonwebtoken.Claims;
import org.apache.commons.lang3.time.DateUtils;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @ClassName: TestJwtTokenUtil
 * @Description:
 * @Author: Demon
 * @Date: 2020/6/2 18:04
 * @Copyright: 2020 www.yetech.com.cn Inc. All rights reserved.
 * 注意：本内容仅限于元镁信息科技（上海）有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
public class TestJwtTokenUtil {

    public static void main(String[] args) {
        Map<String, Object> claims = new LinkedHashMap<>();
        claims.put("appId","1000000000000000");
        claims.put("appKey","1234567890");
        Date expiresAt = DateUtils.addSeconds(new Date(),7200);
        String token = JwtTokenUtil.createToken(claims,expiresAt);
        System.out.println(token);
        try {
            Claims params = JwtTokenUtil.verifyToken(token);
            System.out.println(params.get("appId"));
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

    }
}

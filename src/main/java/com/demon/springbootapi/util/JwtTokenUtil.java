package com.demon.springbootapi.util;

import io.jsonwebtoken.*;
import org.apache.commons.codec.binary.Hex;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: JwtTokenUtil
 * @Description:
 * @Author: Demon
 * @Date: 2020/6/2 16:52
 */
public class JwtTokenUtil {

    private static final String TOKEN_PRE = "eyJhbGciOiJIUzI1NiJ9";
    private static final String APP_SECRET_KEY = "project_app_secret";
    private static byte[] key_bytes = {0x31, 0x32, 0x33, 0x34, 0x35, 0x50, 0x37, 0x38, 0x39, 0x40, 0x41, 0x42, 0x43, 0x44, 0x45, 0x46};

    private static Map<String, Object> getHeader() {
        // 头部信息
        Map<String, Object> header = new HashMap<>(2);
        header.put("alg", "HS256");
        header.put("typ", "JWT");
        return header;
    }

    /**
     * 生成token
     *
     * @param claims    claims
     * @param expiresAt expiresAt
     * @return 返回
     */
    public static String createToken(Map<String, Object> claims, Date expiresAt) {
        if (claims == null || expiresAt == null) {
            return null;
        }
        String token = Jwts.builder()
                // 自定义属性
                .setClaims(claims)
                // 过期时间
                .setExpiration(expiresAt)
                // 签名算法以及密匙
                .signWith(SignatureAlgorithm.HS256, APP_SECRET_KEY).compact();
        String[] strings = token.split("\\.");
        return encrypt(strings[1] + "." + strings[2]);
    }

    public static Claims verifyToken(String token) throws Exception {
        try {
            String tokenStr = TOKEN_PRE + "." + decrypt(token);
            return Jwts.parser().setSigningKey(APP_SECRET_KEY).parseClaimsJws(tokenStr).getBody();
        } catch (SignatureException e) {
            e.printStackTrace();
            throw new Exception("token签名错误", e);
        } catch (MalformedJwtException e) {
            e.printStackTrace();
            throw new Exception("token格式错误", e);
        } catch (ExpiredJwtException e) {
            e.printStackTrace();
            throw new Exception("token过期错误", e);
        } catch (UnsupportedJwtException e) {
            e.printStackTrace();
            throw new Exception("token不支持错误", e);
        } catch (Exception e) {
            throw new Exception("token未知错误", e);
        }
    }

    /**
     * 加密
     *
     * @param value 值
     * @return 返回
     */
    private static String encrypt(String value) {
        int mode = Cipher.ENCRYPT_MODE;
        try {
            Cipher cipher = initCipher(mode);
            byte[] outBytes = cipher.doFinal(value.getBytes());
            return String.valueOf(Hex.encodeHex(outBytes));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 解密
     *
     * @param value value
     * @return 返回
     */
    private static String decrypt(String value) {
        int mode = Cipher.DECRYPT_MODE;
        try {
            Cipher cipher = initCipher(mode);
            byte[] outBytes = cipher.doFinal(Hex.decodeHex(value.toCharArray()));
            return new String(outBytes);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 初始化密码
     *
     * @param mode mode
     * @return 返回
     * @throws NoSuchAlgorithmException 异常
     * @throws NoSuchPaddingException   异常
     * @throws InvalidKeyException      异常
     */
    private static Cipher initCipher(int mode) throws NoSuchAlgorithmException,
            NoSuchPaddingException, InvalidKeyException {
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        Key key = new SecretKeySpec(key_bytes, "AES");
        cipher.init(mode, key);
        return cipher;
    }
}

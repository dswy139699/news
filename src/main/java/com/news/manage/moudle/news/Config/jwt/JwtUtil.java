package com.news.manage.moudle.news.Config.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.*;

public class JwtUtil {
    /**
     * 过期10分钟
     * */
    private static final long EXPIRE_TIME = 24 * 60 * 60 * 1000;

    /**
     * jwt密钥
     * */
    private static final String SECRET = "this_is_secret";

    /**
     * 生成jwt字符串，十分钟后过期  JWT(json web token)
     * @param userId
     * @param pwd,Map的value只能存放值的类型为：Map，List，Boolean，Integer，Long，Double，String and Date
     * @return
     * */
    public static String sign(String userId, String pwd) {
        try {
            Map<String, Object> info = new HashMap<>();
            info.put("role", pwd);
            Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            return JWT.create()
                    //将userId保存到token里面
                    .withAudience(userId)
                    //存放自定义数据
                    .withClaim("info", info)
                    //五分钟后token过期
                    .withExpiresAt(date)
                    //token的密钥
                    .sign(algorithm);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 根据token获取userId
     * @param token
     * @return
     * */
    public static String getUserId(String token) {
        try {
            String userId = JWT.decode(token).getAudience().get(0);
            return userId;
        }catch (JWTDecodeException e) {
            return null;
        }
    }

    /**
     * 根据token获取自定义数据info
     * @param token
     * @return
     * */
    public static Map<String, Object> getInfo(String token) {
        try {
            return JWT.decode(token).getClaim("info").asMap();
        }catch (JWTDecodeException e) {
            return null;
        }
    }

    /**
     * 校验token
     * @param token
     * @return
     * */
    public static boolean checkSign(String token) {
        try {
            Algorithm algorithm  = Algorithm.HMAC256(SECRET);
            JWTVerifier verifier = JWT.require(algorithm)
                    //.withClaim("username, username)
                    .build();
            verifier.verify(token);
            return true;
        }catch (JWTVerificationException e) {
            throw new RuntimeException("token 无效，请重新获取");
        }
    }

}

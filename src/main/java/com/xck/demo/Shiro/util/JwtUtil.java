package com.xck.demo.Shiro.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.xck.demo.constant.RedisConstant;
import io.jsonwebtoken.Jwts;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author xck
 * @date 2020/5/27 16:03
 */
public class JwtUtil {

    /**
     * 生成签名,EXPIRE_TIME 分钟后过期
     * @param secret 用户密码
     * @param map 需要放进token的信息
     * @return
     */
    public static String sign(String secret, Map<String,Object> map){
        if (null == map){
            map = new HashMap<>(16);
    }
        Date date = new Date(System.currentTimeMillis() + RedisConstant.EXPIRE_TIME);
        Algorithm algorithm = Algorithm.HMAC256(secret);
        //设置头信息
        Map<String, Object> header = new HashMap<>(2);
        header.put("typ", "JWT");
        header.put("alg", "HS256");
        return JWT.create()
                .withHeader(header)
                .withClaim("id", String.valueOf(map.get("id")))
                .withClaim("pass",secret)
                .withClaim("class", String.valueOf(map.get("class")))
                .withExpiresAt(date)
                .sign(algorithm);
    }


    /**
     * 校验token是否正确
     *
     * @param token  密钥
     * @param secret 用户的密码
     * @return 是否正确
     */
    public static boolean verity(String token,String secret) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT jwt = verifier.verify(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 获得token中的信息无需secret解密也能获得
     *@param token 密钥
     * @return token中包含的用户id
     */
    public static String getId(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("id").asString();
        } catch (JWTDecodeException e) {
            return null;
        }
    }

    /**
     * 获得token中的信息无需secret解密也能获得
     *@param token 密钥
     * @return token中包含的用户password
     */
    public static String getPass(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("pass").asString();
        } catch (JWTDecodeException e) {
            return null;
        }
    }

    /**
     * 获得token中的信息无需secret解密也能获得
     *@param token 密钥
     * @return token中包含的用户class
     */
    public static String getClass(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("class").asString();
        } catch (JWTDecodeException e) {
            return null;
        }
    }

    public static void main(String[] args) {
        Map<String,Object> map = new HashMap<>(16);
        map.put("id",19);
        map.put("class","计算机");
        String token = JwtUtil.sign("1234",map);
        System.out.println("token:"+token+"\n");
        System.out.println("class:"+JwtUtil.getClass(token)+"\n");
        System.out.println("id:"+JwtUtil.getId("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJwYXNzIjoiMTIzNCIsImlkIjoiMTkiLCJleHAiOjE1OTA2NzY1MTEsImNsYXNzIjoi6K6h566X5py656eR5a2m5LiO5oqA5pyv5LqM54-tIn0.4Bh7foYqL1Yo83rZVHLbZ9PJO2vc4WVQVDQBts0YzvU"));
        System.out.println("pass:"+JwtUtil.getPass(token));
        System.out.println(JwtUtil.verity("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJwYXNzIjoiMTIzNCIsImlkIjoiMTkiLCJleHAiOjE1OTA2MzM3OTMsImNsYXNzIjoi6K6h566X5py656eR5a2m5LiO5oqA5pyv5LqM54-tIn0.4fMwTy4qKaA5OFYiNE4F5nH6W4S42No1x1-l_P1mQQ0","1234"));
    }

}

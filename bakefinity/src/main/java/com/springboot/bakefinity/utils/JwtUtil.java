package com.springboot.bakefinity.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Date;

public class JwtUtil {
    private final long expirationMillis = 3600000;
    private final String secretKey="UCjDkp3AtNp0CulNGght5KnBFzTRBBdk";

    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationMillis))
                .signWith(getSecretkey(), SignatureAlgorithm.HS256)
                .compact();
    }
    public String getUserNameFromJwt(String jwt)
    {
        return getPayLoad(jwt).getSubject();
    }
    public Claims getPayLoad(String jwt)
    {
        return Jwts.parserBuilder().setSigningKey(getSecretkey()).build().parseClaimsJws(jwt).getBody();
    }
    public Key getSecretkey()
    {
        byte[] keyBytes=secretKey.getBytes();
        return new SecretKeySpec(keyBytes,0,keyBytes.length,"HmacSHA256");
    }
}

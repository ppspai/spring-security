package com.example.test.util;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.example.test.vo.UserDetailsImpl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenProvider implements AuthenticationProvider{

    private final String key;

    /*

    // PropertyPlaceHolderConfigurer 방식
    public JwtTokenProvider(@Value("${jwt.secret.key}") String key) {
        this.key = key;
    }
    */

    // SpEL(Spring Expression Language) 방식
    public JwtTokenProvider(@Value("#{environment['jwt.secret.key']}") String key) {
        this.key = key;
    }

    public String createToken(String userId) {
        return Jwts.builder()
                .signWith(new SecretKeySpec(key.getBytes(), SignatureAlgorithm.HS512.getJcaName()))
                .setSubject(userId)
                .setIssuedAt(Timestamp.valueOf(LocalDateTime.now()))
                .setExpiration(Date.from(Instant.now().plus(30, ChronoUnit.MINUTES)))
                .compact();
    }

    public String validateToken(String token) {
        Jws<Claims> claims = getClaims(token);
        return claims
                .getBody()
                .getSubject();
    }

    public Authentication getAuthentication(String accessToken) {
        // 토큰 복호화
        Jws<Claims> claims = Jwts.parserBuilder()
                            .setSigningKey(key.getBytes())
                            .build()
                            .parseClaimsJws(accessToken);
 
        // 클레임에서 권한 정보 가져오기
        // Collection<? extends GrantedAuthority> authorities =
        //         Arrays.stream(claims.get("auth").toString().split(","))
        //                 .map(SimpleGrantedAuthority::new)
        //                 .collect(Collectors.toList());
        
        Map<String, Object> cc = claims.getBody();
        
        // List<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList((List)cc.get("role"));
 
        // UserDetails 객체를 만들어서 Authentication 리턴
        UserDetails principal = new UserDetailsImpl("", "", (String)cc.get("id"));
        return new UsernamePasswordAuthenticationToken(principal, "");
    }


    public boolean isTokenValid(String token) {
        if (token == null) {
            return false;
        }
        Jws<Claims> claims = getClaims(token);
        long expiration = claims
                            .getBody()
                            .getExpiration().getTime();
        long cur = System.currentTimeMillis();
        return expiration > cur;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        try {
            String username = (String) authentication.getPrincipal();
            return new UsernamePasswordAuthenticationToken(authentication, username);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

    public Jws<Claims> getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key.getBytes())
                .build()
                .parseClaimsJws(token);
    }

}

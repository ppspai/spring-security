package com.example.test.interceptor;

import java.io.IOException;

import org.springframework.web.servlet.HandlerInterceptor;

import com.example.test.util.JwtTokenProvider;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class JwtTokenInterceptor implements HandlerInterceptor{

    final JwtTokenProvider tokenProvider;

    @Override
    public boolean preHandle(final HttpServletRequest request, final HttpServletResponse response, final Object handler) throws IOException {
        HttpSession session = request.getSession();

        final String token = (String)session.getAttribute("token");
        if(tokenProvider.isTokenValid(token)) {
            return true;
        }
        response.sendRedirect("/error/unauthorized");
        
        return false;
    }
}

package com.springboot.bakefinity.security.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Service
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    JwtManager jwtManager;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String head=request.getHeader("Authorization");
        if(head==null||!head.startsWith("Bearer "))
        {
            filterChain.doFilter(request,response);

        }
        else
        {
            String jwt=head.substring(7);
            JwtAuthentication jwtAuthentication=new JwtAuthentication(jwt);

            jwtAuthentication=(JwtAuthentication) jwtManager.authenticate(jwtAuthentication);
            if(jwtAuthentication.isAuthenticated())
            {
                filterChain.doFilter(request,response);
            }
            else
            {
                System.out.println("Unauthorized");
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            }
        }
        filterChain.doFilter(request,response);


    }
}
//package com.oauth2.security;
//
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.web.filter.GenericFilterBean;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import javax.servlet.http.HttpServletRequest;
//import java.io.IOException;
//
//public class RequestAuthenticationFilter extends GenericFilterBean {
//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//
//        Authentication authentication = AuthenticationToken.getAuthentication((HttpServletRequest) servletRequest);
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//        filterChain.doFilter(servletRequest, servletResponse);
//    }
//}

package com.grabIt.util;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.OncePerRequestFilter;

public class CorsFilter  extends OncePerRequestFilter {

	  @Override
	  protected void doFilterInternal(HttpServletRequest request,
	    HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
	    if(request.getHeader("Access-Control-Request-Method") != null
	          && "OPTIONS".equals(request.getMethod())) {
	      response.addHeader("Access-Control-Allow-Origin", "*");
	      response.addHeader("Access-Control-Allow-Methods", "POST,GET,UPDATE");
	      
	    }
	    filterChain.doFilter(request, response);
	  }
	}
/**
 * 
 */
package com.sb.kafka.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.sb.kafka.utils.CorsUtils;

/**
 * Implementation of filter to handle cross origin requests
 * 
 * @author ankur.mahajan
 * @written 03-Mar-2018
 */
@Order(1)
@Component
public class CrossOriginRequestFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;

		HttpServletResponse httpResponse = (HttpServletResponse) response;
		CorsUtils.allowAllOrigins(httpRequest, httpResponse);
		chain.doFilter(httpRequest, httpResponse);

	}

	@Override
	public void destroy() {

	}
}

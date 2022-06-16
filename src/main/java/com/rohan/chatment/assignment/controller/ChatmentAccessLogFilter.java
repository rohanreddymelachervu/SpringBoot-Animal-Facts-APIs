package com.rohan.chatment.assignment.controller;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import ch.qos.logback.core.status.Status;

@Component
public class ChatmentAccessLogFilter implements Filter {
	@Value("${chatment.access.log.apikey}")
	private String accessAPIKey;
	Logger logger=LoggerFactory.getLogger(ChatmentAccessLogFilter.class);
	private final String ACCESSLOGENDPOINT = "/access/log";
	private final String AUTHORIZATIONHEADER = "Authorization";
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		logger.info("Requested URI is: " + httpRequest.getRequestURI());;
		if(httpRequest.getRequestURI().equals(ACCESSLOGENDPOINT)) {
			logger.info("Access Log Endpoint is called. Authenticating API key...");
			boolean validKey = false;
			String authHeader = httpRequest.getHeader(AUTHORIZATIONHEADER);
			if(authHeader == null || authHeader.isEmpty()) {
				logger.info("Received blank authorization header");
				logger.info("Access denied");
				httpResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
				return;
			}
			logger.info("Received API Key is: " + authHeader);
			validKey = apiKeyAuthentication(authHeader);
			if(validKey) {
				logger.info("Access granted");
				chain.doFilter(request, response);
			}
			else {
				logger.info("Access denied");
				httpResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
				return;
			}
		}
		else {
			chain.doFilter(request, response);
		}
	}
	private boolean apiKeyAuthentication(String authHeader) {
		return authHeader.equals(accessAPIKey);
	}

}

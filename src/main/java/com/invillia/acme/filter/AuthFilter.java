package com.invillia.acme.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.invillia.acme.exceptions.AuthHeaderNotFoundException;
import com.invillia.acme.security.TokenManagement;

@Component
public class AuthFilter implements Filter {

	@Autowired
	private TokenManagement tokenManagement;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		if (req.getRequestURI().equals("/acme/auth")) {
			chain.doFilter(request, response);
		} else {
			String auth = req.getHeader("auth");
			if (!this.tokenManagement.isValid(auth)) {
				throw new AuthHeaderNotFoundException("Sem autorizacao.");
			} else {
				chain.doFilter(request, response);
			}
		}
	}

}

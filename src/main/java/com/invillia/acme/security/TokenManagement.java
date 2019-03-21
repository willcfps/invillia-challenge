package com.invillia.acme.security;

import java.util.UUID;

import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

@Component
public class TokenManagement {

	private String token;

	public String getToken() {
		this.token = UUID.randomUUID().toString();
		return this.token;
	}

	public boolean isValid(String token) {
		if (ObjectUtils.isEmpty(this.token) || ObjectUtils.isEmpty(token)) {
			return false;
		} else {
			return this.token.equals(token);
		}
	}
}

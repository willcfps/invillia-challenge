package com.invillia.acme.exceptions;

import java.io.IOException;

public class AuthHeaderNotFoundException extends IOException {

	private static final long serialVersionUID = 2353927931614418385L;

	public AuthHeaderNotFoundException(String message) {
		super(message);
	}
}

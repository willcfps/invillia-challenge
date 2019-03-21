package com.invillia.acme.exceptions;

public class EntityNotFoundException extends Exception {

	private static final long serialVersionUID = 2353927931614418385L;

	public EntityNotFoundException(String message) {
		super(message);
	}
}

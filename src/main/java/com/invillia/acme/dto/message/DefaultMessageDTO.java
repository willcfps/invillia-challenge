package com.invillia.acme.dto.message;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(value = Include.NON_NULL)
public class DefaultMessageDTO {
	private String status;
	private String shortMessage;
	private String message;

	public DefaultMessageDTO() {

	}

	public DefaultMessageDTO(String shortMessage) {
		super();
		this.shortMessage = shortMessage;
	}
	
	public DefaultMessageDTO(String shortMessage, String message) {
		super();
		this.shortMessage = shortMessage;
		this.message = message;
	}

	public DefaultMessageDTO(String status, String shortMessage, String message) {
		super();
		this.status = status;
		this.shortMessage = shortMessage;
		this.message = message;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getShortMessage() {
		return shortMessage;
	}

	public void setShortMessage(String shortMessage) {
		this.shortMessage = shortMessage;
	}
}

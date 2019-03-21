package com.invillia.acme.dto.message.validation;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.invillia.acme.dto.message.DefaultMessageDTO;

@JsonInclude(value = Include.NON_NULL)
public class ValidationMessageDTO extends DefaultMessageDTO {
	private List<DefaultMessageDTO> messages;

	public ValidationMessageDTO() {
		super();
		this.messages = new ArrayList<>();
	}
	
	public ValidationMessageDTO(String message) {
		super(message);
		this.messages = new ArrayList<>();
	}
	
	public ValidationMessageDTO(String status, String message) {
		super(status, message);
		this.messages = new ArrayList<>();
	}

	public List<DefaultMessageDTO> getMessages() {
		return messages;
	}

	public void setMessages(List<DefaultMessageDTO> messages) {
		this.messages = messages;
	}
}

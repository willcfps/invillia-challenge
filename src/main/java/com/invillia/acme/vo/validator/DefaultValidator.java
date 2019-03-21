package com.invillia.acme.vo.validator;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import com.invillia.acme.dto.message.DefaultMessageDTO;
import com.invillia.acme.dto.message.validation.ValidationMessageDTO;

public class DefaultValidator {

	private DefaultValidator() {

	}

	public static DefaultMessageDTO hasErrors(BindingResult v) {
		ValidationMessageDTO m = new ValidationMessageDTO("Informações pendentes.");
		for (FieldError fe : v.getFieldErrors()) {
			m.getMessages().add(new DefaultMessageDTO(fe.getDefaultMessage()));
		}

		return m;
	}
}

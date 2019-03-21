package com.invillia.acme.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.invillia.acme.dto.message.DefaultMessageDTO;
import com.invillia.acme.security.TokenManagement;
import com.invillia.acme.vo.UserModel;
import com.invillia.acme.vo.validator.DefaultValidator;

@RestController
public class AuthController {

	private String teste = "teste";

	@Autowired
	private TokenManagement tokenManagement;

	@PostMapping(path = "/auth")
	public ResponseEntity<DefaultMessageDTO> createAddress(@Valid @RequestBody UserModel model,
			BindingResult validation) {
		if (validation.hasErrors()) {
			return new ResponseEntity<>(DefaultValidator.hasErrors(validation), HttpStatus.BAD_REQUEST);
		} else {
			if (model.getPassword().equals(this.teste) && model.getUsername().equals(this.teste)) {
				return new ResponseEntity<>(new DefaultMessageDTO(this.tokenManagement.getToken()), HttpStatus.OK);
			} else {
				return new ResponseEntity<>(new DefaultMessageDTO("Nome de usuário e/ou senha inválido."),
						HttpStatus.NOT_ACCEPTABLE);
			}
		}
	}
}

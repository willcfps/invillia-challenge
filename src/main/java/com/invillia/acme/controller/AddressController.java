package com.invillia.acme.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.invillia.acme.dto.message.DefaultMessageDTO;
import com.invillia.acme.dto.message.ShopMessageDTO;
import com.invillia.acme.exceptions.EntityNotFoundException;
import com.invillia.acme.repository.interfaces.AddressRepository;
import com.invillia.acme.vo.AddressModel;
import com.invillia.acme.vo.validator.DefaultValidator;

@RestController
public class AddressController {

	@Autowired
	private AddressRepository repository;

	@PostMapping(path = "/address")
	public ResponseEntity<DefaultMessageDTO> createAddress(@Valid @RequestBody AddressModel model,
			BindingResult validation) {
		if (validation.hasErrors()) {
			return new ResponseEntity<>(DefaultValidator.hasErrors(validation), HttpStatus.BAD_REQUEST);
		} else {
			this.repository.save(model);
			return new ResponseEntity<>(new DefaultMessageDTO("Endereço criado com sucesso."), HttpStatus.OK);
		}
	}

	@PutMapping(path = "/address")
	public ResponseEntity<DefaultMessageDTO> updateAddress(@Valid @RequestBody AddressModel model,
			BindingResult validation) {
		if (validation.hasErrors()) {
			return new ResponseEntity<>(DefaultValidator.hasErrors(validation), HttpStatus.BAD_REQUEST);
		} else {
			try {
				this.repository.update(model);
				return new ResponseEntity<>(new DefaultMessageDTO("Endereço atualizado com sucesso."), HttpStatus.OK);
			} catch (EntityNotFoundException e) {
				return new ResponseEntity<>(new DefaultMessageDTO(e.getMessage()), HttpStatus.BAD_REQUEST);
			}
		}
	}

	@GetMapping(path = "/address")
	public ResponseEntity<DefaultMessageDTO> getAddress(@RequestParam(value = "id") Integer id) {
		if (id == null) {
			return new ResponseEntity<>(new DefaultMessageDTO("Informe o id do endereço."), HttpStatus.BAD_REQUEST);
		} else {
			Optional<AddressModel> optional = this.repository.findById(id);
			if (optional.isPresent()) {
				ShopMessageDTO<AddressModel> message = new ShopMessageDTO<>();
				message.setData(optional.get());
				return new ResponseEntity<>(message, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(new DefaultMessageDTO("Endereço não encontrado."), HttpStatus.NOT_FOUND);
			}
		}
	}
}

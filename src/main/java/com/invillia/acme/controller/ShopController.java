package com.invillia.acme.controller;

import java.util.ArrayList;
import java.util.List;
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
import com.invillia.acme.repository.interfaces.ShopRepository;
import com.invillia.acme.vo.ShopModel;
import com.invillia.acme.vo.validator.DefaultValidator;

@RestController
public class ShopController {

	@Autowired
	private ShopRepository repository;

	@PostMapping(path = "/shop")
	public ResponseEntity<DefaultMessageDTO> createShop(@Valid @RequestBody ShopModel model, BindingResult validation) {
		if (validation.hasErrors()) {
			return new ResponseEntity<>(DefaultValidator.hasErrors(validation), HttpStatus.BAD_REQUEST);
		} else {
			this.repository.save(model);
			return new ResponseEntity<>(new DefaultMessageDTO("Loja criada com sucesso."), HttpStatus.OK);
		}
	}

	@PutMapping(path = "/shop")
	public ResponseEntity<DefaultMessageDTO> updateShop(@Valid @RequestBody ShopModel model, BindingResult validation) {
		if (validation.hasErrors()) {
			return new ResponseEntity<>(DefaultValidator.hasErrors(validation), HttpStatus.BAD_REQUEST);
		} else {
			try {
				this.repository.update(model);
				return new ResponseEntity<>(new DefaultMessageDTO("Loja atualizada com sucesso."), HttpStatus.OK);
			} catch (EntityNotFoundException e) {
				return new ResponseEntity<>(new DefaultMessageDTO(e.getMessage()), HttpStatus.BAD_REQUEST);
			}
		}
	}

	@GetMapping(path = "/shop")
	public ResponseEntity<DefaultMessageDTO> getShop(@RequestParam(value = "id") Integer id) {
		if (id == null) {
			return new ResponseEntity<>(new DefaultMessageDTO("Informe o id da loja."), HttpStatus.BAD_REQUEST);
		} else {
			Optional<ShopModel> optional = this.repository.findById(id);
			if (optional.isPresent()) {
				ShopMessageDTO<ShopModel> message = new ShopMessageDTO<>();
				message.setData(optional.get());

				return new ResponseEntity<>(message, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(new DefaultMessageDTO("Loja não encontrada."), HttpStatus.NOT_FOUND);
			}
		}
	}

	@GetMapping(path = "/shop/all")
	public ResponseEntity<DefaultMessageDTO> getAllShop() {
		Iterable<ShopModel> optional = this.repository.findAll();
		List<ShopModel> list = new ArrayList<>();
		optional.forEach((ShopModel m) -> {
			list.add(m);
		});

		ShopMessageDTO<List<ShopModel>> message = new ShopMessageDTO<>();
		message.setData(list);
		return new ResponseEntity<>(message, HttpStatus.OK);
	}
}

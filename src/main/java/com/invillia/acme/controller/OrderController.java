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

import com.invillia.acme.dto.message.DefaultMessageDTO;
import com.invillia.acme.dto.message.ShopMessageDTO;
import com.invillia.acme.exceptions.EntityNotFoundException;
import com.invillia.acme.repository.interfaces.OrderRepository;
import com.invillia.acme.vo.OrderModel;
import com.invillia.acme.vo.validator.DefaultValidator;

public class OrderController {

	@Autowired
	private OrderRepository repository;

	@PostMapping(path = "/order")
	public ResponseEntity<DefaultMessageDTO> createOrder(@Valid @RequestBody OrderModel model,
			BindingResult validation) {
		if (validation.hasErrors()) {
			return new ResponseEntity<>(DefaultValidator.hasErrors(validation), HttpStatus.BAD_REQUEST);
		} else {
			this.repository.save(model);
			return new ResponseEntity<>(new DefaultMessageDTO("Pedido criado com sucesso."), HttpStatus.OK);
		}
	}

	@PutMapping(path = "/order")
	public ResponseEntity<DefaultMessageDTO> updateOrder(@Valid @RequestBody OrderModel model,
			BindingResult validation) {
		if (validation.hasErrors()) {
			return new ResponseEntity<>(DefaultValidator.hasErrors(validation), HttpStatus.BAD_REQUEST);
		} else {
			try {
				this.repository.update(model);
				return new ResponseEntity<>(new DefaultMessageDTO("Pedido atualizado com sucesso."), HttpStatus.OK);
			} catch (EntityNotFoundException e) {
				return new ResponseEntity<>(new DefaultMessageDTO(e.getMessage()), HttpStatus.BAD_REQUEST);
			}
		}
	}

	@GetMapping(path = "/order")
	public ResponseEntity<DefaultMessageDTO> getOrder(@RequestParam(value = "id") Integer id) {
		if (id == null) {
			return new ResponseEntity<>(new DefaultMessageDTO("Informe o id do pedido."), HttpStatus.BAD_REQUEST);
		} else {
			Optional<OrderModel> optional = this.repository.findById(id);
			if (optional.isPresent()) {
				ShopMessageDTO<OrderModel> message = new ShopMessageDTO<>();
				message.setData(optional.get());

				return new ResponseEntity<>(message, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(new DefaultMessageDTO("Pedido não encontrado."), HttpStatus.NOT_FOUND);
			}
		}
	}
}

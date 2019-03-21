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
import com.invillia.acme.repository.interfaces.OrderItemRepository;
import com.invillia.acme.vo.OrderItemModel;
import com.invillia.acme.vo.validator.DefaultValidator;

public class OrderItemController {

	@Autowired
	private OrderItemRepository itemRepository;

	@PostMapping(path = "/order/item")
	public ResponseEntity<DefaultMessageDTO> createItem(@Valid @RequestBody OrderItemModel model,
			BindingResult validation) {
		if (validation.hasErrors()) {
			return new ResponseEntity<>(DefaultValidator.hasErrors(validation), HttpStatus.BAD_REQUEST);
		} else {
			this.itemRepository.save(model);
			return new ResponseEntity<>(new DefaultMessageDTO("Item do pedido criado com sucesso."), HttpStatus.OK);
		}
	}

	@PutMapping(path = "/order/item")
	public ResponseEntity<DefaultMessageDTO> updateItem(@Valid @RequestBody OrderItemModel model,
			BindingResult validation) {
		if (validation.hasErrors()) {
			return new ResponseEntity<>(DefaultValidator.hasErrors(validation), HttpStatus.BAD_REQUEST);
		} else {
			try {
				this.itemRepository.update(model);
				return new ResponseEntity<>(new DefaultMessageDTO("Item do pedido atualizado com sucesso."),
						HttpStatus.OK);
			} catch (EntityNotFoundException e) {
				return new ResponseEntity<>(new DefaultMessageDTO(e.getMessage()), HttpStatus.BAD_REQUEST);
			}
		}
	}

	@GetMapping(path = "/order")
	public ResponseEntity<DefaultMessageDTO> getItem(@RequestParam(value = "id") Integer id) {
		if (id == null) {
			return new ResponseEntity<>(new DefaultMessageDTO("Informe o id do item."), HttpStatus.BAD_REQUEST);
		} else {
			Optional<OrderItemModel> optional = this.itemRepository.findById(id);
			if (optional.isPresent()) {
				ShopMessageDTO<OrderItemModel> message = new ShopMessageDTO<>();
				message.setData(optional.get());

				return new ResponseEntity<>(message, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(new DefaultMessageDTO("Item de pedido não encontrado."), HttpStatus.NOT_FOUND);
			}
		}
	}
}

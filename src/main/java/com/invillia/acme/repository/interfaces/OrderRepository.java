package com.invillia.acme.repository.interfaces;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.invillia.acme.exceptions.EntityNotFoundException;
import com.invillia.acme.vo.OrderModel;

public interface OrderRepository extends CrudRepository<OrderModel, Integer> {

	String NOT_FOUND = "Pedido não encontrado.";

	public default OrderModel update(OrderModel shop) throws EntityNotFoundException {
		if (shop.getId() == null) {
			throw new EntityNotFoundException(NOT_FOUND);
		} else {
			Optional<OrderModel> optional = this.findById(shop.getId());
			if (optional.isPresent()) {
				shop.setId(optional.get().getId());
				return this.save(shop);
			} else {
				throw new EntityNotFoundException(NOT_FOUND);
			}
		}
	}

}

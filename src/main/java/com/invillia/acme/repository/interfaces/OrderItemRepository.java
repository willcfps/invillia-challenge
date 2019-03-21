package com.invillia.acme.repository.interfaces;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.invillia.acme.exceptions.EntityNotFoundException;
import com.invillia.acme.vo.OrderItemModel;

public interface OrderItemRepository extends CrudRepository<OrderItemModel, Integer> {

	String NOT_FOUND = "Item do pedido não encontrado.";

	public default OrderItemModel update(OrderItemModel shop) throws EntityNotFoundException {
		if (shop.getId() == null) {
			throw new EntityNotFoundException(NOT_FOUND);
		} else {
			Optional<OrderItemModel> optional = this.findById(shop.getId());
			if (optional.isPresent()) {
				shop.setId(optional.get().getId());
				return this.save(shop);
			} else {
				throw new EntityNotFoundException(NOT_FOUND);
			}
		}
	}

}

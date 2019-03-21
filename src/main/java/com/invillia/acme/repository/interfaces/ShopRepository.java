package com.invillia.acme.repository.interfaces;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.invillia.acme.exceptions.EntityNotFoundException;
import com.invillia.acme.vo.ShopModel;

public interface ShopRepository extends CrudRepository<ShopModel, Integer> {

	public default ShopModel update(ShopModel shop) throws EntityNotFoundException {
		if (shop.getId() == null) {
			throw new EntityNotFoundException("Loja não encontrada.");
		} else {
			Optional<ShopModel> optional = this.findById(shop.getId());
			if (optional.isPresent()) {
				shop.setId(optional.get().getId());
				return this.save(shop);
			} else {
				throw new EntityNotFoundException("Loja não encontrada.");
			}
		}
	}

}

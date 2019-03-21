package com.invillia.acme.repository.interfaces;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.data.repository.CrudRepository;

import com.invillia.acme.exceptions.EntityNotFoundException;
import com.invillia.acme.vo.AddressModel;

public interface AddressRepository extends CrudRepository<AddressModel, Integer> {

	String NOT_FOUND = "Endereço não encontrado.";

	public default AddressModel update(@Valid AddressModel shop) throws EntityNotFoundException {
		if (shop.getId() == null) {
			throw new EntityNotFoundException(NOT_FOUND);
		} else {
			Optional<AddressModel> optional = this.findById(shop.getId());
			if (optional.isPresent()) {
				shop.setId(optional.get().getId());
				return this.save(shop);
			} else {
				throw new EntityNotFoundException(NOT_FOUND);
			}
		}
	}

}

package com.invillia.acme.vo;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;

import com.invillia.acme.enums.StatusOrderEnum;

@Entity
public class OrderModel {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Temporal(TemporalType.TIMESTAMP)
	@PastOrPresent(message = "Data de confirmação não pode ser uma data futura.")
	private Date confirmationDate;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "ADDRESS_ID", foreignKey = @ForeignKey(name = "FK_ORDER_ADDRESS"))
	private AddressModel address;

	@Enumerated(value = EnumType.STRING)
	@NotNull(message = "Informe o status do pedido.")
	private StatusOrderEnum status;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@NotEmpty(message = "Adicione ao menos 1 (um) item ao pedido.")
	private Set<OrderItemModel> items;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getConfirmationDate() {
		return confirmationDate;
	}

	public void setConfirmationDate(Date confirmationDate) {
		this.confirmationDate = confirmationDate;
	}

	public AddressModel getAddress() {
		return address;
	}

	public void setAddress(AddressModel address) {
		this.address = address;
	}

	public StatusOrderEnum getStatus() {
		return status;
	}

	public void setStatus(StatusOrderEnum status) {
		this.status = status;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderModel other = (OrderModel) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public Set<OrderItemModel> getItems() {
		return items;
	}

	public void setItems(Set<OrderItemModel> items) {
		this.items = items;
	}

}

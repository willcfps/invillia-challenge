package com.invillia.acme.dto.message;

public class ShopMessageDTO<T> extends DefaultMessageDTO {
	private T data;

	public ShopMessageDTO() {
		super();
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

}

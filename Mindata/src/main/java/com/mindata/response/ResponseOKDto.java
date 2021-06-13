package com.mindata.response;

/**
 * Devuelve un ResponseOKDto con un unico resultado de un objeto del modelo 
 * @author Leo
 *
 * @param <T>
 */
public class ResponseOKDto<T> extends ResponseDto {

	private T model;

	public ResponseOKDto(String endPoint, String tipoMetodo, String codigo, T dto) {
		super(endPoint, tipoMetodo, codigo);
		this.setModel(dto);
	}

	public T getModel() {
		return model;
	}

	public void setModel(T model) {
		this.model = model;
	}

}

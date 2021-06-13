package com.mindata.response;

import java.util.List;

/**
 * Devuelve un ResponseOKListDto con un listado de objetos del modelo
 * @author Leo
 *
 */
public class ResponseOKListDto<T> extends ResponseDto {

	private List<T> list;

	public ResponseOKListDto() {}

	public ResponseOKListDto(String endPoint, String tipoMetodo, String codigo, List<T> dto) {
		super(endPoint, tipoMetodo, codigo);
		this.setList(dto);
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

}

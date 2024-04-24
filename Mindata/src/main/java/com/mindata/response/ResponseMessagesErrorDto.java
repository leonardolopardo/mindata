package com.mindata.response;

import java.util.List;

/**
 * Devuelve un ResponseErrorDto utilizado para devolver errores
 * @author Leo
 *
 */
public class ResponseErrorDto extends ResponseDto {
	
	/**
	 * mensajes de retorno
	 */
	private List<String> mensajes;
	
	public ResponseErrorDto() {}

	public ResponseErrorDto(String endPoint, String tipoMetodo, String codigo, List<String> mensajes) {
		super(endPoint, tipoMetodo, codigo);
		this.mensajes = mensajes;
	}
	
	public List<String> getMensajes() {
		return mensajes;
	}

	public void setMensajes(List<String> mensajes) {
		this.mensajes = mensajes;
	}
	
}

package com.mindata.response;

public class ResponseErrorDto extends ResponseDto {

	/**
	 * mensajes de retorno
	 */
	private String mensaje;

	public ResponseErrorDto() {
	}

	public ResponseErrorDto(String endPoint, String tipoMetodo, String codigo, String mensaje) {
		super(endPoint, tipoMetodo, codigo);
		this.mensaje = mensaje;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

}

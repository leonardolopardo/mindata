package com.mindata.response;

/**
 * Abstraccion de los Dtos de respuesta de los controllers
 * @author Leo
 *
 */
public abstract class ResponseDto {

	/**
	 * path del endpoint 
	 */
	private String endPoint;
	/**
	 * GET, POST, etc
	 */
	private String tipoMetodo;
	/**
	 * codigo de retorno 200, 400, etc
	 */
	private String codigo;

	public ResponseDto() {}

	public ResponseDto(String endPoint, String tipoMetodo, String codigo) {
		this.endPoint = endPoint;
		this.tipoMetodo = tipoMetodo;
		this.codigo = codigo;
	}
	
	public String getEndPoint() {
		return endPoint;
	}

	public void setEndPoint(String endPoint) {
		this.endPoint = endPoint;
	}

	public String getTipoMetodo() {
		return tipoMetodo;
	}

	public void setTipoMetodo(String tipoMetodo) {
		this.tipoMetodo = tipoMetodo;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

}

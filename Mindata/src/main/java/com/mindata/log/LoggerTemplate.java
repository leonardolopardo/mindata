package com.mindata.log;

public class LoggerTemplate {

	private LoggerTemplate() {}

	private static final String RESPONSE_OK = "RETORNANDO RESPONSEOK";

	public static String inicialize(String controller, String method) {
		return "INICIALIZANDO CONTROLLER: " + controller + "/" + "METODO: " + method;
	}

	public static String validator(String validator) {
		return "EJECUTANDO VALIDATOR: " + validator;
	}

	public static String method(String method) {
		return "EJECUTANDO METODO: " + method;
	}

	public static String responseOK() {
		return RESPONSE_OK;
	}

}

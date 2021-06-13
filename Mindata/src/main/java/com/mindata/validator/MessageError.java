package com.mindata.validator;

/**
 * Message Error
 * @author Leo
 *
 */
public class MessageError {

	public static final String NOT_IMPLEMENTED_EXCEPTION_MESSAGE = "Metodo no implementado";
	public static final String ELEMENT_NOTFOUND_MESSAGE = "La consulta no obtuvo resultados";

	private MessageError () {}
	
	/**
	 * Print message error
	 * @param field
	 * @param validator
	 * @return
	 */
	public static String messageError(Object field, String validator) {
		return "El campo " + field + " no puede ser " + validator;
	}
	
	/**
	 * Print message error
	 * @return
	 */
	public static String elementNotFound () {
		return ELEMENT_NOTFOUND_MESSAGE;
	}
	
}

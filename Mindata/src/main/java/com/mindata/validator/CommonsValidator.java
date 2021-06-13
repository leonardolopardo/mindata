package com.mindata.validator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import com.mindata.constant.FieldConstant;

/**
 * Commons Validator
 * @author Leo
 *
 */
public class CommonsValidator {
	
	private CommonsValidator() {}
	
	private static final String VACIO = "vacio";
	
	/**
	 * Common validator by Id
	 * @param id
	 * @return
	 */
	public static List<String> validatorById(Integer id) {
		List <String> mensajes = new ArrayList<>();
		CommonsValidator.setMessageErrorsList(mensajes, 
				CommonsValidator.empty(id, FieldConstant.ID));
		return mensajes;
	}
	
	//Lists
	public static String empty (Set<?> fields) {
		if (fields == null || fields.isEmpty()) {
			return MessageError.messageError (fields, VACIO);
		}
		return null;
	}
	
	public static String empty (Map<?,?> fields) {
		if (fields == null) {
			return MessageError.messageError (fields, VACIO);
		}
		return null;
	}
	
	//Fields
	/**
	 * Verifica si un campo es vacio. Caso contrario devuelve un mensaje de error
	 * @param field
	 * @return
	 */
	public static String empty (Object field) {
		if (field == null) {
			return MessageError.messageError (field, VACIO);
		}
		return null;
	}
	
	/**
	 * Verifica si un campo es vacio. Caso contrario devuelve un mensaje de error
	 * @param field
	 * @param fieldName
	 * @return
	 */
	public static String empty (Long field, String fieldName) {
		if (field == null) {
			return MessageError.messageError(fieldName, VACIO);
		}
		return null;
	}
	
	/**
	 * Verifica si un campo es vacio. Caso contrario devuelve un mensaje de error
	 * @param field
	 * @param fieldName
	 * @return
	 */
	public static String empty (Integer field, String fieldName) {
		if (field == null) {
			return MessageError.messageError(fieldName, VACIO);
		}
		return null;
	}
	
	/**
	 * Verifica si un campo es vacio. Caso contrario devuelve un mensaje de error
	 * @param field
	 * @param fieldName
	 * @return
	 */
	public static String empty (String field, String fieldName) {
		if (StringUtils.isEmpty(field)) {
			return MessageError.messageError(fieldName, VACIO);
		}
		return null;
	}
	
	/**
	 * Si los parametros fields tienen mensaje de error, los asigna al listado de mensaje de errores
	 * @param list
	 * @param fields
	 */
	public static void setMessageErrorsList(List <String> mensajes, String ... fields ) {
		for (String fieldToValidate : fields) {
			if (fieldToValidate != null) {
				mensajes.add(fieldToValidate);
			}
		}
	}
	
	public static String elementNotFound () {
		return MessageError.elementNotFound();
	}
	
}

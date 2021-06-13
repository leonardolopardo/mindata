package com.mindata.controller;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.mindata.response.ResponseDto;

/**
 * Interfase para listados
 * @author Leo
 *
 * @param <T>
 */
public interface IListController<T> {
	/**
	 * FindOne <T>
	 * @param dto
	 * @return
	 */
	@PostMapping(value = "/findById")
	ResponseDto findById (@RequestBody T dto);
	
	/**
	 * FindAny  <T>
	 * @param dto
	 * @return
	 */
	@PostMapping(value = "/findByName")
	ResponseDto findByName (@RequestBody T dto);

	/**
	 * FindAll <T>
	 * @return
	 */
	@PostMapping(value = "/findAll")
	ResponseDto findAll ();
}

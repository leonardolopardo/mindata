package com.mindata.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.mindata.response.ResponseDto;

/**
 * Interfase para listados
 * @author Leo
 *
 * @param <T>
 */
public interface IListController<T> {
	/**
	 * FindAny  <T>
	 * @param dto
	 * @return
	 */
	@GetMapping(value = "/findByName")
	ResponseDto findByName (@RequestParam String name);

	/**
	 * FindOne <Dto>
	 * @param dto
	 * @return
	 */
	@GetMapping(value = "/{id}")
	ResponseDto findOne (@PathVariable Long id);
	
	/**
	 * FindAll <Dto>
	 * @return
	 */
	@GetMapping(value = "")
	ResponseDto findAll ();
	
	
	
}

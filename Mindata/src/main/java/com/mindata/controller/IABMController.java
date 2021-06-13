package com.mindata.controller;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.mindata.response.ResponseDto;

/**
 * Interfase ABM Controller
 * @author Leo
 *
 * @param <T>
 */
public interface IABMController <T> {
	/**
	 * Modify <T>
	 * @param dto
	 *  * See path in Interface
	 * @return
	 */
	@PostMapping(value = "/modify")
	ResponseDto modify (@RequestBody T dto);
	
	/**
	 * Delete <T>
	 * See path in Interface
	 */
	@PostMapping(value = "/delete")
	ResponseDto deleteById (@RequestBody T dto);

}

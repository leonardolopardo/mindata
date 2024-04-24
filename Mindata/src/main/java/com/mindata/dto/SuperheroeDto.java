package com.mindata.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class SuperheroeDto {

	private Long id;

	@NotEmpty(message = "Name no puede ser vacio")
	@Size(max = 255, message = "Name no puede superar los 255 caracteres")
	private String name;

	public SuperheroeDto() {
	}

	public SuperheroeDto(Long id, String name) {
		this.id = id;
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}

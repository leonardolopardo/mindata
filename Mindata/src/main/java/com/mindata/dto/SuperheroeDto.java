package com.mindata.dto;

import java.io.Serializable;

public class SuperheroeDto implements Serializable {

	private static final long serialVersionUID = 6190637089973025227L;

	private Integer id;
	private String name;
	
	public SuperheroeDto () {}
	public SuperheroeDto (Integer id, String name) {
		this.id = id;
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}

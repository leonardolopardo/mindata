package com.mindata.builder;

import java.util.List;

public interface IBuilder <Model, DTO> {
	
	/**
	 * Dto to model
	 * @param dto
	 * @return
	 */
	public Model builderToModel (DTO dto);
	
	/**
	 * Model to Dto
	 * @param model
	 * @return
	 */
	public DTO builderToDto (Model model);
	
	/**
	 * List<Model> to List <Dto>
	 * @param list
	 * @return
	 */
	public List<DTO> builderListToDto (List<Model> list);

}

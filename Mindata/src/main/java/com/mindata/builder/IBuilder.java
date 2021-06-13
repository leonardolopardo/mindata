package com.mindata.builder;

import java.util.List;

public interface IBuilder <M, D> {
	
	/**
	 * Dto to model
	 * @param dto
	 * @return
	 */
	public M builderToModel (D dto);
	
	/**
	 * Model to Dto
	 * @param model
	 * @return
	 */
	public D builderToDto (M model);
	
	/**
	 * List<Model> to List <Dto>
	 * @param list
	 * @return
	 */
	public List<D> builderListToDto (List<M> list);

}

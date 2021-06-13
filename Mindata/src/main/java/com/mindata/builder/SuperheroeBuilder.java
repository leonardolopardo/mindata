package com.mindata.builder;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.mindata.dto.SuperheroeDto;
import com.mindata.model.Superheroe;

@Component
public class SuperheroeBuilder implements IBuilder<Superheroe, SuperheroeDto> {

	@Override
	public Superheroe builderToModel(SuperheroeDto dto) {
		return new Superheroe (dto.getId(), dto.getName());
	}

	@Override
	public SuperheroeDto builderToDto(Superheroe model) {
		return new SuperheroeDto(model.getId(), model.getName());
	}

	@Override
	public List<SuperheroeDto> builderListToDto(List<Superheroe> list) {
		List<SuperheroeDto> listDto = new ArrayList<>();

		for (Superheroe superheroe : list) {
			listDto.add(new SuperheroeDto(superheroe.getId(), superheroe.getName()));
		}
		return listDto;
	}
	
	
	
}

package com.mindata.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mindata.annotation.TrackExecutionTime;
import com.mindata.builder.SuperheroeBuilder;
import com.mindata.constant.CodigoRespuestaConstant;
import com.mindata.constant.ControllerConstant;
import com.mindata.constant.EndPointConstant;
import com.mindata.constant.FieldConstant;
import com.mindata.constant.TipoMetodoConstant;
import com.mindata.dto.SuperheroeDto;
import com.mindata.log.LoggerTemplate;
import com.mindata.model.Superheroe;
import com.mindata.response.ResponseDto;
import com.mindata.response.ResponseErrorDto;
import com.mindata.response.ResponseOKDto;
import com.mindata.response.ResponseOKListDto;
import com.mindata.service.SuperheroeService;
import com.mindata.validator.CommonsValidator;
import com.mindata.validator.IValidator;

@RequestMapping("/superheroe")
@RestController
public class SuperheroeController implements IABMController<SuperheroeDto>, IListController<SuperheroeDto> {
	
	
	static final Logger logger = Logger.getLogger(SuperheroeController.class);
	
	@Autowired
	private SuperheroeService shService;
	
	@Autowired
	private SuperheroeBuilder shBuilder;
	
	@TrackExecutionTime
	@Override
	public ResponseDto modify(SuperheroeDto dto) {
		// logger controller
		logger.info(LoggerTemplate.inicialize(ControllerConstant.SUPERHEROE_CONTROLLER, ControllerConstant.SERVICE_SAVE));

		// logger validator
		logger.info(LoggerTemplate.validator(ControllerConstant.SUPERHEROE_VALIDATOR));
		// validator
		//validId
		List<String> mensajes = IValidator.validator(dto.getId());
		//validNombre
		CommonsValidator.setMessageErrorsList(mensajes, CommonsValidator.empty(dto.getName(), FieldConstant.NOMBRE));
		
		if (!mensajes.isEmpty()) {
			return new ResponseErrorDto(ControllerConstant.SERVICE_SAVE, TipoMetodoConstant.POST, CodigoRespuestaConstant.ERROR, mensajes);
		}
		
		try {
			// logger service
			logger.info(LoggerTemplate.method(ControllerConstant.SERVICE_SAVE));

			// builder
			Superheroe superheroe = shBuilder.builderToModel(dto);	
			// save
			shService.save(superheroe);
			
			// logger response
			logger.info(LoggerTemplate.responseOK());
			// return
			return new ResponseOKDto<SuperheroeDto>(ControllerConstant.SERVICE_SAVE, TipoMetodoConstant.POST, CodigoRespuestaConstant.OK, dto);
		} catch (Exception e) {
			String messageException = e.getMessage();
			logger.error(messageException);
			mensajes.add(messageException);
			return new ResponseErrorDto(ControllerConstant.SERVICE_SAVE, TipoMetodoConstant.POST, CodigoRespuestaConstant.ERROR, mensajes);
		}
	}

	@TrackExecutionTime
	@Override
	public ResponseDto deleteById(SuperheroeDto dto) {
		// logger controller
		logger.info(LoggerTemplate.inicialize(ControllerConstant.SUPERHEROE_CONTROLLER, ControllerConstant.SERVICE_DELETE));
		
		// logger validator
		logger.info(LoggerTemplate.validator(ControllerConstant.SUPERHEROE_VALIDATOR));
		
		// validator
		List<String> mensajes = IValidator.validator(dto.getId());
		
		if (!mensajes.isEmpty()) {
			return new ResponseErrorDto(EndPointConstant.DELETE, TipoMetodoConstant.POST, CodigoRespuestaConstant.ERROR, mensajes);
		}
		
		try {
			// logger service
			logger.info(LoggerTemplate.method(ControllerConstant.SERVICE_DELETE));

			//delete
			shService.deleteById(dto.getId());

			// logger response
			logger.info(LoggerTemplate.responseOK());
			
			// return
			return new ResponseOKDto<SuperheroeDto>(EndPointConstant.DELETE, TipoMetodoConstant.POST, CodigoRespuestaConstant.OK, null);
		} catch (Exception e) {
			String messageException = e.getMessage();
			logger.error(messageException);
			mensajes.add(messageException);
			return new ResponseErrorDto(EndPointConstant.DELETE, TipoMetodoConstant.POST, CodigoRespuestaConstant.ERROR, mensajes);
		}
	}

	@TrackExecutionTime
	@Override
	public ResponseDto findById(SuperheroeDto dto) {
		// logger controller
		logger.info(LoggerTemplate.inicialize(ControllerConstant.SUPERHEROE_CONTROLLER, EndPointConstant.FIND_BY_ID));
		// logger validator
		logger.info(LoggerTemplate.validator(ControllerConstant.SUPERHEROE_VALIDATOR));
		// validator
		List<String> mensajes = IValidator.validator(dto.getId());
		if (!mensajes.isEmpty()) {
			return new ResponseErrorDto(EndPointConstant.FIND_BY_ID, TipoMetodoConstant.POST, CodigoRespuestaConstant.ERROR, mensajes);
		}
		
		try {
			// logger service
			logger.info(LoggerTemplate.method(ControllerConstant.SERVICE_FIND_BY_ID));

			// List
			Optional<Superheroe> value = shService.findById(dto.getId());
			
			if(value.isPresent()) {
				Superheroe utensilio = value.get();
				
				// Builder Model to Dto
				SuperheroeDto utensilioDto = shBuilder.builderToDto(utensilio);
				
				// logger response
				logger.info(LoggerTemplate.responseOK());
				
				// return
				return new ResponseOKDto<SuperheroeDto>(EndPointConstant.FIND_BY_ID, TipoMetodoConstant.POST, CodigoRespuestaConstant.OK, utensilioDto);
			} else {
				mensajes.add(CommonsValidator.elementNotFound());
				return new ResponseErrorDto(EndPointConstant.FIND_BY_ID, TipoMetodoConstant.POST, CodigoRespuestaConstant.ERROR, mensajes);
			}
		} catch (Exception e) {
			String messageException = e.getMessage();
			logger.error(messageException);
			mensajes.add(messageException);
			return new ResponseErrorDto(EndPointConstant.FIND_BY_ID, TipoMetodoConstant.POST, CodigoRespuestaConstant.ERROR, mensajes);
		}
	}

	@TrackExecutionTime
	@Override
	public ResponseDto findByName(SuperheroeDto dto) {
		// logger controller
		logger.info(LoggerTemplate.inicialize(ControllerConstant.SUPERHEROE_CONTROLLER, EndPointConstant.FIND_BY_NAME));
		// logger validator
		logger.info(LoggerTemplate.validator(ControllerConstant.SUPERHEROE_VALIDATOR));
		// validator
		List<String> mensajes = new ArrayList<>();
		CommonsValidator.setMessageErrorsList(mensajes, CommonsValidator.empty(dto.getName(), FieldConstant.NOMBRE));
		
		if (!mensajes.isEmpty()) {
			return new ResponseErrorDto(EndPointConstant.FIND_BY_NAME, TipoMetodoConstant.POST, CodigoRespuestaConstant.ERROR, mensajes);
		}
		
		try {
			// logger service
			logger.info(LoggerTemplate.method(ControllerConstant.SERVICE_FIND_BY_NAME));

			// List
			Superheroe sh = new Superheroe();
			sh.setName(dto.getName());
			
			List<Superheroe> shs = shService.findSuperheroeByNameLike(sh.getName());
			
			if(!shs.isEmpty()) {
				// Builder Model to Dto
				List<SuperheroeDto> shsDto = shBuilder.builderListToDto(shs);
				
				// logger response
				logger.info(LoggerTemplate.responseOK());
				
				// return
				return new ResponseOKListDto<SuperheroeDto>(EndPointConstant.FIND_BY_NAME, TipoMetodoConstant.POST, CodigoRespuestaConstant.OK, shsDto);
			} else {
				mensajes.add(CommonsValidator.elementNotFound());
				return new ResponseErrorDto(EndPointConstant.FIND_BY_NAME, TipoMetodoConstant.POST, CodigoRespuestaConstant.ERROR, mensajes);
			}
		} catch (Exception e) {
			String messageException = e.getMessage();
			logger.error(messageException);
			mensajes.add(messageException);
			return new ResponseErrorDto(EndPointConstant.FIND_BY_NAME, TipoMetodoConstant.POST, CodigoRespuestaConstant.ERROR, mensajes);
		}
	}

	@TrackExecutionTime
	@Override
	public ResponseDto findAll() {
		// logger controller
		logger.info(LoggerTemplate.inicialize(ControllerConstant.SUPERHEROE_CONTROLLER, EndPointConstant.FIND_ALL));
		
		try {
			// logger service
			logger.info(LoggerTemplate.method(ControllerConstant.SERVICE_FIND_ALL));

			// List
			List<Superheroe> listSuperheroes = (ArrayList<Superheroe>) shService.findAll();
			
			//Build Model List to Dto List
			List<SuperheroeDto> listUtensiliosDto = shBuilder.builderListToDto(listSuperheroes);
			
			// logger response
			logger.info(LoggerTemplate.responseOK());
			// return
			return new ResponseOKListDto<SuperheroeDto>(EndPointConstant.FIND_ALL, TipoMetodoConstant.POST, CodigoRespuestaConstant.OK, listUtensiliosDto);
		} catch (Exception e) {
			String messageException = e.getMessage();
			logger.error(messageException);
			List<String> mensajes = new ArrayList<>();
			mensajes.add(messageException);
			return new ResponseErrorDto(EndPointConstant.FIND_ALL, TipoMetodoConstant.POST, CodigoRespuestaConstant.ERROR, mensajes);
		}
	}

}

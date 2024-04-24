package com.mindata.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mindata.annotation.TrackExecutionTime;
import com.mindata.builder.SuperheroeBuilder;
import com.mindata.constant.CodigoRespuestaConstant;
import com.mindata.constant.ControllerConstant;
import com.mindata.constant.EndPointConstant;
import com.mindata.constant.ErrorMessagesConstant;
import com.mindata.constant.TipoMetodoConstant;
import com.mindata.dto.SuperheroeDto;
import com.mindata.log.LoggerTemplate;
import com.mindata.model.Superheroe;
import com.mindata.response.ResponseDto;
import com.mindata.response.ResponseErrorDto;
import com.mindata.response.ResponseOKDto;
import com.mindata.response.ResponseOKListDto;
import com.mindata.service.SuperheroeService;

import jakarta.validation.Valid;

@RequestMapping("/superheroe")
@RestController
public class SuperheroeController implements IABMController<SuperheroeDto>, IListController<SuperheroeDto> {

	static final Logger logger = Logger.getLogger(SuperheroeController.class);

	@Autowired
	private SuperheroeService shService;

	@Autowired
	private SuperheroeBuilder shBuilder;

	private ResponseDto save(Long id, SuperheroeDto dto, String tipoMetodoConstant) {
		// Setteo el id para la actualizacion
		dto.setId(id);
		return this.save(dto, tipoMetodoConstant);
	}

	private ResponseDto save(SuperheroeDto dto, String tipoMetodoConstant) {
		try {
			// logger controller
			logger.info(LoggerTemplate.inicialize(ControllerConstant.SUPERHEROE_CONTROLLER,
					ControllerConstant.SERVICE_SAVE));

			// logger service
			logger.info(LoggerTemplate.method(ControllerConstant.SERVICE_SAVE));

			// builder to Model
			Superheroe superheroe = shBuilder.builderToModel(dto);

			// save
			Superheroe shGuardado = shService.save(superheroe);
			
			// builder to DTO
			SuperheroeDto dtoGuardado =  shBuilder.builderToDto(shGuardado);
			
			// logger response
			logger.info(LoggerTemplate.responseOK());
			// return
			return new ResponseOKDto<SuperheroeDto>(ControllerConstant.SERVICE_SAVE, tipoMetodoConstant,
					CodigoRespuestaConstant.OK, dtoGuardado);

		} catch (Exception e) {
			String messageException = e.getMessage();
			logger.error(messageException);
			return new ResponseErrorDto(ControllerConstant.SERVICE_SAVE, tipoMetodoConstant,
					CodigoRespuestaConstant.ERROR, messageException);
		}
	}

	@TrackExecutionTime
	@Override
	public ResponseDto add(@Valid SuperheroeDto dto) {
		return this.save(dto, TipoMetodoConstant.POST);
	}

	@TrackExecutionTime
	@Override
	public ResponseDto modify(@PathVariable Long id, @Valid SuperheroeDto dto) {
		return this.save(id, dto, TipoMetodoConstant.PUT);
	}

	@TrackExecutionTime
	@Override
	public ResponseDto deleteById(@PathVariable Long id) {
		// logger controller
		logger.info(
				LoggerTemplate.inicialize(ControllerConstant.SUPERHEROE_CONTROLLER, ControllerConstant.SERVICE_DELETE));

		try {
			// logger service
			logger.info(LoggerTemplate.method(ControllerConstant.SERVICE_DELETE));

			// delete
			shService.deleteById(id);

			// logger response
			logger.info(LoggerTemplate.responseOK());

			// return
			return new ResponseOKDto<SuperheroeDto>(EndPointConstant.DELETE, TipoMetodoConstant.DELETE,
					CodigoRespuestaConstant.OK, null);
		} catch (Exception e) {
			String messageException = e.getMessage();
			logger.error(messageException);
			return new ResponseErrorDto(EndPointConstant.DELETE, TipoMetodoConstant.DELETE,
					CodigoRespuestaConstant.ERROR, messageException);
		}
	}

	@TrackExecutionTime
	@Override
	public ResponseDto findOne(@PathVariable Long id) {
		// logger controller
		logger.info(LoggerTemplate.inicialize(ControllerConstant.SUPERHEROE_CONTROLLER, EndPointConstant.FIND_BY_ID));

		try {
			// logger service
			logger.info(LoggerTemplate.method(ControllerConstant.SERVICE_FIND_BY_ID));

			// List
			Optional<Superheroe> value = shService.findById(id);

			if (value.isPresent()) {
				Superheroe sh = value.get();

				// Builder Model to Dto
				SuperheroeDto shDto = shBuilder.builderToDto(sh);

				// logger response
				logger.info(LoggerTemplate.responseOK());

				// return
				return new ResponseOKDto<SuperheroeDto>(EndPointConstant.FIND_BY_ID, TipoMetodoConstant.GET,
						CodigoRespuestaConstant.OK, shDto);
			} else {
				return new ResponseErrorDto(EndPointConstant.FIND_BY_ID, TipoMetodoConstant.GET,
						CodigoRespuestaConstant.ERROR, "");
			}
		} catch (Exception e) {
			String messageException = e.getMessage();
			logger.error(messageException);
			return new ResponseErrorDto(EndPointConstant.FIND_BY_ID, TipoMetodoConstant.GET,
					CodigoRespuestaConstant.ERROR, messageException);
		}
	}

	@TrackExecutionTime
	@Override
	public ResponseDto findByName(@RequestParam String name) {
		// logger controller
		logger.info(LoggerTemplate.inicialize(ControllerConstant.SUPERHEROE_CONTROLLER, EndPointConstant.FIND_BY_NAME));

		try {
			// logger service
			logger.info(LoggerTemplate.method(ControllerConstant.SERVICE_FIND_BY_NAME));

			// List
			Superheroe sh = new Superheroe();
			sh.setName(name);

			List<Superheroe> shs = shService.findSuperheroeByNameLike(sh.getName());

			if (!shs.isEmpty()) {
				// Builder Model to Dto
				List<SuperheroeDto> shsDto = shBuilder.builderListToDto(shs);

				// logger response
				logger.info(LoggerTemplate.responseOK());

				// return
				return new ResponseOKListDto<SuperheroeDto>(EndPointConstant.FIND_BY_NAME, TipoMetodoConstant.GET,
						CodigoRespuestaConstant.OK, shsDto);
			} else {
				return new ResponseErrorDto(EndPointConstant.FIND_BY_NAME, TipoMetodoConstant.GET,
						CodigoRespuestaConstant.ERROR, ErrorMessagesConstant.ELEMENT_NOTFOUND_MESSAGE);
			}
		} catch (Exception e) {
			String messageException = e.getMessage();
			logger.error(messageException);
			return new ResponseErrorDto(EndPointConstant.FIND_BY_NAME, TipoMetodoConstant.GET,
					CodigoRespuestaConstant.ERROR, messageException);
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

			// Build Model List to Dto List
			List<SuperheroeDto> listShDto = shBuilder.builderListToDto(listSuperheroes);

			// logger response
			logger.info(LoggerTemplate.responseOK());
			// return
			return new ResponseOKListDto<SuperheroeDto>(EndPointConstant.FIND_ALL, TipoMetodoConstant.GET,
					CodigoRespuestaConstant.OK, listShDto);
		} catch (Exception e) {
			String messageException = e.getMessage();
			logger.error(messageException);
			return new ResponseErrorDto(EndPointConstant.FIND_ALL, TipoMetodoConstant.GET,
					CodigoRespuestaConstant.ERROR, messageException);
		}
	}

}

package com.mindata.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Collections;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import com.mindata.builder.SuperheroeBuilder;
import com.mindata.dto.SuperheroeDto;
import com.mindata.model.Superheroe;
import com.mindata.response.ResponseDto;
import com.mindata.response.ResponseOKDto;
import com.mindata.service.SuperheroeService;

//@ExtendWith(SpringExtension.class)
@WebMvcTest(SuperheroeController.class)
//@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
public class SuperheroeControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private SuperheroeService shService;

	@MockBean
	private SuperheroeController shController;
	
	@MockBean
	private SuperheroeBuilder shBuilder;

	@MockBean
	private TestRestTemplate restTemplate;


	@org.junit.jupiter.api.Test
	void assertResultInFindById() throws Exception {
		SuperheroeDto dto = new SuperheroeDto();
		dto.setId(1L);
		dto.setName("HULK");
		
		ResponseDto responseDto = new ResponseOKDto<SuperheroeDto>("find_all", "get", "200", dto);
		
		when(shController.findOne(1L)).thenReturn(responseDto);

	    mockMvc.perform(get("/superheroe/1"))
        .andDo(print())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.model.id", is(1)))
	    .andExpect(jsonPath("$.model.name", is("HULK")))
	    .andExpect(jsonPath("$").isNotEmpty())
	    ;
	}

	@org.junit.jupiter.api.Test
	void assertResultDelete() throws Exception {
		SuperheroeDto dto = new SuperheroeDto();
		dto.setId(1L);
		dto.setName("HULK");
		
		ResponseDto responseDto = new ResponseOKDto<SuperheroeDto>("delete", "delete", "200", dto);
		
		when(shController.deleteById(1L)).thenReturn(responseDto);

	    mockMvc.perform(delete("/superheroe/1"))
        .andDo(print())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.model.id", is(1)))
	    .andExpect(jsonPath("$.model.name", is("HULK")))
	    .andExpect(jsonPath("$").isNotEmpty())
	    ;
	}

	@Test
	void assertResultModify() {
		String result1 = "{\"endPoint\":\"service.save\",\"tipoMetodo\":\"put\",\"codigo\":\"200\",\"model\":{\"id\":1,\"name\":\"THOR\"}}";
		String result2 = "{\"endPoint\":\"service.save\",\"tipoMetodo\":\"put\",\"codigo\":\"200\",\"model\":{\"id\":2,\"name\":\"SUPERMAN\"}}";

		String jsonToModify = "{\"id\":1,\"name\":\"THOR\"}";
		String jsonModified = "{\"id\":2,\"name\":\"SUPERMAN\"}";

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

		HttpEntity<String> entity = new HttpEntity<>(jsonToModify, headers);
		Superheroe sh = new Superheroe();
		when(shService.save(sh)).thenReturn((new Superheroe(1L, "THOR")));

		ResponseEntity<String> superHeroResponse = restTemplate.postForEntity("/superheroe/modify", entity,
				String.class);

		assertThat(superHeroResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(superHeroResponse.getBody()).isEqualTo(result1);

		HttpEntity<String> entity2 = new HttpEntity<>(jsonModified, headers);
		Superheroe sh2 = new Superheroe();
		when(shService.save(sh2)).thenReturn((new Superheroe(2L, "SUPERMAN")));

		ResponseEntity<String> superHeroResponse2 = restTemplate.postForEntity("/superheroe/modify", entity2,
				String.class);

		assertThat(superHeroResponse2.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(superHeroResponse2.getBody()).isEqualTo(result2);

	}

}

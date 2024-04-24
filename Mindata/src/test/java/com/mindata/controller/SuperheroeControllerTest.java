package com.mindata.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.mindata.model.Superheroe;
import com.mindata.service.SuperheroeService;

@ExtendWith(SpringExtension.class)
@WebMvcTest(SuperheroeController.class)
public class SuperheroeControllerTest {

	@MockBean
	private SuperheroeService shService;

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	void assertResultInFindAll() {
		String result = "{\"endPoint\":\"find_all\",\"tipoMetodo\":\"get\",\"codigo\":\"200\",\"list\":[{\"id\":1,\"name\":\"HULK\"},{\"id\":2,\"name\":\"THOR\"}]}";

		
//		when(shService.findAll()).thenReturn(Collections.singletonList(result));
//	    mockMvc.perform(get("/api/orders"))
//	        .andDo(print())
//	        .andExpect(status().isOk())
//	        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//	        .andExpect(jsonPath("$", hasSize(1)))
//	        .andExpect(jsonPath("$").isArray());
	}
		
//		List<Superheroe> shList = new ArrayList<>();
//		shList.add(new Superheroe(1L, "HULK"));
//		shList.add(new Superheroe(2L, "THOR"));

//		Class<Superheroe> clase = Superheroe.class;
//		when(shService.findAll()).thenReturn(shList);
//		ResponseEntity<Superheroe> superHeroResponse = restTemplate.getForEntity("/superheroe", clase,
//				new HashMap<>());
//
//		// assert
//		assertThat(superHeroResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
//		assertThat(superHeroResponse.getBody()).isEqualTo( clase);
//	}

	@Test
	void assertResultInFindById() {
		String result = "{\"endPoint\":\"find_by_id\",\"tipoMetodo\":\"get\",\"codigo\":\"200\",\"model\":{\"id\":1,\"name\":\"THOR\"}}";

		when(shService.findById(1L)).thenReturn(Optional.of(new Superheroe(1L, "THOR")));

		String json = "{\"id\":\"1\"}";

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

		HttpEntity<String> entity = new HttpEntity<>(json, headers);

		ResponseEntity<Superheroe> superHeroResponse = restTemplate.getForEntity("/superheroe/findById", Superheroe.class,
				String.class);

		assertThat(superHeroResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(superHeroResponse.getBody()).isEqualTo(result);
	}

	@Test
	void assertResultDelete() {
		String result = "{\"endPoint\":\"delete\",\"tipoMetodo\":\"delete\",\"codigo\":\"200\",\"model\":null}";
		String json = "{\"id\":\"1\"}";

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

		HttpEntity<String> entity = new HttpEntity<>(json, headers);

		ResponseEntity<String> superHeroResponse = restTemplate.postForEntity("/superheroe/delete", entity,
				String.class);

		assertThat(superHeroResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(superHeroResponse.getBody()).isEqualTo(result);
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

	@Test
	void assertResultFindByName() {
		String result = "{\"endPoint\":\"find_by_name\",\"tipoMetodo\":\"get\",\"codigo\":\"200\",\"list\":[{\"id\":1,\"name\":\"THOR\"}]}";
		Superheroe sh = new Superheroe(1L, "THOR");

		List<Superheroe> shList = new ArrayList<>();
		shList.add(sh);

		when(shService.findSuperheroeByNameLike("THOR")).thenReturn(shList);

		ResponseEntity<String> superHeroResponse = restTemplate.postForEntity("/superheroe/findByName", sh,
				String.class);

		assertThat(superHeroResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(superHeroResponse.getBody()).isEqualTo(result);
	}

}

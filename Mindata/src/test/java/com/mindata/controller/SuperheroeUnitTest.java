package com.mindata.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.mindata.model.Superheroe;
import com.mindata.service.SuperheroeService;

@RunWith(MockitoJUnitRunner.Silent.class)
public class SuperheroeUnitTest {

	@Mock
	private SuperheroeService shService;

	@Test
	public void testFindById() {
		Superheroe sh = new Superheroe(1L, "SUPERMAN");
		Mockito.when(shService.findById(1L)).thenReturn(Optional.of(sh));

		assertNotNull(sh);
		assertEquals("SUPERMAN", sh.getName());
		assertEquals(1L, sh.getId().longValue());
	}
	
	@Test
	public void testFindByName() {
		Superheroe sh = new Superheroe(1L, "JUAN");
		List<Superheroe> listSH = new ArrayList<Superheroe>();
		listSH.add(sh);
		
		Mockito.when(shService.findSuperheroeByNameLike("JUAN")).thenReturn(listSH);

		assertNotNull(listSH);
		assertEquals(1, listSH.size());
	}
	
}

package com.locadora.controller;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.locadora.infra.genero.Genero;
import com.locadora.infra.genero.GeneroController;
import com.locadora.infra.genero.GeneroService;

import aj.org.objectweb.asm.TypeReference;

@RunWith(SpringRunner.class)
@WebMvcTest(GeneroController.class)
public class GeneroTest {
	
	@Autowired
	MockMvc mockMvc;
	
	@InjectMocks
	GeneroController generoController;
	
	@MockBean
	GeneroService generoService;
	
	ObjectMapper mapper = new ObjectMapper();
	
	private String PATH = "/generos";
	
	@Test
	public void listarTodosTest_200_ListaPopulada() {
		private final String JSON_CONTENT = "";
		
		
		List<Genero> expectedResult = mapper.readValue( JSON_CONTENT,
				new TypeReference<List<Genero>>() { });

	}

}

package com.locadora.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.locadora.infra.genero.Genero;
import com.locadora.infra.genero.GeneroController;
import com.locadora.infra.genero.GeneroRepository;
import com.locadora.infra.genero.GeneroService;



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
	public void listarTodosTest_200_ListaPopulada() throws Exception {
		
		String JSON_CONTENT = "[{\"id\":1,\"nome\":\"COMEDIA\"},{\"id\":2,\"nome\":\"ROMANCE\"}]";
		
		List<Genero> expectedResult = mapper.readValue( JSON_CONTENT, new TypeReference<List<Genero>>() { });
		
		when(generoService.listarTodos()).thenReturn(expectedResult);

		RequestBuilder request = MockMvcRequestBuilders.get(PATH).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(request).andExpect(status().isOk()).andExpect(content().json(JSON_CONTENT)).andReturn();

	}
	
	@Test
	public void listarRodosTest_200_ListaVazia() throws Exception {
		List<Genero> expectedResult = new ArrayList();
		
		when(generoService.listarTodos()).thenReturn(expectedResult);

		RequestBuilder request = MockMvcRequestBuilders.get(PATH).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(request).andExpect(status().isOk()).andExpect(content().json("[]")).andReturn();
	}
	
	@Test
	public void buscarPorNome_200_NomeEncontrado() throws Exception {
		String JSON_CONTENT = "{\"id\":1,\"nome\":\"COMEDIA\"}";
		
		Genero expectedResult = mapper.readValue( JSON_CONTENT, new TypeReference<Genero>() { });
		
		when(generoService.buscarPorNome("COMEDIA")).thenReturn(expectedResult);

		RequestBuilder request = MockMvcRequestBuilders.get(PATH+"/COMEDIA").accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(request).andExpect(status().isOk()).andExpect(content().json(JSON_CONTENT)).andReturn();
	}
	
	@Test
	public void buscarPorNome_404_NomeNaoEncontrado() throws Exception {
		String JSON_CONTENT = "{\"id\":1,\"nome\":\"COMEDIA\"}";
		
		Genero expectedResult = mapper.readValue( JSON_CONTENT, new TypeReference<Genero>() { });
		
		when(generoService.buscarPorNome("jdjfd")).thenReturn(expectedResult);

		RequestBuilder request = MockMvcRequestBuilders.get(PATH+"/jdjfd").accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(request).andExpect(status().isNotFound()).andReturn();
	}
	
	@Test
	public void criar_201_GeneroCriado() throws Exception {
		
		String JSON_CONTENT = "{\"id\":1,\"nome\":\"COMEDIA\"}";
		
		Genero expectedResult = mapper.readValue( JSON_CONTENT, new TypeReference<Genero>() { });
		
		when(generoService.criar(new Genero("COMEDIA"))).thenReturn(expectedResult);
		RequestBuilder request = MockMvcRequestBuilders.post(PATH).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(request).andExpect(status().isCreated()).andReturn();
	}
	
	@Test
	public void criar_400_GeneroDuplicado() throws Exception {
		String JSON_CONTENT = "{\"id\":1,\"nome\":\"COMEDIA\"}";
		
		RequestBuilder request = MockMvcRequestBuilders.post(PATH).content(JSON_CONTENT).contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(request).andExpect(status().isBadRequest()).andReturn();
	}

	@Test
	public void criar_400_argumentoInvalido() throws Exception {
		
		String JSON_CONTENT = "{\"id\":1,\"nome\":\"C\"}";
		RequestBuilder request = MockMvcRequestBuilders.post(PATH).content(JSON_CONTENT)
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(request).andExpect(status().isBadRequest()).andReturn();

	}
	
	@Test
	public void atualizar_204_GeneroAtualizado() throws Exception {
		
		String JSON_CONTENT = "{\"id\":1,\"nome\":\"COMEDIA\"}";
		
		Genero genero = new Genero("COMEDIAS ROMANTICAS"); 
		
		when(generoService.atualizar("COMEDIA", genero )).thenReturn(genero);
		
		RequestBuilder request = MockMvcRequestBuilders.put(PATH+"/COMEDIA").content(JSON_CONTENT)
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(request).andExpect(status().isNoContent()).andReturn();

	}

}

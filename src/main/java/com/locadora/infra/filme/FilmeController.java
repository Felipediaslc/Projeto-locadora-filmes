package com.locadora.infra.filme;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.locadora.infra.genero.Genero;

/**
 * Controller de {@link Filme} para configuração das rotas
 * @author SOUSA, Taynar - Marco/2019
 * @since 1.0
 */
@RestController
@RequestMapping("/filmes")
public class FilmeController {

	@Autowired
	FilmeService filmeService;
	
	@GetMapping
	public ResponseEntity<List<Filme>> listarTodos(){
		List<Filme> listaDeFilme = filmeService.listarTodos();
		return ResponseEntity.status(HttpStatus.OK).body(listaDeFilme);
	}
	
	@GetMapping("/{titulo}")
	public ResponseEntity<Filme> buscarPorTitulo(@PathVariable("titulo") String titulo){
		Filme filmeEncontrado = filmeService.buscarPorTitulo(titulo);
		
		return ResponseEntity.status(HttpStatus.OK).body(filmeEncontrado);
		
	}
	
	/*@GetMapping("/genero/{genero}")
	public ResponseEntity<List<Filme>> buscarPorGenero(@PathVariable("genero") String genero){
		List<Filme> filmeEncontrado = filmeService.listarFilmePorGenero(genero);
		
		return ResponseEntity.status(HttpStatus.OK).body(filmeEncontrado);
	}*/
	
	@PostMapping
	public ResponseEntity<Filme> criar(@Valid @RequestBody Filme filme){
		Filme filmeCriado = filmeService.criar(filme);
		return ResponseEntity.status(HttpStatus.CREATED).body(filmeCriado);
	}
}

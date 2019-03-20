package com.locadora.infra.filme;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



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
	
	/**
	 * Metodo que  retorna no body todos os {@link Filme filmes} cadastrados
	 * @see FilmeService
	 * @return {@link HttpStatus.OK} com  a lista de {@link Filme filmes} no body
	 */
	@GetMapping
	public ResponseEntity<List<Filme>> listarTodos(){
		List<Filme> listaDeFilme = filmeService.listarTodos();
		return ResponseEntity.status(HttpStatus.OK).body(listaDeFilme);
	}
	/**
	 * Metodo que retorna no body um {@link Filme} atraves da busca feita a partir do titulo
	 * @see FilmeService
	 * @param titulo
	 * @return {@link HttpStatus.OK} com o {@link Filme} no body
	 */
	@GetMapping("/{titulo}")
	public ResponseEntity<Filme> buscarPorTitulo(@PathVariable("titulo") String titulo){
		Filme filmeEncontrado = filmeService.buscarPorTitulo(titulo);
		
		return ResponseEntity.status(HttpStatus.OK).body(filmeEncontrado);
		
	}
	/**
	 * Metodo que retorna uma lista de {@link Filme filmes} a partir da busca pelo {@link Genero}
	 * @see FilmeService
	 * @param genero
	 * @return {@link HttpStatus.OK} com a lista de {@link Filme filmes} daquele {@link Genero} no body
	 */
	@GetMapping("/genero/{genero}")
	public ResponseEntity<List<Filme>> buscarPorGenero(@PathVariable("genero") String genero){
		List<Filme> filmeEncontrado = filmeService.listarFilmePorGenero(genero);
		
		return ResponseEntity.status(HttpStatus.OK).body(filmeEncontrado);
	}
	
	/**
	 * Metodo que cadastra um {@link filme}
	 * @see FilmeRepository
	 * @param filme
	 * @return {@link HttpStatus.CREATED} com o {@link Filme} criado no body
	 */
	@PostMapping
	public ResponseEntity<Filme> criar(@Valid @RequestBody Filme filme){
		Filme filmeCriado = filmeService.criar(filme);
		return ResponseEntity.status(HttpStatus.CREATED).body(filmeCriado);
	}
	
	/**
	 * Metodo que atualiza todos os atributos de {@link Filme}
	 * @see FilmeRepository
	 * @param titulo
	 * @param filme
	 * @return {@link HttpStatus.NO_CONTENT} 
	 */
	@PutMapping
	public ResponseEntity<Filme> atualizar(@PathVariable String titulo, @Valid @RequestBody Filme filme){
		Filme filmeAtualizado = filmeService.atualizar(titulo, filme);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body(filmeAtualizado);
	}
	
	/**
	 * Metodo que atualiza a quantidade contida no estoque de {@link Filme}
	 * @see FilmeRepository
	 * @param titulo
	 * @param qtEstoque
	 * @return {@link HttpStatus.NO_CONTENT} 
	 */
	@PutMapping("/qtEstoque/{titulo}")
	public ResponseEntity<Filme> atualizarQtEstoque(@PathVariable String titulo, @Valid @RequestBody int qtEstoque){
		Filme filmeAtualizado = filmeService.atualizarQtEstoque(titulo, qtEstoque);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body(filmeAtualizado);
	}
	
	/**
	 * Metodo responsavel por atualizar o valor da diaria da locaçao de um {@link Filme}
	 * @see FilmeRepository
	 * @param titulo
	 * @param valorDiaria
	 * @return {@link HttpStatus.NO_CONTENT} 
	 */
	@PutMapping("/valorDiaria/{titulo}")
	public ResponseEntity<Filme> atualizarValorDiaria(@PathVariable String titulo, @Valid @RequestBody double valorDiaria){
		Filme filmeAtualizado = filmeService.atualizarValorDiaria(titulo, valorDiaria);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body(filmeAtualizado);
	}
}

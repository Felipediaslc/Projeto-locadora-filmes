package com.locadora.infra.genero;

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
 * Classe responsavel por configurar as rotas para {@link Genero}
 * @author SOUSA, Taynar
 * @since 1.0
 */
@RestController
@RequestMapping("/generos")
public class GeneroController {
	
	@Autowired
	private GeneroService generoService;
	
	/**
	 * Metodo que lista todos os {@link Genero generos} cadastrados no banco de dados
	 * @see ResponseEntity
	 * @return 
	 */
	@GetMapping
	public ResponseEntity<List<Genero>> listarTodos(){
		List<Genero> generos = generoService.listarTodos();
		return ResponseEntity.status(HttpStatus.OK).body(generos);
	}
	
	@GetMapping("/{nome}")
	public ResponseEntity<Genero> listarPorNome(@PathVariable("nome") String nome){
		Genero genero = generoService.buscarPorNome(nome);
		return ResponseEntity.status(HttpStatus.OK).body(genero);
	}
	
	/**
	 * Metodo responsavel por criar um {@link Genero} e salvar no banco
	 * @param genero
	 * @return
	 */
	@PostMapping
	public ResponseEntity<Genero> criar(@Valid @RequestBody Genero genero){
		Genero generoSalvo = generoService.criar(genero);
		return ResponseEntity.status(HttpStatus.CREATED).body(generoSalvo);
	}
	
	/**
	 * Metodo responsavel por atualizar um {@link Genero}
	 * @param nome
	 * @param genero
	 * @return
	 */
	@PutMapping("/{nome}")
	public ResponseEntity<Genero> criar(@PathVariable("nome") String nome,@Valid @RequestBody Genero genero){
		Genero generoSalvo = generoService.atualizar(nome,genero);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body(generoSalvo);
	}
	
}

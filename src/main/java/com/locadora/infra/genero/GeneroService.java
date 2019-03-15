package com.locadora.infra.genero;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * Classe responsavel pela regra de negocios atribuidos a {@link Genero}
 * @author SOUSA, Taynar
 * @since 1.0
 */
@Service
public class GeneroService {
	
	@Autowired
	private GeneroRepository generoRepository;
	
	/**
	 * Metodo que lista todos os {@link Genero generos}
	 * 
	 * @return
	 */
	public List<Genero> listarTodos(){
		return generoRepository.findAll();
	}
	
	public Genero listarPorNome(String nome) {
		Optional<Genero> generoASerBuscado = generoRepository.findByNome(nome);
		return generoASerBuscado.get();
	}
	
	/**
	 * Metodo que cria um genero e o salva no banco
	 * @see GeneroRepository
	 * @param genero
	 * @return
	 */
	public Genero criar(Genero genero) {
		Genero generoSalvo = generoRepository.save(genero);
		return generoSalvo;
	}
	

}

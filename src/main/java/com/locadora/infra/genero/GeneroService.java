package com.locadora.infra.genero;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.locadora.infra.genero.exception.GeneroDuplicadoException;
import com.locadora.infra.genero.exception.GeneroNaoEncontradoException;
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
	
	public Genero buscarPorNome(String nome) {
		Optional<Genero> generoASerBuscado = generoRepository.findByNome(nome);
		if(!generoASerBuscado.isPresent()) {
			throw new GeneroNaoEncontradoException();
		}
		return generoASerBuscado.get();
	}
	
	/**
	 * Metodo que cria um genero e o salva no banco
	 * @see GeneroRepository
	 * @param genero
	 * @return
	 */
	public Genero criar(Genero genero) {
		Optional<Genero> generoSalvo = generoRepository.findByNome(genero.getNome());
		if(generoSalvo.isPresent()) {
			throw new GeneroDuplicadoException();
		}
		generoRepository.save(generoSalvo.get());
		return generoSalvo.get();
	}
	
	/**
	 * Metodo que atualiza um {@link Genero}
	 * @param nomeantigo
	 * @param genero
	 * @return Genero atualizado
	 */
	public Genero atualizar(String nomeantigo, Genero genero) {
		
		Optional<Genero> generoAAtualizar = generoRepository.findByNome(genero.getNome());
		if(generoAAtualizar.isPresent()) {
			throw new GeneroDuplicadoException();
		}
		
		Genero generoCadastrado = buscarPorNome(nomeantigo);
		BeanUtils.copyProperties(genero, generoCadastrado, "id");
		
		return generoRepository.save(generoCadastrado);
		
	}
	
}

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
 * 
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
	 * @see GeneroRepository
	 * @return Lista de {@link Genero generos}.
	 * @since 1.0
	 */
	public List<Genero> listarTodos() {
		return generoRepository.findAll();
	}

	/**
	 * Metodo responsavel por listar um {@link Genero} a partir da pesquisa feita
	 * pelo seu nomw.
	 * 
	 * @see generoRepository
	 * @param nome
	 * @return {@link Genero} que possui o nome informado na pesquisa
	 * @since 1.0
	 */
	public Genero buscarPorNome(String nome) {
		Optional<Genero> generoSalvo = generoRepository.findByNome(nome);
		if(!generoSalvo.isPresent()) {
			throw new GeneroNaoEncontradoException();
		}
		return generoSalvo.get();
	}

	/**
	 * Metodo responsavel por buscar um {@link Genero} a partir do seu id.
	 * 
	 * @see GeneroRepository
	 * @param id
	 * @return {@link Genero} com o id informado.
	 * @since 1.0
	 */
	public Genero buscarPorId(Integer id) {
		Optional<Genero> generoSalvo = this.generoRepository.findById(id);
		if (!generoSalvo.isPresent()) {
			throw new GeneroNaoEncontradoException();
		}
		return generoSalvo.get();
	}

	/**
	 * Metodo que cria um genero e o salva no banco
	 * 
	 * @see GeneroRepository
	 * @param genero
	 * @return {@link Genero} criado
	 */
	public Genero criar(Genero genero) {
		if (generoExiste(genero)) {
			throw new GeneroDuplicadoException();
		}
		final Genero generoSalvo = generoRepository.save(genero);
		return generoSalvo;
	}

	/**
	 * Metodo que atualiza um {@link Genero}
	 * 
	 * @see GeneroRepository
	 * @param nomeAntigo
	 * @param genero
	 * @return Genero atualizado
	 * @since 1.0
	 */
	public Genero atualizar(Integer id, Genero genero) {
		Genero generoCadastrado = buscarPorId(id);
		if (generoExiste(genero)) {
			throw new GeneroDuplicadoException();
		}
		BeanUtils.copyProperties(genero, generoCadastrado, "id");
		return generoRepository.save(generoCadastrado);

	}

	/**
	 * Metodo responsavel por verificar se {@link Genero} ja existe no banco de
	 * dados.
	 * 
	 * @param nome
	 * @return
	 */
	public boolean generoExiste(Genero genero ) {
		Optional<Genero> generoSalvo = generoRepository.findByNome(genero.getNome());
		return generoSalvo.isPresent();
	}

}

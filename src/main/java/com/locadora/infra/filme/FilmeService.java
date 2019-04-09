package com.locadora.infra.filme;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.locadora.infra.filme.exception.FilmeDuplicadoException;
import com.locadora.infra.filme.exception.FilmeNaoEncontradoException;
import com.locadora.infra.filme.exception.FilmeSemEstoqueException;
import com.locadora.infra.genero.Genero;
import com.locadora.infra.genero.GeneroService;

/**
 * Classe responsavel pela regra de negocios de {@link Filme}
 * 
 * @author SOUSA, Taynar - Marco/2019
 * @since 1.0
 */
@Service
public class FilmeService {

	@Autowired
	private FilmeRepository filmeRepository;

	@Autowired
	private GeneroService generoService;

	/**
	 * Metodo responsavel por listar todos os {@link Filme filmes} cadastrados
	 * 
	 * @see FilmeRepository
	 * @return todos os {@link Filme filmes} cadastrados no banco
	 * @since 1.0
	 */
	public List<Filme> listarTodos() {
		return this.filmeRepository.findAll();
	}

	/**
	 * Metodo responsavel por listar {@link Filme filmes} a partir do
	 * {@link Genero}. como entrada recebe o nome de um {@link Genero} e esse genero
	 * sera pesquisado e em seguida será buscado no repository de filme todos os
	 * filmes que tem esse {@link Genero}
	 * 
	 * @param genero
	 * @see FilmeRepository
	 * @see GeneroService
	 * @return lista de {@link Filme filmes} de um determinado {@link Genero}.
	 * @since 1.0
	 */
	public List<Filme> listarPorGenero(String genero) {
		final Genero generoFilmes = this.generoService.buscarPorNome(genero);
		final List<Filme> listaFilmes = this.filmeRepository.findFilmeByGenero(generoFilmes);

		return listaFilmes;
	}

	/**
	 * Metodo responsavel por buscar um {@link Filme} a partir do seu titulo.
	 * 
	 * @param titulo
	 * @see FilmeRepository
	 * @return {@link Filme} que possui o titulo no qual foi buscado.
	 * @since 1.0
	 */
	public Filme buscarPorTitulo(String titulo) {
		Optional<Filme> filmeSalvo = this.filmeRepository.findByTitulo(titulo);
		if (!filmeSalvo.isPresent()) {
			throw new FilmeNaoEncontradoException();
		}

		return filmeSalvo.get();
	}

	public Filme buscarPorId(Integer id) {
		Optional<Filme> filmeSalvo = this.filmeRepository.findById(id);
		if (!filmeSalvo.isPresent()) {
			throw new FilmeNaoEncontradoException();
		}
		return filmeSalvo.get();
	}

	/**
	 * Metodo responsavel por criar um {@link Filme} e adiciona-lo ao banco de
	 * dados.
	 * 
	 * @param filme
	 * @see FilmeRepository
	 * @return {@link Filme} criado.
	 * @since 1.0
	 */
	public Filme criar(Filme filme) {

		if (filmeExiste(filme.getTitulo())) {
			throw new FilmeDuplicadoException();
		}
		final Filme filmeSalvo = this.filmeRepository.save(filme);

		return filmeSalvo;
	}

	/**
	 * Metodo responsavel por atualizar os dados de filme
	 * 
	 * @see FilmeRepository
	 * @param titulo
	 * @param filme
	 * @return {@link Filme} atualizado.
	 * @since 1.0
	 */
	public Filme atualizar(String titulo, Filme filme) {
		final Filme filmeSalvo = this.buscarPorTitulo(titulo);
		BeanUtils.copyProperties(filme, filmeSalvo, "id");
		if (this.filmeExiste(filme.getTitulo())) {
			throw new FilmeDuplicadoException();
		}

		return this.filmeRepository.save(filmeSalvo);
	}

	/**
	 * Metodo responsavel por atualizar a quantidade de {link Filme filmes} no
	 * estoque.
	 * 
	 * @see FilmeRepository
	 * @param id
	 * @param qtEstoque
	 * @return {@link Filme} com estoque atualizado.
	 */
	public Filme atualizarEstoque(Integer id, Integer qtEstoque) {
		Filme filmeSalvo = buscarPorId(id);
		filmeSalvo.setQuantidadeEstoque(qtEstoque);
		return filmeRepository.save(filmeSalvo);
	}

	/**
	 * Metodo responsavel por atualizar o valor da diaria de determinado
	 * {@link Filme}
	 * 
	 * @see FilmeRepository
	 * @param id
	 * @param valor
	 * @return {@link Filme} com o valor da diaria atualizado
	 * @since 1.0
	 */
	public Filme atualizarValorDiaria(Integer id, double valor) {
		Filme filmeSalvo = this.buscarPorId(id);
		filmeSalvo.setValorDiaria(valor);
		return this.filmeRepository.save(filmeSalvo);
	}

	/**
	 * Metodo responsavel por atualizar um {@link Genero} de um {@link Filme}
	 * 
	 * @see FilmeRepository
	 * @param id
	 * @param genero
	 * @return {@link Filme} com o {@link Genero} atualizado
	 */
	public Filme atualizarGenero(Integer id, Genero genero) {
		Filme filmeSalvo = this.buscarPorId(id);
		if (this.generoService.generoExiste(genero)) {
			filmeSalvo.setGenero(genero);
		}
		return filmeRepository.save(filmeSalvo);
	}

	/**
	 * Metodo responsavel por informar se o {@link Filme} existe a partir do titulo.
	 * 
	 * @param titulo
	 * @see FilmeRepository
	 * @return false se {@link Filme} nao existir; true se {@link Filme} existe
	 */
	private boolean filmeExiste(String titulo) {
		Optional<Filme> filmeExistente = this.filmeRepository.findByTitulo(titulo);

		return filmeExistente.isPresent();
	}
	
	/**
	 * Metodo responsavel por verificar se a quantidade de {@link Filme filmes} no
	 * estoque é suficiente para a quantidade que se deseja locar.
	 * 
	 * @see FilmeRepository
	 * @param filme
	 * @param quantidadeLocada
	 * @throws FilmeSemEstoqueException
	 * @return true se tiver estoque suficiente
	 * @since 1.0
	 */
	public boolean filmeTemEstoque(Filme filme, int quantidadeLocada) {
		if (filme.getQuantidadeEstoque() < quantidadeLocada) {
			throw new FilmeSemEstoqueException();
		}
		return true;
	}

}

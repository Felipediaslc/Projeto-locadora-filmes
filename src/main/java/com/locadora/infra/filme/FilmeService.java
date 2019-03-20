package com.locadora.infra.filme;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.locadora.infra.filme.exception.FilmeDuplicadoException;
import com.locadora.infra.filme.exception.FilmeNaoEncontradoException;
import com.locadora.infra.genero.Genero;
import com.locadora.infra.genero.GeneroService;
/**
 * Classe responsavel pela regra de negocios de {@link Filme}
 * @author SOUSA, Taynar - Marco/2019
 * @since 1.0
 */
@Service
public class FilmeService {
	
	@Autowired
	FilmeRepository filmeRepository;
	
	@Autowired
	GeneroService generoService;
	/**
	 * Metodo responsavel por listar todos os {@link Filme filmes} cadastrados
	 * @see FilmeRepository
	 * @return todos os {@link Filme filmes} cadastrados no banco
	 */
	public List<Filme> listarTodos(){
		return filmeRepository.findAll();
	}
	
	/**
	 * Metodo responsavel por listar {@link Filme filmes} a partir do {@link Genero}.
	 * como entrada recebe o nome de um {@link Genero} e esse genero sera pesquisado e
	 * em seguida será buscado no repository de filme todos os filmes que tem esse {@link Genero}
	 * @param genero
	 * @see FilmeRepository
	 * @see GeneroService
	 * @return lista de {@link Filme filmes} de um determinado {@link Genero}.
	 */
	public List<Filme> listarFilmePorGenero(String genero){
		Genero generoFilmes = generoService.buscarPorNome(genero);
		List<Filme> listaDeFilmes = filmeRepository.findFilmeByGenero(generoFilmes);
		
		return listaDeFilmes;
	}
	
	/**
	 * Metodo responsavel por buscar um {@link Filme} a partir do seu titulo.
	 * @param titulo
	 * @see FilmeRepository
	 * @return {@link Filme} que possui o titulo no qual foi buscado.
	 */
	public Filme buscarPorTitulo(String titulo) {
		Optional<Filme> filmeExistente =  filmeRepository.findByTitulo(titulo);
		if(!filmeExistente.isPresent()) {
			throw new FilmeNaoEncontradoException();
			}
		
		return filmeExistente.get();
	}
	
	/**
	 * Metodo responsavel por criar um {@link Filme} e adiciona-lo ao banco de dados.
	 * @param filme
	 * @see FilmeRepository
	 * @return {@link Filme} criado.
	 */
	public Filme criar(Filme filme) {
		if(filmeExiste(filme.getTitulo())) {
			throw new FilmeDuplicadoException();
		}
		Filme filmeSalvo = filmeRepository.save(filme);
		
		return filmeSalvo;
	}
	
	public Filme atualizar(String titulo,Filme filme) {
		Filme filmeSalvo = buscarPorTitulo(titulo);
		BeanUtils.copyProperties(filme, filmeSalvo, "id");
		if(filmeExiste(filme.getTitulo())) {
			throw new FilmeDuplicadoException();
		}
		
		return filmeRepository.save(filmeSalvo);
	}
	
	public Filme atualizarQtEstoque(String titulo,int qtEstoque) {
		Filme filmeSalvo = buscarPorTitulo(titulo);
		filmeSalvo.setQtEstoque(qtEstoque);
		return filmeRepository.save(filmeSalvo);
	}
	
	
	public Filme atualizarValorDiaria(String titulo, double valor) {
		Filme filmeSalvo = buscarPorTitulo(titulo);
		filmeSalvo.setValorDiaria(valor);
		return filmeRepository.save(filmeSalvo);
	}
	
	public Filme atualizarGenero(String titulo, Genero genero) {
		Filme filmeSalvo = buscarPorTitulo(titulo);
		filmeSalvo.setGenero(genero);
		return filmeRepository.save(filmeSalvo);
	}
	
	/**
	 * Metodo responsavel por informar se o {@link Filme} existe a partir do titulo.
	 * @param titulo
	 * @see FilmeRepository
	 * @return false se {@link Filme} nao existir; true se {@link Filme} existe
	 */
	private boolean filmeExiste(String titulo) {
		Optional<Filme> filmeExistente =  filmeRepository.findByTitulo(titulo);
		
		return filmeExistente.isPresent();
	}
	
	
}

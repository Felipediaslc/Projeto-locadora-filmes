package com.locadora.infra.locacaoTemFilme;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.locadora.infra.filme.Filme;
import com.locadora.infra.filme.FilmeRepository;
import com.locadora.infra.filme.FilmeService;
import com.locadora.infra.filme.exception.FilmeSemEstoqueException;
import com.locadora.infra.locacao.Locacao;

@Service
public class LocacaoTemFilmeService {

	@Autowired
	private FilmeService filmeService;

	public List<LocacaoTemFilme> criar(Locacao locacao, List<LocacaoTemFilme> filmes) {
		LocacaoTemFilme locacaoTemFilme;
		List<LocacaoTemFilme> listaAtualizada = new ArrayList<>();
		for (int i = 0; i < filmes.size(); i++) {
			filmes.get(i).setLocacao(locacao);
			Filme filme = this.filmeService.buscarPorId(filmes.get(i).getFilme().getId());
			if (filmeTemEstoque(filme, filmes.get(i).getQtLocada())) {
				locacaoTemFilme = new LocacaoTemFilme(locacao, filme, filmes.get(i).getQtLocada());
				Double valor = calcularValorTotalDaDiaria(filme.getValorDiaria(), filmes.get(i).getQtLocada());
				locacaoTemFilme.setVlrTotal(valor);
				listaAtualizada.add(locacaoTemFilme);
				subtraiQtEstoque(filme, filmes.get(i).getQtLocada());
			}
		}
		return listaAtualizada;

	}

	private void subtraiQtEstoque(Filme filme, int qtLocada) {
		int quantidadeEstoqueAtualizado = filme.getQuantidadeEstoque() - qtLocada;
		this.filmeService.atualizarEstoque(filme.getId(), quantidadeEstoqueAtualizado);
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

	private Double calcularValorTotalDaDiaria(Double valorFilme, int qtlocada) {

		Double vlrtotal = valorFilme * qtlocada;

		return vlrtotal;
	}
}

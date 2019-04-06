package com.locadora.infra.locacaoTemFilme;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.locadora.infra.filme.Filme;
import com.locadora.infra.filme.FilmeService;
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
			if (this.filmeService.filmeTemEstoque(filme, filmes.get(i).getQuantidadeLocada())) {
				locacaoTemFilme = new LocacaoTemFilme(locacao, filme, filmes.get(i).getQuantidadeLocada());
				Double valor = calcularValorTotalDaDiaria(filme.getValorDiaria(), filmes.get(i).getQuantidadeLocada());
				locacaoTemFilme.setVlrTotalDiaria(valor);
				listaAtualizada.add(locacaoTemFilme);
				subtraiQuantidadeEstoque(filme, filmes.get(i).getQuantidadeLocada());
			}
		}
		return listaAtualizada;

	}
	
	public void somaQuantidadeEstoque(Filme filme, Integer quantidadeLocada) {
		Integer quantidadeEstoqueAtualizado = filme.getQuantidadeEstoque() + quantidadeLocada;
		this.filmeService.atualizarEstoque(filme.getId(), quantidadeEstoqueAtualizado);
		
	}
	private void subtraiQuantidadeEstoque(Filme filme, Integer quantidadeLocada) {
		
		int quantidadeEstoqueAtualizado = filme.getQuantidadeEstoque() - quantidadeLocada;
		this.filmeService.atualizarEstoque(filme.getId(), quantidadeEstoqueAtualizado);
		
	}


	private Double calcularValorTotalDaDiaria(Double valorFilme, int qtlocada) {

		Double vlrtotal = valorFilme * qtlocada;

		return vlrtotal;
	}
}

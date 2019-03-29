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
		for (int i =0;i<filmes.size();i++) {
			if(i>5) {
				System.out.println("nao pode alugar mais que 5");
			}
			
			filmes.get(i).setLocacao(locacao);
			Filme filme = this.filmeService.buscarPorId(filmes.get(i).getFilme().getId());
			locacaoTemFilme = new LocacaoTemFilme(locacao, filme, filmes.get(i).getQtLocada());
			 Double valor = calcularValorTotalDaDiaria(filme.getValorDiaria(), filmes.get(i).getQtLocada());
			locacaoTemFilme.setVlrTotal(valor);
			listaAtualizada.add(locacaoTemFilme);
		}
		return listaAtualizada;
		 
	}


	private Double calcularValorTotalDaDiaria(Double valorFilme, int qtlocada) {
		
		Double vlrtotal = valorFilme*qtlocada;
		
		return vlrtotal;
	}
}

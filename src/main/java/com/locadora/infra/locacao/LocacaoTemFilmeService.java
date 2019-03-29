package com.locadora.infra.locacao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.locadora.infra.filme.Filme;
import com.locadora.infra.filme.FilmeService;

@Service
public class LocacaoTemFilmeService {

	@Autowired
	private LocacaoTemFilmeRepository locacaoTemFilmeRepository;
	
	@Autowired
	private FilmeService filmeService;
		
	public void criar(Locacao locacao, List<LocacaoTemFilme> filmes) {
		
		for (int i =0;i<filmes.size();i++) {
			if(i>5) {
				System.out.println("nao pode alugar mais que 5");
			}
			filmes.get(i).setLocacao(locacao);
			Filme filme = this.filmeService.buscarPorId(filmes.get(i).getFilme().getId());
			 Double valor = calcularValorTotalDaDiaria(filme.getValorDiaria(), filmes.get(i).getQtLocada());
			 filmes.get(i).setVlrTotal(valor);
			 this.locacaoTemFilmeRepository.save(filmes.get(i));
		}
		
		 
	}


	private Double calcularValorTotalDaDiaria(Double valorFilme, int qtlocada) {
		
		Double vlrtotal = valorFilme*qtlocada;
		
		return vlrtotal;
	}
}

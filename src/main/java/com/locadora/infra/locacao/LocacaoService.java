package com.locadora.infra.locacao;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.locadora.infra.cliente.Cliente;
import com.locadora.infra.cliente.ClienteService;
import com.locadora.infra.enums.StatusLocacao;

/**
 * Classe responsável pelas regras de negocios atribuidos a {@link Locacao}
 * @author SOUSA, Taynar - Marco/2019
 * @since 1.0
 */
@Service
public class LocacaoService {

	@Autowired
	private LocacaoRepository locacaoRepository;
	
	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private LocacaoTemFilmeService locacaoTemFilmeService;
	
	public List<Locacao> listarTodos(){
		return this.locacaoRepository.findAll();
	}
	
	public Locacao buscarPorCliente(String cpf) {
		Cliente cliente = this.clienteService.buscarPorCpf(cpf);
		
		Locacao locacaoSalva = this.locacaoRepository.findByCliente(cliente);
		
		return locacaoSalva;
	}
	
	/*public List<Locacao> listarPorStatus(StatusLocacao status){
		List<Locacao> listaLocacoes = this.locacaoRepository.findByStatus(status);
		return listaLocacoes;
	}*/
	
	public Locacao criar(Locacao locacao) {
		List<LocacaoTemFilme> locacaoTemFilmes = locacao.getFilmes();
		this.setInformacoesVazias(locacao);
		Locacao locacaoSalva = this.locacaoRepository.save(locacao);
		this.locacaoTemFilmeService.criar(locacaoSalva, locacaoTemFilmes);
		this.setInformacoesPreenchidas(locacaoSalva);
		this.locacaoRepository.save(locacaoSalva);
		return locacaoSalva;
		
	}
	
	
	
	private void setInformacoesVazias(Locacao locacao) {
		locacao.setDataRealizacao(LocalDate.now());
		locacao.setDataDevolucao(null);
		locacao.setValorTotal(null);
		locacao.setFilmes(null);
	}
	
	private void setInformacoesPreenchidas(Locacao locacao) {
		locacao.setDataRealizacao(LocalDate.now());
		locacao.setDataDevolucao(totDias(LocalDate.now()));
		locacao.setValorTotal(calcValorTotal(locacao));
		
	}
	private LocalDate totDias(LocalDate dataInicio) {
		LocalDate novaData = dataInicio.plus(5, ChronoUnit.DAYS);
		return novaData;
	}

	private Double calcValorTotal(Locacao locacao) {
		Double valorTotal = 0.0;
		for(int i=0; i<locacao.getFilmes().size();i++) {
			valorTotal+= locacao.getFilmes().get(i).getVlrTotal();
		}
		
		return valorTotal*5;
	}



}

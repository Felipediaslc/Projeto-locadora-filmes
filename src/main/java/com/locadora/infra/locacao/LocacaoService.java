package com.locadora.infra.locacao;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.locadora.infra.cliente.Cliente;
import com.locadora.infra.cliente.ClienteService;
import com.locadora.infra.enums.Status;

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
	
	public List<Locacao> listarTodos(){
		return this.locacaoRepository.findAll();
	}
	
	public Locacao buscarPorCliente(String cpf) {
		Cliente cliente = this.clienteService.buscarPorCpf(cpf);
		
		Locacao locacaoSalva = this.locacaoRepository.findByCliente(cliente);
		
		return locacaoSalva;
	}
	
	public List<Locacao> buscarPosStatus(Status status){
		List<Locacao> listaLocacoes = this.locacaoRepository.findByStatus(status);
		return listaLocacoes;
	}
	
	
	
	public Long totDias(LocalDate dataInicio, LocalDate dataFim) {
		Long totDias = 	ChronoUnit.DAYS.between(dataInicio, dataFim);
		
		return totDias;
	}

	public Double calcValorTotal(Long totDias, Double valorDiaria) {
		return (totDias * valorDiaria);
	}



}

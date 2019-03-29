package com.locadora.infra.locacao;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
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
	/**
	 * Metodo responsavel por buscar locacoes de um determinado {@link Cliente}.
	 * @see LocacaoRepository
	 * @param cpf
	 * @return Lista de {@link Locacao locacoes} do {@link Cliente} informado.
	 */
	public List<Locacao> buscarPorCliente(String cpf) {
		Cliente cliente = this.clienteService.buscarPorCpf(cpf);
		
		List<Locacao> locacaoSalva = this.locacaoRepository.findByCliente(cliente);
		
		return locacaoSalva;
	}
	/**
	 * Metodo responsavel por buscar {@link Locacao} através do id informado.
	 * @see LocacaoRepository
	 * @param id
	 * @return {@link Locacao} que possui o id informado.
	 */
	public Locacao buscarPorId(int id) {
		Optional<Locacao> locacao = this.locacaoRepository.findById(id);
		
		return locacao.get();
	}
	/**
	 * Metodo responsavel por listar as {@link Locacao locacoes} pelo status.
	 * @see LocacaoRepository
	 * @param status
	 * @return Lista de locacoes que possuem o status informado.
	 */
	public List<Locacao> listarPorStatus(StatusLocacao status){
		List<Locacao> listaLocacoes = this.locacaoRepository.findByStatusLocacao(status);
		return listaLocacoes;
	}
	
	public Locacao criar(Locacao locacao) {
		List<LocacaoTemFilme> locacaoTemFilmes = locacao.getFilmes();
		this.setInformacoesVazias(locacao);
		Locacao locacaoSalva = this.locacaoRepository.save(locacao);
		locacaoTemFilmes = this.locacaoTemFilmeService.criar(locacaoSalva, locacaoTemFilmes);
		locacaoSalva.setFilmes(locacaoTemFilmes);
		this.setInformacoesPreenchidas(locacaoSalva);
		locacaoSalva = this.atualizar(locacaoSalva.getId(), locacaoSalva);
		return locacaoSalva;
		
	}
	
	public Locacao atualizar(int id, Locacao locacao) {
		Locacao locacaoSalva = this.buscarPorId(id);
		BeanUtils.copyProperties(locacao, locacaoSalva, "id");
		return this.locacaoRepository.save(locacaoSalva);
	}
	
	
	private void setInformacoesVazias(Locacao locacao) {
		locacao.setDataRealizacao(LocalDate.now());
		locacao.setDataDevolucao(null);
		locacao.setValorTotal(null);
		locacao.setFilmes(null);
		locacao.setStatusLocacao(StatusLocacao.ABERTO);
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

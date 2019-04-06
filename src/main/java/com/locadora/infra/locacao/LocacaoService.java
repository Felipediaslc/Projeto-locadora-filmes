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
import com.locadora.infra.locacao.exceptions.LocacaoNaoEncontradaException;
import com.locadora.infra.locacaoTemFilme.LocacaoTemFilme;
import com.locadora.infra.locacaoTemFilme.LocacaoTemFilmeService;

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
	public List<Locacao> listarPorCliente(String cpf) {
		Cliente cliente = this.clienteService.buscarPorCpf(cpf);
		
		List<Locacao> locacaoSalva = this.locacaoRepository.findByCliente(cliente);
		
		return locacaoSalva;
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
	
	public List<LocacaoTemFilme> listarFilmes(Integer idLocacao){
		Locacao locacaoSalva = this.buscarPorId(idLocacao);
		List<LocacaoTemFilme> listaFilmes = locacaoSalva.getFilmes();
		return listaFilmes;
	}
	
	/**
	 * Metodo responsavel por buscar {@link Locacao} através do id informado.
	 * @see LocacaoRepository
	 * @param id
	 * @return {@link Locacao} que possui o id informado.
	 */
	public Locacao buscarPorId(int id) {
		Optional<Locacao> locacao = this.locacaoRepository.findById(id);
		if(!locacao.isPresent()) {
			throw new LocacaoNaoEncontradaException();
		}
		
		return locacao.get();
	}
	
	/**
	 * Metodo responsavel por criar uma locacao e salvar no banco de dados
	 * @see LocacaoRepository
	 * @see LocacaoTemFilmeService
	 * @param locacao
	 * @return {@link Locacao} criada.
	 */
	public Locacao criar(Locacao locacao) {
		List<LocacaoTemFilme> locacaoTemFilmes = locacao.getFilmes();
		this.setInformacoesVazias(locacao);
		Locacao locacaoSalva = this.locacaoRepository.save(locacao);
		locacaoTemFilmes = this.locacaoTemFilmeService.criar(locacaoSalva, locacaoTemFilmes);
		locacaoSalva.setFilmes(locacaoTemFilmes);
		locacaoSalva = this.atualizarCriacao(locacaoSalva.getId(), locacaoSalva);
		return locacaoSalva;
		
	}
	
	/**
	 * Metodo responsavel por atualizar a {@link Locacao} adicionando a lista de {@link LocacaoTemFilme}.
	 * Esse metodo e utilizado apenas pelo metodo criar.
	 * @see LocacaoRepository
	 * @param id
	 * @param locacao
	 * @return {@link Locacao} atualizada
	 */
	public Locacao atualizarCriacao(int id, Locacao locacao) {
		Locacao locacaoSalva = this.buscarPorId(id);
		BeanUtils.copyProperties(locacao, locacaoSalva, "id");
		return this.locacaoRepository.save(locacaoSalva);
	}
	
	public Locacao devolver(int id) {
		Locacao locacaoSalva = this.buscarPorId(id);
		Locacao locacao = locacaoSalva;
		this.setInformacoesDevolucao(locacao);
		locacao.setValorTotal(valorFinal(locacao.getDataRealizacao(),locacao.getDataDevolucao(),locacao.getFilmes()));
		this.atualizarEstoque(locacao.getFilmes());
		BeanUtils.copyProperties(locacao, locacaoSalva, "id");
		return this.locacaoRepository.save(locacaoSalva);
	}
	
	
	private void atualizarEstoque(List<LocacaoTemFilme> filmes) {
		for(int i=0;i<filmes.size();i++) {
			this.locacaoTemFilmeService.somaQuantidadeEstoque(filmes.get(i).getFilme(),filmes.get(i).getQuantidadeLocada());
		}
		
	}
	/**
	 * Metodo responsavel por setar as informacoes vazias para salvar uma {@link Locacao}
	 * 
	 * @param locacao
	 */
	private void setInformacoesVazias(Locacao locacao) {
		locacao.setDataRealizacao(LocalDate.now());
		locacao.setDataDevolucao(null);
		locacao.setValorTotal(null);
		locacao.setFilmes(null);
		locacao.setStatusLocacao(StatusLocacao.ABERTO);
	}
	
	/**
	 * Metodo que seta informacoes adicionais para salvar uma {@link Locacao}
	 * @param locacao
	 */
	private void setInformacoesDevolucao(Locacao locacao) {
		locacao.setDataDevolucao(LocalDate.now());
		locacao.setStatusLocacao(StatusLocacao.DEVOLVIDO);
		
	}
	/**
	 * Metodo responsavel por  calcular o total de dias desde a data da realizacao da locacao ate a devolucao.
	 * 
	 * @param dataInicio
	 * @return Total de dias da locacao.
	 */
	private Double valorFinal(LocalDate dataInicio, LocalDate dataDevolucao, List<LocacaoTemFilme> filmes) {
		Integer totalDias = (int) ChronoUnit.DAYS.between(dataInicio, dataDevolucao);
		Double valorFinal = this.calcularValorTodasDiarias(filmes)*totalDias;
		return valorFinal;
	}

	/**
	 * Metodo que calcula o valor total a ser pago pela {@link Locacao}
	 * @param locacao
	 * @return Valor total a ser pago pela {@link Locacao}
	 */
	private Double calcularValorTodasDiarias(List<LocacaoTemFilme> filmes) {
		Double valorTotalDiarias = 0.0;
		for(int i=0; i<filmes.size();i++) {
			valorTotalDiarias+= filmes.get(i).getVlrTotalDiaria();
			System.out.println(valorTotalDiarias);
		}
		//System.out.println(valorTotalDiarias);
		return valorTotalDiarias;
	}



}

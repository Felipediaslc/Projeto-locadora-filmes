package com.locadora.infra.locacao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.locadora.infra.cliente.Cliente;
import com.locadora.infra.enums.StatusLocacao;


/**
 * Classe Modelo de Locacao para manipulacao no banco de dados
 * 
 * @author SOUSA, Taynar - Marco/2019
 * @since 1.0
 */
@Entity
@Table(name = "locacao")
public class Locacao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "DATA_REALIZACAO")
	private LocalDate dataRealizacao;

	@Column(name = "DATA_DEVOLUCAO")
	private LocalDate dataDevolucao;

	@Column(name = "VALOR_TOTAL")
	private Double valorTotal;

	@ManyToOne
	@JoinColumn(name = "CLIENTE_ID")
	private Cliente cliente;

	@Enumerated(EnumType.STRING)
	@Column(name = "STATUS")
	private StatusLocacao statusLocacao;

	@OneToMany(mappedBy = "filme",cascade = CascadeType.ALL,orphanRemoval = true )
	private List<LocacaoTemFilme> filmes = new ArrayList<>();
	
	
	public Locacao(LocalDate dataRealizacao, LocalDate dataDevolucao, Double valorTotal, Cliente cliente, StatusLocacao statusLocacao,
			List<LocacaoTemFilme> filmes) {
		super();
		this.dataRealizacao = dataRealizacao;
		this.dataDevolucao = dataDevolucao;
		this.valorTotal = valorTotal;
		this.cliente = cliente;
		this.statusLocacao = statusLocacao;
		this.filmes = filmes;
	}

	public Locacao() {

	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cliente == null) ? 0 : cliente.hashCode());
		result = prime * result + ((dataDevolucao == null) ? 0 : dataDevolucao.hashCode());
		result = prime * result + ((dataRealizacao == null) ? 0 : dataRealizacao.hashCode());
		result = prime * result + ((filmes == null) ? 0 : filmes.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((statusLocacao == null) ? 0 : statusLocacao.hashCode());
		result = prime * result + ((valorTotal == null) ? 0 : valorTotal.hashCode());
		return result;
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Locacao other = (Locacao) obj;
		if (cliente == null) {
			if (other.cliente != null)
				return false;
		} else if (!cliente.equals(other.cliente))
			return false;
		if (dataDevolucao == null) {
			if (other.dataDevolucao != null)
				return false;
		} else if (!dataDevolucao.equals(other.dataDevolucao))
			return false;
		if (dataRealizacao == null) {
			if (other.dataRealizacao != null)
				return false;
		} else if (!dataRealizacao.equals(other.dataRealizacao))
			return false;
		if (filmes == null) {
			if (other.filmes != null)
				return false;
		} else if (!filmes.equals(other.filmes))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (statusLocacao != other.statusLocacao)
			return false;
		if (valorTotal == null) {
			if (other.valorTotal != null)
				return false;
		} else if (!valorTotal.equals(other.valorTotal))
			return false;
		return true;
	}



	public StatusLocacao getStatus() {
		return statusLocacao;
	}
	public void setStatus(StatusLocacao status) {
		this.statusLocacao = status;
	}

	public List<LocacaoTemFilme> getFilmes() {
		return filmes;
	}

	public void setFilmes(List<LocacaoTemFilme> filmes) {
		this.filmes = filmes;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDate getDataRealizacao() {
		return dataRealizacao;
	}

	public void setDataRealizacao(LocalDate dataRealizacao) {
		this.dataRealizacao = dataRealizacao;
	}

	public LocalDate getDataDevolucao() {
		return dataDevolucao;
	}

	public void setDataDevolucao(LocalDate dataDevolucao) {
		this.dataDevolucao = dataDevolucao;
	}

	public Double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public StatusLocacao getStatuLocacao() {
		return statusLocacao;
	}

	public void setStatusLocacao(StatusLocacao statusLocacao) {
		this.statusLocacao = statusLocacao;
	}

}

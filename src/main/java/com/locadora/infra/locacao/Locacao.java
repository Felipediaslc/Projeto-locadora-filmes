package com.locadora.infra.locacao;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.locadora.infra.cliente.Cliente;

@Entity
@Table(name="LOCACAO")
public class Locacao {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "DATA_REALIZACAO")
	private Date dataRealizacao;
	
	@Column(name = "DATA_DEVOLUCAO")
	private Date dataDevolucao;
	
	@Column(name = "VALOR_TOTAL")
	private Double valorTotal;
	
	@ManyToOne
	@JoinColumn(name="CLIENTE_ID")
	private Cliente cliente;
	
	

	public Locacao(Date dataRealizacao, Date dataDevolucao, Double valorTotal, Cliente cliente) {
		super();
		this.dataRealizacao = dataRealizacao;
		this.dataDevolucao = dataDevolucao;
		this.valorTotal = valorTotal;
		this.cliente = cliente;
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
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		}
		else if (!cliente.equals(other.cliente))
			return false;
		if (dataDevolucao == null) {
			if (other.dataDevolucao != null)
				return false;
		}
		else if (!dataDevolucao.equals(other.dataDevolucao))
			return false;
		if (dataRealizacao == null) {
			if (other.dataRealizacao != null)
				return false;
		}
		else if (!dataRealizacao.equals(other.dataRealizacao))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		}
		else if (!id.equals(other.id))
			return false;
		if (valorTotal == null) {
			if (other.valorTotal != null)
				return false;
		}
		else if (!valorTotal.equals(other.valorTotal))
			return false;
		return true;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDataRealizacao() {
		return dataRealizacao;
	}

	public void setDataRealizacao(Date dataRealizacao) {
		this.dataRealizacao = dataRealizacao;
	}

	public Date getDataDevolucao() {
		return dataDevolucao;
	}

	public void setDataDevolucao(Date dataDevolucao) {
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
	
	
}

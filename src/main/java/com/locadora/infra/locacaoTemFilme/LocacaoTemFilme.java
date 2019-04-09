package com.locadora.infra.locacaoTemFilme;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.locadora.infra.filme.Filme;
import com.locadora.infra.locacao.Locacao;

@Entity
@Table(name = "locacao_tem_filme")
public class LocacaoTemFilme {
	
	@EmbeddedId
	private LocacaoTemFilmeId id;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @MapsId("locacaoId")
    private Locacao locacao;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @MapsId("filmeId")
    private Filme filme;
	
	@Column(name = "QUANTIDADE_LOCADA")
	private Integer quantidadeLocada;

	@Column(name = "VALOR_TOTAL_DIARIA")
	private Double vlrTotalDiaria;

	
	
	public LocacaoTemFilme( Locacao locacao, Filme filme, Integer quantidadeLocada) {
		
		this.locacao = locacao;
		this.filme = filme;
		this.quantidadeLocada = quantidadeLocada;
		this.id = new LocacaoTemFilmeId(locacao.getId(), filme.getId());

	}

	public LocacaoTemFilme(){
		
	}
	
	

	public LocacaoTemFilmeId getId() {
		return id;
	}

	public void setId(LocacaoTemFilmeId id) {
		this.id = id;
	}
	
	@JsonIgnore
	public Locacao getLocacao() {
		return locacao;
	}

	public void setLocacao(Locacao locacao) {
		this.locacao = locacao;
	}

	
	public Filme getFilme() {
		return filme;
	}

	public void setFilme(Filme filme) {
		this.filme = filme;
	}



	public Integer getQuantidadeLocada() {
		return quantidadeLocada;
	}

	public void setQuantidadeLocada(Integer quantidadeLocada) {
		this.quantidadeLocada = quantidadeLocada;
	}

	public Double getVlrTotalDiaria() {
		return vlrTotalDiaria;
	}

	public void setVlrTotalDiaria(Double vlrTotalDiaria) {
		this.vlrTotalDiaria = vlrTotalDiaria;
	}
	
	
}

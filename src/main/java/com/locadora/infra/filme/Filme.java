package com.locadora.infra.filme;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.locadora.infra.genero.Genero;

/**
 * Classe Modelo de filme para manipulação do banco de dados
 * @author SOUSA,Taynar - Marco/2019
 * @since 1.0
 */
@Entity
@Table(name="FILME")
public class Filme {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotNull
	@Column(name="TITULO")
	@Size(min=3,max=50)
	private String titulo;
	
	@NotNull
	@Column(name= "DURACAO")
	private Integer duracao;
	
	@NotNull
	@Column(name = "VALOR_DIARIA")
	private Double valorDiaria;
	
	@NotNull
	@Column(name = "QUANTIDADE_ESTOQUE")
	private Integer qtEstoque;
	
	@NotNull
	@Column(name = "NOME_DIRETOR")
	private String nomeDiretor;
	
	@NotNull
	@Column(name="SINOPSE")
	private String sinopse;
	
	@ManyToOne
	@JoinColumn(name="GENERO_ID")
	private Genero genero;

	
	
	public Filme(String titulo,  Integer duracao,  Double valorDiaria,
			 Integer qtEstoque, String sinopse, Genero genero, String nomeDiretor) {
		super();
		this.titulo = titulo;
		this.duracao = duracao;
		this.valorDiaria = valorDiaria;
		this.qtEstoque = qtEstoque;
		this.sinopse = sinopse;
		this.genero = genero;
		this.nomeDiretor = nomeDiretor;
	}
	
	public Filme() {
		
	}
	
	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNomeDiretor() {
		return nomeDiretor;
	}

	public void setNomeDiretor(String nomeDiretor) {
		this.nomeDiretor = nomeDiretor;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Integer getDuracao() {
		return duracao;
	}

	public void setDuracao(Integer duracao) {
		this.duracao = duracao;
	}

	public Double getValorDiaria() {
		return valorDiaria;
	}

	public void setValorDiaria(Double valorDiaria) {
		this.valorDiaria = valorDiaria;
	}

	public Integer getQtEstoque() {
		return qtEstoque;
	}

	public void setQtEstoque(Integer qtEstoque) {
		this.qtEstoque = qtEstoque;
	}

	public String getSinopse() {
		return sinopse;
	}

	public void setSinopse(String sinopse) {
		this.sinopse = sinopse;
	}

	public Genero getGenero() {
		return genero;
	}

	public void setGenero(Genero genero) {
		this.genero = genero;
	}
	
	
}

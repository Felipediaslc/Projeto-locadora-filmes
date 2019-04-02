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
	private Integer quantidadeEstoque;
	
	@NotNull
	@Column(name = "NOME_DIRETOR")
	@Size(min =3, max=50)
	private String nomeDiretor;
	
	@NotNull
	@Column(name="SINOPSE")
	@Size(min = 5, max = 150)
	private String sinopse;
	
	@ManyToOne
	@JoinColumn(name="GENERO_ID")
	private Genero genero;

	
	public Filme(String titulo,  Integer duracao,  Double valorDiaria,
			 Integer quantidadeEstoque,  String nomeDiretor,  String sinopse, Genero genero) {
		super();
		this.titulo = titulo;
		this.duracao = duracao;
		this.valorDiaria = valorDiaria;
		this.quantidadeEstoque = quantidadeEstoque;
		this.nomeDiretor = nomeDiretor;
		this.sinopse = sinopse;
		this.genero = genero;
	}

	public Filme() {
		
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((duracao == null) ? 0 : duracao.hashCode());
		result = prime * result + ((genero == null) ? 0 : genero.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nomeDiretor == null) ? 0 : nomeDiretor.hashCode());
		result = prime * result + ((quantidadeEstoque == null) ? 0 : quantidadeEstoque.hashCode());
		result = prime * result + ((sinopse == null) ? 0 : sinopse.hashCode());
		result = prime * result + ((titulo == null) ? 0 : titulo.hashCode());
		result = prime * result + ((valorDiaria == null) ? 0 : valorDiaria.hashCode());
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
		Filme other = (Filme) obj;
		if (duracao == null) {
			if (other.duracao != null)
				return false;
		} else if (!duracao.equals(other.duracao))
			return false;
		if (genero == null) {
			if (other.genero != null)
				return false;
		} else if (!genero.equals(other.genero))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nomeDiretor == null) {
			if (other.nomeDiretor != null)
				return false;
		} else if (!nomeDiretor.equals(other.nomeDiretor))
			return false;
		if (quantidadeEstoque == null) {
			if (other.quantidadeEstoque != null)
				return false;
		} else if (!quantidadeEstoque.equals(other.quantidadeEstoque))
			return false;
		if (sinopse == null) {
			if (other.sinopse != null)
				return false;
		} else if (!sinopse.equals(other.sinopse))
			return false;
		if (titulo == null) {
			if (other.titulo != null)
				return false;
		} else if (!titulo.equals(other.titulo))
			return false;
		if (valorDiaria == null) {
			if (other.valorDiaria != null)
				return false;
		} else if (!valorDiaria.equals(other.valorDiaria))
			return false;
		return true;
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

	public Integer getQuantidadeEstoque() {
		return quantidadeEstoque;
	}

	public void setQuantidadeEstoque(Integer quantidadeEstoque) {
		this.quantidadeEstoque = quantidadeEstoque;
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

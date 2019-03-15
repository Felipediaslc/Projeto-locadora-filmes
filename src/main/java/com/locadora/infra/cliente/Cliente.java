package com.locadora.infra.cliente;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CPF;

@Entity
@Table(name="CLIENTE")
public class Cliente {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@CPF
	@Column(name="CPF")
	private  String cpf;
	
	@NotNull
	@Size(min = 3, max = 150)
	@Column(name="NOME")
	private String nome;
	
	@NotNull
	@Size(min = 8, max = 150)
	@Column(name="RUA")
	private String rua;
	
	@NotNull
	@Column(name="CEP")
	private String cep;
	
	@NotNull
	@Size(min = 4, max = 150)
	@Column(name = "BAIRRO")
	private String bairro;
	
	@Size(min = 3, max = 150)
	@Column(name="COMPLEMENTO")
	private String complemento;
	
	@NotNull
	@Size(min = 3, max = 50)
	@Column(name="CIDADE")
	private String cidade;
	
	

	public Cliente(@CPF String cpf, @NotNull @Size(min = 3, max = 150) String nome,
			@NotNull @Size(min = 8, max = 150) String rua, @NotNull String cep,
			@NotNull @Size(min = 4, max = 150) String bairro, @Size(min = 3, max = 150) String complemento,
			@NotNull @Size(min = 3, max = 50) String cidade) {
		super();
		this.cpf = cpf;
		this.nome = nome;
		this.rua = rua;
		this.cep = cep;
		this.bairro = bairro;
		this.complemento = complemento;
		this.cidade = cidade;
	}
	public Cliente() {
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	
	
}

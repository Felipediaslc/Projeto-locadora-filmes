package com.locadora.infra.cliente;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CPF;

import com.locadora.infra.endereco.Endereco;

/**
 * Classe Modelo de clientes para manipulação do banco de dados.
 * 
 * @author SOUSA, Taynar Marco/2019
 * @since 1.0
 */

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
	
	@Embedded
	private Endereco endereco;
	
	

	public Cliente(String cpf,String nome, Endereco endereco) {
		this.cpf = cpf;
		this.nome = nome;
		this.endereco = endereco;
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

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	
	
}

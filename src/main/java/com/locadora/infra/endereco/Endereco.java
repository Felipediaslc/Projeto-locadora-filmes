package com.locadora.infra.endereco;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.locadora.infra.cliente.Cliente;
/**
 * Classe Modelo de endereço do {@link Cliente}
 * 
 * @author SOUSA, Taynar Abril/2019
 * @since 1.0
 */
@Embeddable
public class Endereco {

	@NotBlank
	@Size(min = 8, max = 150)
	@Column(name="RUA")
	private String rua;
	
	@NotBlank
	@Column(name="CEP")
	@Size(min = 8, max = 9)
	private String cep;
	
	@NotBlank
	@Size(min = 4, max = 150)
	@Column(name = "BAIRRO")
	private String bairro;
	
	@Size(min = 3, max = 150)
	@Column(name="COMPLEMENTO")
	private String complemento;
	
	@NotBlank
	@Size(min = 3, max = 50)
	@Column(name="CIDADE")
	private String cidade;

	
	
	public Endereco( String rua,  String cep,String bairro, String complemento, String cidade) {
		super();
		this.rua = rua;
		this.cep = cep;
		this.bairro = bairro;
		this.complemento = complemento;
		this.cidade = cidade;
	}
	public Endereco() {
		
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

package com.locadora.infra.usuario;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.locadora.infra.perfil.Perfil;
@Entity
@Table(name="USUARIO")
public class Usuario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotNull
	@Column(name="LOGIN")
	private String login;
	
	@NotNull
	@Column(name="SENHA")
	private String senha;
	
	@ManyToOne
	@JoinColumn(name="PERFIL_ID")
	private Perfil perfil;
	
	

	public Usuario(@NotNull String login, @NotNull String senha, Perfil perfil) {
		super();
		this.login = login;
		this.senha = senha;
		this.perfil = perfil;
	}
	
	public Usuario() {
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Perfil getPerfil() {
		return perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}
	
	
}

package com.locadora.infra.perfil;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.locadora.infra.permissao.Permissao;


/**
 * Classe Modelo de perfil para manipulacao no banco de dados
 * @author SOUSA, Taynar
 * @since 1.0
 */
@Entity
@Table(name = "PERFIL")
public class Perfil {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotNull
	@Size(min =3, max = 20)
	private String nome;
	
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name="PERFIL_TEM_PERMISSAO", joinColumns = @JoinColumn(name="PERFIL_ID"), inverseJoinColumns = @JoinColumn(name = "PERMISSAO_ID"))
	private List<Permissao> permissoes;

	
	
	public Perfil(String nome) {
		super();
		this.nome = nome;
	}
	
	public Perfil() {
		
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
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
		Perfil other = (Perfil) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		}
		else if (!id.equals(other.id))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		}
		else if (!nome.equals(other.nome))
			return false;
		return true;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Permissao> getPermissoes() {
		return permissoes;
	}

	public void setPermissoes(List<Permissao> permissoes) {
		this.permissoes = permissoes;
	}
	
	
}

package com.locadora.infra.filme;

import com.locadora.infra.genero.Genero;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Filme.class)
public abstract class Filme_ {

	public static volatile SingularAttribute<Filme, String> sinopse;
	public static volatile SingularAttribute<Filme, Double> valorDiaria;
	public static volatile SingularAttribute<Filme, String> nomeDiretor;
	public static volatile SingularAttribute<Filme, Genero> genero;
	public static volatile SingularAttribute<Filme, String> titulo;
	public static volatile SingularAttribute<Filme, Integer> quantidadeEstoque;
	public static volatile SingularAttribute<Filme, Integer> id;
	public static volatile SingularAttribute<Filme, Integer> duracao;

}


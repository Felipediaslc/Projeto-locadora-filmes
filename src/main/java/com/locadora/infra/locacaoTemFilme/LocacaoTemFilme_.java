package com.locadora.infra.locacaoTemFilme;

import com.locadora.infra.filme.Filme;
import com.locadora.infra.locacao.Locacao;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(LocacaoTemFilme.class)
public abstract class LocacaoTemFilme_ {

	public static volatile SingularAttribute<LocacaoTemFilme, Double> vlrTotalDiaria;
	public static volatile SingularAttribute<LocacaoTemFilme, Integer> quantidadeLocada;
	public static volatile SingularAttribute<LocacaoTemFilme, LocacaoTemFilmeId> id;
	public static volatile SingularAttribute<LocacaoTemFilme, Locacao> locacao;
	public static volatile SingularAttribute<LocacaoTemFilme, Filme> filme;

}


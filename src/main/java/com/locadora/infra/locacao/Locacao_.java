package com.locadora.infra.locacao;

import com.locadora.infra.cliente.Cliente;
import com.locadora.infra.enums.StatusLocacao;
import com.locadora.infra.locacaoTemFilme.LocacaoTemFilme;
import java.time.LocalDate;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Locacao.class)
public abstract class Locacao_ {

	public static volatile SingularAttribute<Locacao, LocalDate> dataRealizacao;
	public static volatile SingularAttribute<Locacao, Cliente> cliente;
	public static volatile SingularAttribute<Locacao, StatusLocacao> statusLocacao;
	public static volatile SingularAttribute<Locacao, Double> valorTotal;
	public static volatile ListAttribute<Locacao, LocacaoTemFilme> filmes;
	public static volatile SingularAttribute<Locacao, Integer> id;
	public static volatile SingularAttribute<Locacao, LocalDate> dataDevolucao;

}


package com.locadora.infra.locacao.filter;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.locadora.infra.locacao.Locacao;
import com.locadora.infra.locacao.Locacao_;

public class LocacaoRepositoryImpl implements LocacaoRepositoryQuery {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public List<Locacao> filtrar(LocacaoFilter locacaoFilter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Locacao> criteria = builder.createQuery(Locacao.class);

		Root<Locacao> root = criteria.from(Locacao.class);

		Predicate[] predicates = criarRestricoes(locacaoFilter, builder, root);
		criteria.where(predicates);

		TypedQuery<Locacao> query = manager.createQuery(criteria);
		return query.getResultList();
	}

	private Predicate[] criarRestricoes(LocacaoFilter locacaoFilter, CriteriaBuilder builder, Root<Locacao> root) {
		List<Predicate> predicates = new ArrayList<>();
		if (locacaoFilter.getDataRealizacaoDe() != null) {
			predicates.add(
					builder.greaterThanOrEqualTo(root.get(Locacao_.dataRealizacao),
							locacaoFilter.getDataRealizacaoDe()));
		}
		if (locacaoFilter.getDataRealizacaoAte() != null) {
			predicates.add(
					builder.lessThanOrEqualTo(root.get(Locacao_.dataRealizacao),
							locacaoFilter.getDataRealizacaoAte()));
		}
		if (locacaoFilter.getDataDevolucaoDe() != null) {
			predicates.add(
					builder.greaterThanOrEqualTo(root.get(Locacao_.dataRealizacao),
							locacaoFilter.getDataDevolucaoDe()));
		}
		if (locacaoFilter.getDataDevolucaoAte() != null) {
			predicates.add(
					builder.lessThanOrEqualTo(root.get(Locacao_.dataRealizacao),
							locacaoFilter.getDataDevolucaoAte()));
		}
		return predicates.toArray(new Predicate[predicates.size()]);
	}

}

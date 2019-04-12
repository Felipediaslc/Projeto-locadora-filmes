package com.locadora.infra.locacao.filter;

import java.util.List;

import com.locadora.infra.locacao.Locacao;

public interface LocacaoRepositoryQuery{
	public List<Locacao> filtrar(LocacaoFilter locacaoFilter);
	
}

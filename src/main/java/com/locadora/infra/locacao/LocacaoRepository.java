package com.locadora.infra.locacao;

import org.springframework.data.jpa.repository.JpaRepository;



/**
 * Repositorio de {@link Locacao} para manipulacao no banco de dados
 * @author SOUSA, Taynar - Marco/2019
 * @since 1.0
 */
public interface LocacaoRepository extends JpaRepository<Locacao, Integer>{

}

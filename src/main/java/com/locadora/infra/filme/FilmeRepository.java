package com.locadora.infra.filme;

import org.springframework.data.jpa.repository.JpaRepository;
/**
 * Repositorio de {@link Filme} para manipulacao no banco de dados
 * @author SOUSA, Taynar - Marco/2019
 * @since 1.0
 */
public interface FilmeRepository extends JpaRepository<Filme, Integer> {

}

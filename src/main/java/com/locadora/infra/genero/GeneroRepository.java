package com.locadora.infra.genero;



import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositorio de {@link Genero} para manipulacao no banco de dados
 * @author SOUSA, Taynar - Marco/2019
 * @since 1.0
 */
public interface GeneroRepository extends JpaRepository<Genero, Integer> {

	Optional<Genero> findByNome(String nome);
	
}

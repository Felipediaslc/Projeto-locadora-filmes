package com.locadora.infra.cliente;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositorio de {@link Cliente} para manipulacao no banco de dados
 * @author SOUSA, Taynar - Marco/2019
 * @since 1.0
 */
public interface ClienteRepository extends JpaRepository<Cliente, Integer>{
	Optional<Cliente> findByCpf(String cpf);
}

package com.locadora.infra.locacao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.locadora.infra.cliente.Cliente;
import com.locadora.infra.enums.Status;



/**
 * Repositorio de {@link Locacao} para manipulacao no banco de dados
 * @author SOUSA, Taynar - Marco/2019
 * @since 1.0
 */
public interface LocacaoRepository extends JpaRepository<Locacao, Integer>{

		Locacao findByCliente(Cliente cliente);
		List<Locacao> findByStatus(Status status);
}

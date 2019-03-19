package com.locadora.infra.filme;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.locadora.infra.genero.Genero;
/**
 * Repositorio de {@link Filme} para manipulacao no banco de dados
 * @author SOUSA, Taynar - Marco/2019
 * @since 1.0
 */
public interface FilmeRepository extends JpaRepository<Filme, Integer> {
	Optional<Filme> findByTitulo(String titulo);
	List<Filme> findFilmeByGenero(Genero genero);
}

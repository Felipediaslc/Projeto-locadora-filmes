package com.locadora.infra.cliente;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller de {@link Cliente clientes} para configuracao das rotas
 * 
 * @author SOUSA,Taynar Marco/2019
 * @since 1.0
 */
@RestController
@RequestMapping("/clientes")
public class ClienteController {

	@Autowired
	private ClienteService clienteService;
	
	/**
	 * Metodo responsavel por listar todos os {@link Cliente clientes} no body.
	 * @see ClienteService
	 * @return {@link HttpStatus.OK} com o s dados no body.
	 * @since 1.0
	 */
	@GetMapping
	public ResponseEntity<List<Cliente>> listarTodos(){
		List<Cliente> clientes =  this.clienteService.listarTodos();
		return ResponseEntity.status(HttpStatus.OK).body(clientes);
	}
	
	/**
	 * Metodo responsavel por buscar os dados de um {@link Cliente} pelo seu CPF
	 * @see ClienteService
	 * @see ResponseEntity
	 * @param cpf
	 * @return {@link HttpStatus.OK} com os dados do cliente no body.
	 * @since 1.0
	 */
	@GetMapping("/{cpf}")
	public ResponseEntity<Cliente> buscarPeloCpf(@PathVariable("cpf") String cpf){
		Cliente clienteASerBuscado = this.clienteService.buscarPorCpf(cpf);
		return ResponseEntity.status(HttpStatus.OK).body(clienteASerBuscado);
	}
	
	/**
	 * Metodo responsavel pela criacao de um {@link Cliente}
	 * @see ClienteService
	 * @see ResponseEntity
	 * @param cliente
	 * @return {HttpStatus.CREATED} com as informacoes no body.
	 * @since 1.0
	 */
	@PostMapping
	public ResponseEntity<Cliente> criar(@Valid @RequestBody Cliente cliente){
		Cliente clienteSalvo = this.clienteService.criar(cliente);
		return ResponseEntity.status(HttpStatus.CREATED).body(clienteSalvo);
	}
	/**
	 * Metodo responsavel por atualizar os dados de {@link Cliente}
	 * @see ClienteService
	 * @see ResponseEntity
	 * @param id
	 * @param cliente
	 * @return {@link HttpStatus.NO_CONTENT}
	 */
	@PutMapping("/{id}")
	public ResponseEntity<Cliente> atualizar(@PathVariable Integer id, @Valid @RequestBody Cliente cliente){
		Cliente clienteAtualizado = this.clienteService.atualizar(id, cliente);
		
		return ResponseEntity.status(HttpStatus.OK).body(clienteAtualizado);
	}
}

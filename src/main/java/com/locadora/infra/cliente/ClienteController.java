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
 * Controller de {@link Cliente clientes} para configuração das rotas
 * 
 * @author SOUSA,Taynar Marco/2019
 * @since 1.0
 */
@RestController
@RequestMapping("/clientes")
public class ClienteController {

	@Autowired
	private ClienteService clienteService;
	
	@GetMapping
	public ResponseEntity<List<Cliente>> listarTodos(){
		List<Cliente> clientes =  this.clienteService.listarTodos();
		return ResponseEntity.status(HttpStatus.OK).body(clientes);
	}
	
	@GetMapping("/{cpf}")
	public ResponseEntity<Cliente> buscarPeloCpf(@PathVariable("cpf") String cpf){
		Cliente clienteASerBuscado = this.clienteService.buscarPorCpf(cpf);
		return ResponseEntity.status(HttpStatus.OK).body(clienteASerBuscado);
	}
	
	@PostMapping
	public ResponseEntity<Cliente> criar(@Valid @RequestBody Cliente cliente){
		Cliente clienteSalvo = this.clienteService.criar(cliente);
		return ResponseEntity.status(HttpStatus.CREATED).body(clienteSalvo);
	}
	
	@PutMapping("/{cpf}")
	public ResponseEntity<Cliente> atualizar(@PathVariable("cpf") String cpf, @Valid @RequestBody Cliente cliente){
		Cliente clienteAtualizado = this.clienteService.atualizar(cpf, cliente);
		
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body(clienteAtualizado);
	}
}

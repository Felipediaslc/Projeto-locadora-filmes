package com.locadora.infra.cliente;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.locadora.infra.cliente.exception.ClienteNaoEncontradoException;
import com.locadora.infra.cliente.exception.CpfDuplicadoException;

/**
 * Classe responsavel pelos servicos e regras de negocios de {@link Cliente}
 * 
 * @author SOUSA, Taynar
 * @since 1.0 
 */
@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;
	
	
	public List<Cliente> listarTodos(){
		return this.clienteRepository.findAll();
	}
	
	public Cliente buscarPorCpf(String cpf) {
		if(!cpfExiste(cpf)) {
			throw new ClienteNaoEncontradoException();
		}
		
		Optional<Cliente> clienteAserBuscado = this.clienteRepository.findByCpf(cpf);
		return clienteAserBuscado.get();
		
	}
	
	public Cliente criar(Cliente cliente) {
		if(cpfExiste(cliente.getCpf())) {
			throw new CpfDuplicadoException();
		}
		
		Cliente clienteSalvo = this.clienteRepository.save(cliente);
		return clienteSalvo;
	}
	
	private boolean cpfExiste(String cpf) {
		Optional<Cliente> clienteExiste =  this.clienteRepository.findByCpf(cpf);
		return clienteExiste.isPresent();
	}
}

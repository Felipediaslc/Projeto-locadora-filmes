package com.locadora.infra.cliente;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.locadora.infra.cliente.exception.ClienteNaoEncontradoException;
import com.locadora.infra.cliente.exception.CpfDuplicadoException;
import com.locadora.infra.filme.Filme;
import com.locadora.infra.filme.exception.FilmeDuplicadoException;
import com.locadora.utils.ClienteUtils;

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

	/**
	 * Metodo Responsavel por listar todos os {@link Cliente clientes}
	 * 
	 * @return Lista contendo todos os {@link Cliente clientes}; Caso não haja
	 *         nenhum retorna uma lista vazia
	 * @since 1.0
	 */
	public List<Cliente> listarTodos() {
		return this.clienteRepository.findAll();
	}

	/**
	 * Metodo responsavel por buscar um {@link Cliente} pelo seu CPF
	 * 
	 * @throws ClienteNaoEncontradoException
	 * @param cpf
	 * @return {@link Cliente} pertencente ao CPF informado.
	 * @since 1.0
	 */
	public Cliente buscarPorCpf(String cpf) {
		String cpfFormatado = ClienteUtils.validacpf(cpf);
		if (!cpfExiste(cpfFormatado)) {
			throw new ClienteNaoEncontradoException();
		}

		Optional<Cliente> clienteAserBuscado = this.clienteRepository.findByCpf(cpfFormatado);
		return clienteAserBuscado.get();

	}

	/**
	 * Metodo responsavel por criar um {@link Cliente} e adiciona-lo ao banco.
	 * 
	 * @throws CpfDuplicadoException
	 * @param cliente
	 * @return {@link Cliente} cadastrado
	 * @since 1.0
	 */
	public Cliente criar(Cliente cliente) {
		if (cpfExiste(cliente.getCpf())) {
			throw new CpfDuplicadoException();
		}
		setInformation(cliente);
		Cliente clienteSalvo = this.clienteRepository.save(cliente);
		return clienteSalvo;
	}

	public Cliente atualizar(String cpf, Cliente cliente) {
		Cliente clienteSalvo = buscarPorCpf(cpf);

		if (cpfExiste(cliente.getCpf())) {
			if (cliente.getId() != cliente.getId()) {
				throw new CpfDuplicadoException();
			}
		}
		BeanUtils.copyProperties(cliente, clienteSalvo, "id");
		return this.clienteRepository.save(clienteSalvo);
	}

	/**
	 * Metodo responsavel por verificar se cpf ja existe.
	 * 
	 * @param cpf
	 * @return true se existir; false se não existir
	 * @since 1.0
	 */
	private boolean cpfExiste(String cpf) {
		Optional<Cliente> clienteExiste = this.clienteRepository.findByCpf(cpf);
		return clienteExiste.isPresent();
	}

	/**
	 * Metodo que faz a formatação do CEP e do CPF.
	 * 
	 * @see ClienteUtils
	 * @param cliente
	 * @since 1.0
	 */
	private void setInformation(Cliente cliente) {
		String cepFormatado = ClienteUtils.validaCep(cliente.getCep());
		String cpfFormatado = ClienteUtils.validacpf(cliente.getCpf());

		cliente.setCep(cepFormatado);
		cliente.setCpf(cpfFormatado);
	}
}

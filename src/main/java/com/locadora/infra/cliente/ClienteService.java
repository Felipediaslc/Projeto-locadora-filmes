package com.locadora.infra.cliente;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.locadora.infra.cliente.exception.ClienteNaoEncontradoException;
import com.locadora.infra.cliente.exception.CpfDuplicadoException;
import com.locadora.infra.endereco.Endereco;
import com.locadora.utils.ClienteUtils;

/**
 * Classe responsavel pelos servicos e regras de negocios de {@link Cliente}
 * 
 * @author SOUSA, Taynar Marco/2019
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
		String cpfFormatado = ClienteUtils.validaCpf(cpf);
		if (!cpfExiste(cpfFormatado)) {
			throw new ClienteNaoEncontradoException();
		}

		Optional<Cliente> clienteSalvo = this.clienteRepository.findByCpf(cpfFormatado);
		return clienteSalvo.get();

	}
	
	/**
	 * Metodo responsavel por buscar um {@link Cliente} a partir do id.
	 * @see ClienteRepository
	 * @param id
	 * @return {@link Cliente} com o id informado.
	 * @since 1.0
	 */
	public Cliente buscarporId(Integer id) {
		Optional<Cliente> clienteSalvo = this.clienteRepository.findById(id);
		if(clienteSalvo.isPresent()) {
			throw new ClienteNaoEncontradoException();
		}
		return clienteSalvo.get();	}

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
		setInformacao(cliente.getEndereco(),cliente);
		Cliente clienteSalvo = this.clienteRepository.save(cliente);
		return clienteSalvo;
	}
	/**
	 * Metodo responsavel por atualizar os dados do {@link Cliente}
	 * @see ClienteRepository
	 * @param id
	 * @param cliente
	 * @return {@link Cliente} atualizado
	 * @since 1.0
	 */
	public Cliente atualizar(Integer id, Cliente cliente) {
		Cliente clienteSalvo = buscarporId(id);

		if (cpfExiste(cliente.getCpf())) {
			if (cliente.getId() != clienteSalvo.getId()) {
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
		Optional<Cliente> clienteSalvo = this.clienteRepository.findByCpf(cpf);
		return clienteSalvo.isPresent();
	}

	/**
	 * Metodo que faz a formatação do CEP e do CPF.
	 * 
	 * @see ClienteUtils
	 * @param endereco
	 * @param cliente
	 * @since 1.0
	 */
	private void setInformacao(Endereco endereco, Cliente cliente) {
		String cepFormatado = ClienteUtils.validaCep(endereco.getCep());
		String cpfFormatado = ClienteUtils.validaCpf(cliente.getCpf());

		endereco.setCep(cepFormatado);
		cliente.setCpf(cpfFormatado);
	}
}

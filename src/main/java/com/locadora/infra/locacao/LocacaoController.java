package com.locadora.infra.locacao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Classe responsavel pela configuracao de rotas de {@link Locacao}
 * @author SOUSA, Taynar - Marco/2019
 * @since 1.0
 */
@RestController
@RequestMapping("/locacoes")
public class LocacaoController {

	@Autowired
	private LocacaoService locacaoService;
	
	
	@GetMapping
	public ResponseEntity<List<Locacao>> listarTodos(){
		List<Locacao> listaLocacao = this.locacaoService.listarTodos();
		
		return ResponseEntity.status(HttpStatus.OK).body(listaLocacao);
	}
	
	@GetMapping("/{cpf}")
	public ResponseEntity<Locacao> buscarPorCliente(@PathVariable("cpf") String cpf){
		Locacao locacao = this.locacaoService.buscarPorCliente(cpf);
		
		return ResponseEntity.status(HttpStatus.OK).body(locacao);
				
	}
	
	
}

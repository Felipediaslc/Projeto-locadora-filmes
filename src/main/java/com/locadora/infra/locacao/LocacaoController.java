package com.locadora.infra.locacao;

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

import com.locadora.infra.enums.StatusLocacao;
import com.locadora.infra.locacaoTemFilme.LocacaoTemFilme;

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
	@GetMapping("/filmes/id")
	public ResponseEntity<List<LocacaoTemFilme>> listarFilmes(@PathVariable Integer id){
		List<LocacaoTemFilme> listaFilmes= this.locacaoService.listarFilmes(id);
		return ResponseEntity.status(HttpStatus.OK).body(listaFilmes);
	}
	
	@GetMapping("/{cpf}")
	public ResponseEntity<List<Locacao>> buscarPorCliente(@PathVariable("cpf") String cpf){
		List<Locacao> locacao = this.locacaoService.listarPorCliente(cpf);
		
		return ResponseEntity.status(HttpStatus.OK).body(locacao);
				
	}
	
	@GetMapping("/status/ABERTO")
	public ResponseEntity<List<Locacao>> listarLocacoesEmAberto(){
		List<Locacao> locacoesAbertas = this.locacaoService.listarPorStatus(StatusLocacao.ABERTO);
		
		return ResponseEntity.status(HttpStatus.OK).body(locacoesAbertas);
				
	}
	
	@GetMapping("/status/DEVOLVIDO")
	public ResponseEntity<List<Locacao>> listarLocacoesDevolvidas(){
		List<Locacao> locacoesDevolvidas = this.locacaoService.listarPorStatus(StatusLocacao.DEVOLVIDO);
		
		return ResponseEntity.status(HttpStatus.OK).body(locacoesDevolvidas);	
	}
	
	@PostMapping
	public ResponseEntity<Locacao> criar(@Valid @RequestBody Locacao locacao){
		Locacao locacaoSalva = this.locacaoService.criar(locacao);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(locacaoSalva);
	}
	
	@PutMapping("/devolver/{id}")
	public ResponseEntity<Locacao> devolver(@PathVariable Integer id){
		Locacao locacaoSalva = this.locacaoService.devolver(id);
		return ResponseEntity.status(HttpStatus.OK).body(locacaoSalva);
	}
	
	
}

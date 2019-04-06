package com.locadora.infra.cliente;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.locadora.handler.Erro;
import com.locadora.infra.cliente.exception.ClienteNaoEncontradoException;
import com.locadora.infra.cliente.exception.CpfDuplicadoException;

/**
 * Classe responsável pelos tratamento de erros especificos a  {@link Cliente}
 * @author SOUSA, Taynar - Marco/2019
 * @since 1.0
 */
@ControllerAdvice
public class ClienteExceptionHandler {

	@Autowired
	private MessageSource messageSource;
	/**
	 * Tratamento de excecao quando os dados de algum cliente nformado nao e encontrado no banco.
	 * @see MessageSource
	 * @param ex
	 * @param request
	 * @return ResponseEntity.badRequest
	 */
	@ExceptionHandler({ClienteNaoEncontradoException.class })
	public ResponseEntity<Object> handleClienteNaoEncontradoException(ClienteNaoEncontradoException ex,
			WebRequest request) {
		String mensagemUsr = messageSource.getMessage("cliente.nao-encontrado", null,
				LocaleContextHolder.getLocale());
		String mensagemDev = ex.toString();
		List<Erro> erros = Arrays.asList(new Erro(mensagemUsr, mensagemDev));
		return ResponseEntity.badRequest().body(erros) ;
	}
	
	/**
	 * Tratamento de excecao quando o usuario quer cadastrar um cpf ja cadastrado
	 * @see MessageSource
	 * @param ex
	 * @param request
	 * @return ResponseEntity.badRequest
	 */
	@ExceptionHandler({CpfDuplicadoException.class })
	public ResponseEntity<Object> handleCpfDuplicadoException(CpfDuplicadoException ex,
			WebRequest request) {
		String mensagemUsr = messageSource.getMessage("cliente.duplicado", null,
				LocaleContextHolder.getLocale());
		String mensagemDev = ex.toString();
		List<Erro> erros = Arrays.asList(new Erro(mensagemUsr, mensagemDev));
		return ResponseEntity.badRequest().body(erros);
	}
}

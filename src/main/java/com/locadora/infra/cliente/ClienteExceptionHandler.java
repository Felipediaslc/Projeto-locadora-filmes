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
public class ClienteExceptionHandler extends ResponseEntityExceptionHandler{

	@Autowired
	MessageSource messageSource;
	
	@ExceptionHandler({ClienteNaoEncontradoException .class })
	public ResponseEntity<Object> handleDataIntegrityViolationException(ClienteNaoEncontradoException ex,
			WebRequest request) {
		String mensagemUsr = messageSource.getMessage("cliente.nao-encontrado", null,
				LocaleContextHolder.getLocale());
		String mensagemDev = ExceptionUtils.getRootCauseMessage(ex);
		List<Erro> erros = Arrays.asList(new Erro(mensagemUsr, mensagemDev));
		return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
	}
	
	@ExceptionHandler({CpfDuplicadoException .class })
	public ResponseEntity<Object> handleDataIntegrityViolationException(CpfDuplicadoException ex,
			WebRequest request) {
		String mensagemUsr = messageSource.getMessage("cliente.duplicado", null,
				LocaleContextHolder.getLocale());
		String mensagemDev = ExceptionUtils.getRootCauseMessage(ex);
		List<Erro> erros = Arrays.asList(new Erro(mensagemUsr, mensagemDev));
		return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}
}

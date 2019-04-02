package com.locadora.infra.filme;

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
import com.locadora.infra.filme.exception.FilmeDuplicadoException;
import com.locadora.infra.filme.exception.FilmeNaoEncontradoException;
import com.locadora.infra.filme.exception.FilmeSemEstoqueException;
/**
 * Classe responsavel por tratar os erros especificos a {@link Filme}
 * @author SOUSA, Taynar - Marco/2019
 *@since 1.0
 */
@ControllerAdvice
public class FilmeExceptionHandler extends ResponseEntityExceptionHandler {

	@Autowired
	private MessageSource messageSource;
	
	@ExceptionHandler({FilmeNaoEncontradoException .class })
	public ResponseEntity<Object> handleDataIntegrityViolationException(FilmeNaoEncontradoException ex,
			WebRequest request) {
		String mensagemUsr = messageSource.getMessage("filme.nao-encontrado", null,
				LocaleContextHolder.getLocale());
		String mensagemDev = ExceptionUtils.getRootCauseMessage(ex);
		List<Erro> erros = Arrays.asList(new Erro(mensagemUsr, mensagemDev));
		return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
	}
	
	@ExceptionHandler({FilmeDuplicadoException .class })
	public ResponseEntity<Object> handleDataIntegrityViolationException(FilmeDuplicadoException ex,
			WebRequest request) {
		String mensagemUsr = messageSource.getMessage("filme.duplicado", null,
				LocaleContextHolder.getLocale());
		String mensagemDev = ex.toString();
		List<Erro> erros = Arrays.asList(new Erro(mensagemUsr, mensagemDev));
		return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}
	
	@ExceptionHandler({FilmeSemEstoqueException .class })
	public ResponseEntity<Object> handleDataIntegrityViolationException(FilmeSemEstoqueException ex,
			WebRequest request) {
		String mensagemUsr = messageSource.getMessage("filme.sem-estoque", null,
				LocaleContextHolder.getLocale());
		String mensagemDev = ex.toString();
		List<Erro> erros = Arrays.asList(new Erro(mensagemUsr, mensagemDev));
		return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}
}

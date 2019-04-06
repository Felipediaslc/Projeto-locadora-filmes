package com.locadora.infra.genero;

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
import com.locadora.infra.genero.exception.GeneroDuplicadoException;
import com.locadora.infra.genero.exception.GeneroNaoEncontradoException;

/**
 * Classe responsável pelo tratamento de erros especificos a {@link Genero}
 * @author SOUSA, Taynar - Marco/2019
 * @since 1.0
 */
@ControllerAdvice
public class GeneroExceptionHandler{
	
	@Autowired
	MessageSource messageSource;
	
	@ExceptionHandler({GeneroNaoEncontradoException .class })
	public ResponseEntity<Object> handleGeneroNaoEncontradoException(GeneroNaoEncontradoException ex,
			WebRequest request) {
		String mensagemUsr = messageSource.getMessage("genero.nao-encontrado", null,
				LocaleContextHolder.getLocale());
		String mensagemDev = ex.toString();
		List<Erro> erros = Arrays.asList(new Erro(mensagemUsr, mensagemDev));
		return ResponseEntity.badRequest().body(erros);
	}
	
	@ExceptionHandler({GeneroDuplicadoException .class })
	public ResponseEntity<Object> handleGeneroNaoEncontradoException(GeneroDuplicadoException ex,
			WebRequest request) {
		String mensagemUsr = messageSource.getMessage("genero.duplicado", null,
				LocaleContextHolder.getLocale());
		String mensagemDev = ex.toString();
		List<Erro> erros = Arrays.asList(new Erro(mensagemUsr, mensagemDev));
		return ResponseEntity.badRequest().body(erros);
	}
	
}

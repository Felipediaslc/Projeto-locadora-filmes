package com.locadora.utils;

public class ClienteUtils {
	
	public static String validaCpf(String cpf) {
		String cpfFormatado;
		if (cpf.matches("\\d{3}.\\d{3}.\\d{3}-\\d{2}")) {
			cpfFormatado =  cpf;
		}
		cpfFormatado = cpf.substring(0, 3)
				+ "." + cpf.substring(3,6) 
				+ "." + cpf.substring(6,9) + "-"
				+ cpf.substring(9,11);
		
		return cpfFormatado;
	}
	
	public static String validaCep(String cep) {
		String cepFormatado = "";
		if (cep.matches("\\d{2}\\d{3}-\\d{3}")) {
			cepFormatado =  cep;
		}
		cepFormatado = cepFormatado.replace("-", "");
		cepFormatado = cepFormatado.substring(0, 4)	+
				"-" + cepFormatado.substring(4, 7);
		return cepFormatado;
	}

}

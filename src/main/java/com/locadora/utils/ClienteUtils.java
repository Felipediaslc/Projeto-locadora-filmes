package com.locadora.utils;

public class ClienteUtils {

	public static String validacpf(String cpf) {
		if (cpf.matches("\\d{3}.\\d{3}.\\d{3}-\\d{2}")) {
			return cpf;
		}
		String cpfFormatado = cpf.substring(0, 3) 
				+ "." + cpf.substring(3, 6) 
				+ "." + cpf.substring(6, 9) + "-"
				+ cpf.substring(9, 11);
		
		System.out.println(cpfFormatado);
		return cpfFormatado;
	}
	
	public static String validaCep(String cep) {
		if (cep.matches("\\d{2}.\\d{3}-\\d{3}")) {
			return cep;
		}
		String cepFormatado = cep.replace(".", "");
		cepFormatado = cepFormatado.replace("-", "");
		cepFormatado = cepFormatado.substring(0, 2) 
				+ "." + cepFormatado.substring(2, 5) 
				+ "-" + cepFormatado.substring(5, 8);
		System.out.println(cepFormatado);
		return cepFormatado;
	}

}

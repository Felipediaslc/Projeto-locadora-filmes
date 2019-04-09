package com.locadora.infra.locacao.filter;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

public class LocacaoFilter {
		@DateTimeFormat(pattern = "yyy-MM-dd")
		private LocalDate dataRealizacaoDe;
		@DateTimeFormat(pattern = "yyy-MM-dd")
		private LocalDate dataRealizacaoAte;
		@DateTimeFormat(pattern = "yyy-MM-dd")
		private LocalDate dataDevolucaoDe;
		@DateTimeFormat(pattern = "yyy-MM-dd")
		private LocalDate dataDevolucaoAte;
		
		
		public LocalDate getDataRealizacaoDe() {
			return dataRealizacaoDe;
		}
		public void setDataRealizacaoDe(LocalDate dataRealizacaoDe) {
			this.dataRealizacaoDe = dataRealizacaoDe;
		}
		public LocalDate getDataRealizacaoAte() {
			return dataRealizacaoAte;
		}
		public void setDataRealizacaoAte(LocalDate dataRealizacaoAte) {
			this.dataRealizacaoAte = dataRealizacaoAte;
		}
		public LocalDate getDataDevolucaoDe() {
			return dataDevolucaoDe;
		}
		public void setDataDevolucaoDe(LocalDate dataDevolucaoDe) {
			this.dataDevolucaoDe = dataDevolucaoDe;
		}
		public LocalDate getDataDevolucaoAte() {
			return dataDevolucaoAte;
		}
		public void setDataDevolucaoAte(LocalDate dataDevolucaoAte) {
			this.dataDevolucaoAte = dataDevolucaoAte;
		}
		
}

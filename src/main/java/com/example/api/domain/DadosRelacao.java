package com.example.api.domain;

public class DadosRelacao {
	
	private String cep;
	private Long idCustomer;
	
	public DadosRelacao(String cep, Long idCustomer) {
		this.cep = cep;
		this.idCustomer = idCustomer;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public Long getIdCustomer() {
		return idCustomer;
	}

	public void setIdCustomer(Long idCustomer) {
		this.idCustomer = idCustomer;
	}
}

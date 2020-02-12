package com.example.api.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.example.api.domain.exceptions.DomainExceptions;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name= "endereco")
public class Endereco {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@JsonProperty
	private Long id;
	
	@JsonProperty
	private String logradouro;
	
	@JsonProperty
	private String cep;
	
	@JsonProperty
	private String complemento;
	
	@JsonProperty
	private String bairro;
	
	@JsonProperty
	private String localidade;
	
	@JsonProperty
	private String uf;
	
	@JsonProperty
	private String unidade;
	
	@JsonProperty
	private String ibge;
	
	@JsonProperty
	private String gia;
	
	@ManyToOne
	@JoinColumn(name= "customer_id")
	private Customer customer;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getLocalidade() {
		return localidade;
	}

	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getUnidade() {
		return unidade;
	}

	public void setUnidade(String unidade) {
		this.unidade = unidade;
	}

	public String getIbge() {
		return ibge;
	}

	public void setIbge(String ibge) {
		this.ibge = ibge;
	}

	public String getGia() {
		return gia;
	}

	public void setGia(String gia) {
		this.gia = gia;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	

//	public void setCep(String cep) {
//		if (cep == null || cep.length() > 8 || cep.length() < 8) {
//			throw new DomainExceptions("Dados do campo cep não está correto");
//		} else {
//			this.cep = cep;
//		}
//	}
	
	
}

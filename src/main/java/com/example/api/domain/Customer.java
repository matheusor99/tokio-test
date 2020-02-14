package com.example.api.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import com.example.api.domain.exceptions.DomainExceptions;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Customer implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	@NotEmpty
	private String name;

	@Column(nullable = false)
	@NotEmpty
	@Email
	private String email;
	
	@OneToMany(mappedBy="customer")
	@JsonManagedReference
	List<Endereco> enderecos = new ArrayList<>(); 

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		if (name == null) {
			throw new DomainExceptions("nome do usuario não inserido");
		}
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		if (email == null) {
			throw new DomainExceptions("nome do usuario não inserido");
		}
		this.email = email;
	}
	
	public List<Endereco> getEnderecos() {
		return this.enderecos;
	}
	
	public void addEndereco(Endereco endereco) {
		if (endereco == null) {
			throw new DomainExceptions("Endereço invalido");
		}
		this.enderecos.add(endereco);
	}

}

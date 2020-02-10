package com.example.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.api.domain.Customer;
import com.example.api.repository.CustomerRepository;

@Service
public class CustomerService {

	private CustomerRepository repository;

	@Autowired
	public CustomerService(CustomerRepository repository) {
		this.repository = repository;
	}

	public List<Customer> findAll() {
		return repository.findAllByOrderByNameAsc();
	}

	public Optional<Customer> findById(Long id) {
		return repository.findById(id);
	}
	
	public String save(Customer customer) {
		if (customer.getName().isEmpty()) {
			return "O campo nome não pode ser vazio";
		} else if (customer.getEmail().isEmpty()) {
			return "O campo email não pode ser vazio";
		} else {
			repository.save(customer);
			return "Cliente cadastrado com sucesso";
		}
	}
	
	public String update(Customer customer) {
		if (customer.getId() == null) {
			return "Cliente não identificado ou não existe";
		} else if (customer.getName().isEmpty()) {
			return "O campo nome não pode ser vazio";
		} else if (customer.getEmail().isEmpty()) {
			return "O campo email não pode ser vazio";
		}
		else {
			repository.save(customer);
			return "Cliente atualizado com sucesso";
		}
	}
	
	public String delete(Long id) {
		repository.deleteById(id);
		return "Cliente do identificador " + id + " foi deletado";
	}

}

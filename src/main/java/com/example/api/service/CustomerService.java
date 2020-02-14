package com.example.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.api.domain.Customer;
import com.example.api.domain.exceptions.DomainExceptions;
import com.example.api.repository.CustomerRepository;

@Service
public class CustomerService {

	private CustomerRepository repository;

	@Autowired
	public CustomerService(CustomerRepository repository) {
		this.repository = repository;
	}

	public List<Customer> findAll(Pageable pageable) {
		List<Customer> result = repository.findAllByOrderByNameAsc(pageable).getContent();
		return result;
		
	}

	public Optional<Customer> findById(Long id) {
		return repository.findById(id);
	}
	
	public Customer save(Customer customer) {
		try {
			return repository.save(customer);
		} catch (DomainExceptions e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return null;
		}
	}
	
	public Customer update(Customer customer) {
		if(customer.getId() != null) {
			if(repository.findById(customer.getId()).get() != null) {
				return repository.save(customer);
			}
			return null;
		}
		return null;
	}
	
	public String delete(Long id) {
		repository.deleteById(id);
		return "Cliente do identificador " + id + " foi deletado";
	}

}

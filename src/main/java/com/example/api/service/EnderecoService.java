package com.example.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.api.domain.Customer;
import com.example.api.domain.Endereco;
import com.example.api.repository.CustomerRepository;
import com.example.api.repository.EnderecoRepository;

@Service
public class EnderecoService {
	
	private final EnderecoRepository repository;
	private final CustomerService customerService;
	private final RestTemplate restTemplate = new RestTemplate();
	
	@Autowired
	public EnderecoService(EnderecoRepository repository, CustomerService customerService) {
		this.repository = repository;
		this.customerService= customerService;
	}
	
	public List<Endereco> findAll() {
		return repository.findAll();
	}
	
	public Endereco search(String cep) {
		String url = "https://viacep.com.br/ws/" + cep + "/json/";
		ResponseEntity<Endereco> result =  restTemplate.getForEntity(url, Endereco.class);
		return result.getBody();
	}
	
	public String save(String cep, Long idCustomer) {
		Customer customer = customerService.findById(idCustomer).get();
		Endereco endereco = search(cep);
		endereco.setCustomer(customer);
		repository.save(endereco);
		return "Endereço inserido com sucesso";
	}
}

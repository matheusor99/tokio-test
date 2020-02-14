package com.example.api.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.api.domain.Customer;
import com.example.api.domain.Endereco;
import com.example.api.domain.exceptions.DomainExceptions;
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
	
	public Endereco save(String cep, Long idCustomer) {
		try {
			Customer customer = customerService.findById(idCustomer).get();
			Endereco endereco = search(cep);
			List<Endereco> validacaoEndercoExistente = customer.getEnderecos().stream()
					.filter((e) -> e.getCep().equals(endereco.getCep())).collect(Collectors.toList());
			if (validacaoEndercoExistente.isEmpty()) {
				customer.addEndereco(endereco);
				endereco.setCustomer(customer);
				Endereco result = repository.save(endereco);
				return result;
			} else {
				return validacaoEndercoExistente.get(0);
			}
		} catch (DomainExceptions e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return null;
		}
	}
	
	
	public String delete(Long id) {
		if (repository.findById(id).get() != null) {
			repository.deleteById(id);
			return "Endereço excluido com sucesso";
		}
		return "Endereço inexistente";
	}
}

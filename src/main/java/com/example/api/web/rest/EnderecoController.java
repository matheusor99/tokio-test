package com.example.api.web.rest;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.api.domain.DadosRelacao;
import com.example.api.domain.Endereco;
import com.example.api.service.EnderecoService;

@RestController
@RequestMapping("/enderecos")
public class EnderecoController {
	
	private final EnderecoService service;
	
	public EnderecoController(EnderecoService service) {
		this.service = service;
	}
	
	@GetMapping
	public List<Endereco> findAll() {
		return service.findAll();
	}
	
	@GetMapping("/{cep}")
	public ResponseEntity<Endereco> search(@PathVariable("cep") String cep) {
		Endereco result = service.search(cep);
		return ResponseEntity.ok(result);
	}
	
	@PostMapping
	public ResponseEntity<Endereco> save(@RequestBody DadosRelacao dados) {
		Endereco result = service.save(dados.getCep(), dados.getIdCustomer());
		return ResponseEntity.status(HttpStatus.CREATED).body(result);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> delete(@PathVariable("id") Long id) {
		String result = service.delete(id);
		return ResponseEntity.status(HttpStatus.OK).body(result);
	}
	
}

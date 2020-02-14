package com.example.api.web.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.api.domain.Customer;
import com.example.api.service.CustomerService;

@RestController
@RequestMapping("/customers")
public class CustomerController {

	private CustomerService service;

	@Autowired
	public CustomerController(CustomerService service) {
		this.service = service;
	}

	@GetMapping
	public List<Customer> findAll(Pageable pageable) {
		return service.findAll(pageable);
	}

	@GetMapping("/{id}")
	public Customer findById(@PathVariable Long id) {
		return service.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found"));
	}
	
	@PostMapping
	public ResponseEntity<Customer> save(@RequestBody Customer customer) {
		Customer result = service.save(customer);
		return ResponseEntity.status(HttpStatus.CREATED).body(result);
	}
	
	@PutMapping
	public ResponseEntity<Customer> update(@RequestBody Customer customer) {
		Customer result = service.update(customer);
		return ResponseEntity.ok(result);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> delete(@PathVariable("id") Long id) {
		String result = service.delete(id);
		return ResponseEntity.ok(result);
	}
}

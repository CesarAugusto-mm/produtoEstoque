package com.produto.Controller;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
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
import com.produto.Repository.SetorRepository;
import com.produto.model.Setor;

@RestController
@RequestMapping("/setor")
public class SetorController {

	@Autowired
	private SetorRepository repository;
	
	@GetMapping
	public ResponseEntity<List<Setor>> get() {
		return ResponseEntity.ok(repository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Setor> getById(@PathVariable int id) {
		Optional<Setor> setorExist = repository.findById(id);
		
		if (setorExist.isEmpty())
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		
		return ResponseEntity.ok(setorExist.get());
	}
	
	@PostMapping
	public ResponseEntity<Setor> post(@RequestBody Setor setor) {
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(setor));
		
	}
	
	@PutMapping
	public ResponseEntity<Setor> put(@RequestBody Setor setor) {
		return ResponseEntity.status(HttpStatus.OK).body(repository.save(setor));
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable int id) {
		repository.deleteById(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
}

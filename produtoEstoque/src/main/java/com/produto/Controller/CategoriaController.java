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
import com.produto.Repository.CategoriaRepository;
import com.produto.model.Categoria;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {
	
	@Autowired
	private CategoriaRepository repository;
	
	@GetMapping
	public ResponseEntity<List<Categoria>> get() {
		return ResponseEntity.ok(repository.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Categoria> getById(@PathVariable int id) {
		Optional<Categoria> categoriaExist = repository.findById(id);
		
		if (categoriaExist.isEmpty())
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		
		return ResponseEntity.ok(categoriaExist.get());
	}
	
	@PostMapping
	public ResponseEntity<Categoria> post(@RequestBody Categoria categoria) {
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(categoria));
	}
	
	@PutMapping
	public ResponseEntity<Categoria> put(@RequestBody Categoria categoria) {
		return ResponseEntity.status(HttpStatus.OK).body(repository.save(categoria));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable int id) {
		repository.deleteById(id);
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
	
}

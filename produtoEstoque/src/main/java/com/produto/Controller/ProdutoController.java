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
import com.produto.Repository.ProdutoRepository;
import com.produto.model.Produto;

@RestController
@RequestMapping("/produto")
public class ProdutoController {
	
	@Autowired
	private ProdutoRepository repository;
	
	@GetMapping
	public ResponseEntity<List<Produto>> get() {
		return ResponseEntity.ok(repository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Produto> getById(@PathVariable int id) {
		Optional<Produto> produtoExist = repository.findById(id);
		
		if (produtoExist.isEmpty())
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		
		return ResponseEntity.ok(produtoExist.get());
	}
	
	@PostMapping
	public ResponseEntity<Produto> post(@RequestBody Produto produto) {
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(produto));
	}
	
	@PutMapping
	public ResponseEntity<Produto> put(@RequestBody Produto produto) {
		return ResponseEntity.status(HttpStatus.OK).body(repository.save(produto));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable int id) {
		repository.deleteById(id);
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}

}

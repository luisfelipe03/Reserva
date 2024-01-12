package br.com.reserva.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.reserva.data.vo.AlunoVO;
import br.com.reserva.services.AlunoService;


@RestController
@RequestMapping("api/aluno")
public class AlunoController {
	
	@Autowired
	AlunoService service;
	
	
	@GetMapping
	public List<AlunoVO> getTodosAlunos() {
		return service.findAll();
	}
	

	@GetMapping("/{id}")
	public AlunoVO getAluno(@PathVariable(value = "id") Long id) {
		return service.findById(id);
	}
	
	@PostMapping
	public AlunoVO create(@RequestBody AlunoVO aluno) {
		return service.create(aluno);
	}
	
	@PutMapping()
	public AlunoVO update(@RequestBody AlunoVO aluno) {
		return service.update(aluno);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}

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

import br.com.reserva.data.vo.ProfessorVO;
import br.com.reserva.services.ProfessorService;

@RestController
@RequestMapping("/api/professor")
public class ProfessorController {
	
	@Autowired
	ProfessorService service;
	
	@GetMapping
	public List<ProfessorVO> getTodosProfessores() {
		return service.findAll();
	}
	
	@GetMapping("/{id}")
	public ProfessorVO getProfessor(@PathVariable(value = "id") Long id) {
		return service.findById(id);
	}
	
	@PostMapping
	public ProfessorVO create(@RequestBody ProfessorVO professor) {
		return service.create(professor);
	}
	
	@PutMapping()
	public ProfessorVO update(@RequestBody ProfessorVO professor) {
		return service.update(professor);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

}

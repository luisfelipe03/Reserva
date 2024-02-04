package br.com.reserva.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.reserva.data.vo.ProfessorVO;
import br.com.reserva.data.vo.TurmaVO;
import br.com.reserva.facade.Facade;

@RestController
@RequestMapping("/api/professor")
public class ProfessorController {
	
	@Autowired
	Facade facade;
	
	@GetMapping
	@ResponseStatus(code = HttpStatus.OK)
	public ResponseEntity<List<ProfessorVO>> getTodosProfessores() {
		return ResponseEntity.status(HttpStatus.OK).body(facade.getAllProfessor());
	}
	
	@GetMapping("/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public ProfessorVO getProfessor(@PathVariable(value = "id") Long id) {
		return facade.getByIdProfessor(id);
	}
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public ProfessorVO create(@RequestBody ProfessorVO professor) {
		return facade.saveProfessor(professor);
	}
	
	@PostMapping("/{id}/cadastraTurma")
	@ResponseStatus(code = HttpStatus.CREATED)
	public ProfessorVO createTurma(@PathVariable(value = "id") Long id, @RequestBody TurmaVO turma) {
		facade.createTurma(id, turma);
		return facade.getByIdProfessor(id);
	}
	
	@PutMapping
	@ResponseStatus(code = HttpStatus.OK)
	public ProfessorVO update(@RequestBody ProfessorVO professor) {
		return facade.updateProfessor(professor);
	}
	
	@PatchMapping
	public void updateTurma(@RequestBody ProfessorVO professor) {
		facade.updateTurma(professor);
	}
	
	@DeleteMapping(value = "/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void delete(@PathVariable(value = "id") Long id) {
		facade.deleteProfessor(id);
	}

}

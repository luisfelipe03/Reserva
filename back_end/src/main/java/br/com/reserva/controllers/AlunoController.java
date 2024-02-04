package br.com.reserva.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.reserva.data.vo.AlunoVO;
import br.com.reserva.facade.Facade;


@RestController
@RequestMapping("api/aluno")
public class AlunoController {
	
	@Autowired
	Facade facade;
	
	
	@GetMapping
	@ResponseStatus(code = HttpStatus.OK)
	public List<AlunoVO> getTodosAlunos() {
		return facade.getAllAlunos();
	}
	

	@GetMapping("/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public AlunoVO getAluno(@PathVariable(value = "id") Long id) {
		return facade.getByIdAluno(id);
	}
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public AlunoVO create(@RequestBody AlunoVO aluno) {
		return facade.saveAluno(aluno);
	}
	
	@PutMapping()
	@ResponseStatus(code = HttpStatus.OK)
	public AlunoVO update(@RequestBody AlunoVO aluno) {
		return facade.updateAluno(aluno);
	}
	
	@DeleteMapping(value = "/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void delete(@PathVariable(value = "id") Long id) {
		facade.deleteAluno(id);
	}
}

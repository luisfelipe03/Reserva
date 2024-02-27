package br.com.reserva.controllers;

import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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

	@GetMapping(produces={"application/json"})
	@Operation(summary = "Busca todos os professores", description = "Busca todos os professores cadastrados no banco de dados",
			tags = {"Professores"},
			responses = {
					@ApiResponse(description = "Success", responseCode = "200",
							content = @Content(
									mediaType = "application/json",
									array = @ArraySchema(schema = @Schema(implementation = ProfessorVO.class))
							)),
					@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
			}
	)
	@ResponseStatus(code = HttpStatus.OK)
	public ResponseEntity<List<ProfessorVO>> getTodosProfessores() {
		return ResponseEntity.status(HttpStatus.OK).body(facade.getAllProfessor());
	}

	@GetMapping(value = "/{id}", produces={"application/json"})
	@Operation(summary = "Busca um professor por ID", description = "Busca um professor específico cadastrado no banco de dados pelo seu ID",
			tags = {"Professores"},
			responses = {
					@ApiResponse(description = "Success", responseCode = "200",
							content = @Content(
									mediaType = "application/json",
									schema = @Schema(implementation = ProfessorVO.class)
							)),
					@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
					@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
			}
	)
	@ResponseStatus(code = HttpStatus.OK)
	public ProfessorVO getProfessor(@PathVariable(value = "id") Long id) {
		return facade.getByIdProfessor(id);
	}

	@GetMapping(value = "/{id}/turmas", produces={"application/json"})
	@Operation(summary = "Busca todas as turmas de um professor", description = "Busca todas as turmas associadas a um professor específico",
			tags = {"Professores", "Turmas"},
			responses = {
					@ApiResponse(description = "Success", responseCode = "200",
							content = @Content(
									mediaType = "application/json",
									array = @ArraySchema(schema = @Schema(implementation = TurmaVO.class))
							)),
					@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
					@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
			}
	)
	@ResponseStatus(code = HttpStatus.OK)
	public List<TurmaVO> getTurmas(@PathVariable(value = "id") Long id) {
		return facade.getTurmas(id);
	}

	@PostMapping(produces={"application/json"}, consumes={"application/json"})
	@Operation(summary = "Cria um novo professor", description = "Cadastra um novo professor no banco de dados",
			tags = {"Professores"},
			responses = {
					@ApiResponse(description = "Created", responseCode = "201",
							content = @Content(
									mediaType = "application/json",
									schema = @Schema(implementation = ProfessorVO.class)
							)),
					@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
					@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
			}
	)
	@ResponseStatus(code = HttpStatus.CREATED)
	public ProfessorVO createProfessor(@RequestBody ProfessorVO professor) {
		return facade.saveProfessor(professor);
	}

	@PostMapping(value = "/{id}/cadastraTurma", consumes = "application/json")
	@Operation(summary = "Cadastra uma turma para um professor", description = "Cadastra uma nova turma associada a um professor específico",
			tags = {"Professores"},
			responses = {
					@ApiResponse(description = "Created", responseCode = "201"),
					@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
					@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
					@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
			}
	)
	@ResponseStatus(code = HttpStatus.CREATED)
	public ProfessorVO createTurma(@PathVariable(value = "id") Long id, @RequestBody TurmaVO turma) {
		facade.createTurma(id, turma);
		return facade.getByIdProfessor(id);
	}

	@PutMapping(produces={"application/json"}, consumes={"application/json"})
	@Operation(summary = "Atualiza um professor", description = "Atualiza os dados de um professor cadastrado no banco de dados",
			tags = {"Professores"},
			responses = {
					@ApiResponse(description = "Success", responseCode = "200",
							content = @Content(
									mediaType = "application/json",
									schema = @Schema(implementation = ProfessorVO.class)
							)),
					@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
					@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
					@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
			}
	)
	@ResponseStatus(code = HttpStatus.OK)
	public ProfessorVO updateProfessor(@RequestBody ProfessorVO professor) {
		return facade.updateProfessor(professor);
	}

	@PatchMapping(produces={"application/json"}, consumes={"application/json"})
	@Operation(summary = "Atualiza turma de um professor", description = "Atualiza informações de uma turma associada a um professor",
			tags = {"Professores", "Turmas"},
			responses = {
					@ApiResponse(description = "Success", responseCode = "200"),
					@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
					@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
					@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
			}
	)
	@ResponseStatus(code = HttpStatus.OK)
	public void updateTurma(@RequestBody ProfessorVO professor) {
		facade.updateTurma(professor);
	}

	@DeleteMapping(value = "/{id}")
	@Operation(summary = "Deleta um professor", description = "Remove um professor cadastrado no banco de dados pelo seu ID",
			tags = {"Professores"},
			responses = {
					@ApiResponse(description = "No Content", responseCode = "204", content = @Content),
					@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
					@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
					@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
			}
	)
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void deleteProfessor(@PathVariable(value = "id") Long id) {
		facade.deleteProfessor(id);
	}
	@DeleteMapping(value = "/{idProf}/deletaTurma/{idTurma}")
	public void deleteTurma(@PathVariable(value = "idTurma") Long idTurma, @PathVariable(value = "idProf") Long idProf) {
		facade.deleteTurma(idTurma,idProf);
	}

}

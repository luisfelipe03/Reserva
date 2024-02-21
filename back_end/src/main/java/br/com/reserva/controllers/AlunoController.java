package br.com.reserva.controllers;

import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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


	@GetMapping(produces={"application/json"})
	@Operation(summary = "Busca todos os alunos", description = "Busca todos os alunos cadastrados no banco de dados",
			tags = {"Alunos"},
			responses = {
					@ApiResponse(description = "Success", responseCode = "200",
							content = @Content(
									mediaType = "application/json",
									array = @ArraySchema(schema = @Schema(implementation = AlunoVO.class))
							)),
					@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
			}
	)
	@ResponseStatus(code = HttpStatus.OK)
	public List<AlunoVO> getTodosAlunos() {
		return facade.getAllAlunos();
	}


	@GetMapping(value = "/{id}", produces={"application/json"})
	@Operation(summary = "Busca um aluno por ID", description = "Busca um aluno específico cadastrado no banco de dados pelo seu ID",
			tags = {"Alunos"},
			responses = {
					@ApiResponse(description = "Success", responseCode = "200",
							content = @Content(
									mediaType = "application/json",
									schema = @Schema(implementation = AlunoVO.class)
							)),
					@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
					@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
			}
	)
	@ResponseStatus(code = HttpStatus.OK)
	public AlunoVO getAlunoId(@PathVariable(value = "id") Long id) {
		return facade.getByIdAluno(id);
	}

	@PostMapping(produces={"application/json"}, consumes={"application/json"})
	@Operation(summary = "Cria um novo aluno", description = "Cadastra um novo aluno no banco de dados",
			tags = {"Alunos"},
			responses = {
					@ApiResponse(description = "Created", responseCode = "201",
							content = @Content(
									mediaType = "application/json",
									schema = @Schema(implementation = AlunoVO.class)
							)),
					@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
					@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
			}
	)
	@ResponseStatus(code = HttpStatus.CREATED)
	public AlunoVO create(@RequestBody AlunoVO aluno) {
		return facade.saveAluno(aluno);
	}

	@PutMapping(produces={"application/json"}, consumes={"application/json"})
	@Operation(summary = "Atualiza um aluno", description = "Atualiza os dados de um aluno cadastrado no banco de dados",
			tags = {"Alunos"},
			responses = {
					@ApiResponse(description = "Success", responseCode = "200",
							content = @Content(
									mediaType = "application/json",
									schema = @Schema(implementation = AlunoVO.class)
							)),
					@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
					@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
					@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
			}
	)
	@ResponseStatus(code = HttpStatus.OK)
	public AlunoVO update(@RequestBody AlunoVO aluno) {
		return facade.updateAluno(aluno);
	}

	@DeleteMapping(value = "/{id}")
	@Operation(summary = "Deleta um aluno", description = "Remove um aluno cadastrado no banco de dados pelo seu ID",
			tags = {"Alunos"},
			responses = {
					@ApiResponse(description = "No Content", responseCode = "204", content = @Content),
					@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
					@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
					@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
			}
	)
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void delete(@PathVariable(value = "id") Long id) {
		facade.deleteAluno(id);
	}
}

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

import br.com.reserva.data.vo.LaboratorioVO;
import br.com.reserva.facade.Facade;

@RestController
@RequestMapping("api/laboratorio")
public class LaboratorioController {
	
	@Autowired
	Facade facade;

	@GetMapping(produces={"application/json"})
	@Operation(summary = "Busca todos os laboratórios", description = "Busca todos os laboratórios cadastrados no banco de dados",
			tags = {"Laboratórios"},
			responses = {
					@ApiResponse(description = "Success", responseCode = "200",
							content = @Content(
									mediaType = "application/json",
									array = @ArraySchema(schema = @Schema(implementation = LaboratorioVO.class))
							)),
					@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
			}
	)
	@ResponseStatus(code = HttpStatus.OK)
	public List<LaboratorioVO> getTodosLabs() {
		return facade.getAllLab();
	}

	@GetMapping(value = "/{id}", produces={"application/json"})
	@Operation(summary = "Busca um laboratório por ID", description = "Busca um laboratório específico cadastrado no banco de dados pelo seu ID",
			tags = {"Laboratórios"},
			responses = {
					@ApiResponse(description = "Success", responseCode = "200",
							content = @Content(
									mediaType = "application/json",
									schema = @Schema(implementation = LaboratorioVO.class)
							)),
					@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
					@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
			}
	)
	@ResponseStatus(code = HttpStatus.OK)
	public LaboratorioVO getLab(@PathVariable(value = "id") long id) {
		return facade.getByIdLab(id);
	}

	@PostMapping(produces={"application/json"}, consumes={"application/json"})
	@Operation(summary = "Cria um novo laboratório", description = "Cadastra um novo laboratório no banco de dados",
			tags = {"Laboratórios"},
			responses = {
					@ApiResponse(description = "Created", responseCode = "201",
							content = @Content(
									mediaType = "application/json",
									schema = @Schema(implementation = LaboratorioVO.class)
							)),
					@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
					@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
			}
	)
	@ResponseStatus(code = HttpStatus.CREATED)
	public LaboratorioVO createLab(@RequestBody LaboratorioVO lab) {
		return facade.createLab(lab);
	}

	@PutMapping(produces={"application/json"}, consumes={"application/json"})
	@Operation(summary = "Atualiza um laboratório", description = "Atualiza os dados de um laboratório cadastrado no banco de dados",
			tags = {"Laboratórios"},
			responses = {
					@ApiResponse(description = "Success", responseCode = "200",
							content = @Content(
									mediaType = "application/json",
									schema = @Schema(implementation = LaboratorioVO.class)
							)),
					@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
					@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
					@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
			}
	)
	@ResponseStatus(code = HttpStatus.OK)
	public LaboratorioVO updateLab(@RequestBody LaboratorioVO lab) {
		return facade.updateLab(lab);
	}

	@DeleteMapping(value = "/{id}")
	@Operation(summary = "Deleta um laboratório", description = "Remove um laboratório cadastrado no banco de dados pelo seu ID",
			tags = {"Laboratórios"},
			responses = {
					@ApiResponse(description = "No Content", responseCode = "204", content = @Content),
					@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
					@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
					@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
			}
	)
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void deleteLab(@PathVariable(value = "id") Long id) {
		facade.deleteLab(id);
	}

}

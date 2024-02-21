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

import br.com.reserva.data.vo.AdministradorVO;
import br.com.reserva.facade.Facade;

@RestController
@RequestMapping("api/adm")
public class AdministradorController {
	
	@Autowired
	Facade facade;

	@GetMapping(produces={"application/json"})
	@Operation(summary = "Busca todos os administradores", description = "Busca todos os administradores cadastrados no banco de dados",
			tags = {"Administradores"},
			responses = {
					@ApiResponse(description = "Success", responseCode = "200",
							content = @Content(
									mediaType = "application/json",
									array = @ArraySchema(schema = @Schema(implementation = AdministradorVO.class))
							)),
					@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
			}
	)
	@ResponseStatus(code = HttpStatus.OK)
	public List<AdministradorVO> getTodosAdm() {
		return facade.getAllAdm();
	}

	@GetMapping(value = "/{id}", produces={"application/json"})
	@Operation(summary = "Busca um administrador por ID", description = "Busca um administrador específico cadastrado no banco de dados pelo seu ID",
			tags = {"Administradores"},
			responses = {
					@ApiResponse(description = "Success", responseCode = "200",
							content = @Content(
									mediaType = "application/json",
									schema = @Schema(implementation = AdministradorVO.class)
							)),
					@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
					@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
			}
	)
	@ResponseStatus(code = HttpStatus.OK)
	public AdministradorVO getAdmId(@PathVariable(value = "id") Long id) {
		return facade.getByIdAdm(id);
	}

	@PostMapping(produces={"application/json"}, consumes={"application/json"})
	@Operation(summary = "Cria um novo administrador", description = "Cadastra um novo administrador no banco de dados",
			tags = {"Administradores"},
			responses = {
					@ApiResponse(description = "Created", responseCode = "201",
							content = @Content(
									mediaType = "application/json",
									schema = @Schema(implementation = AdministradorVO.class)
							)),
					@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
					@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
			}
	)
	@ResponseStatus(code = HttpStatus.CREATED)
	public AdministradorVO create(@RequestBody AdministradorVO administradorVO) {
		return facade.createAdm(administradorVO);
	}

	@PutMapping(produces={"application/json"}, consumes={"application/json"})
	@Operation(summary = "Atualiza um administrador", description = "Atualiza os dados de um administrador cadastrado no banco de dados",
			tags = {"Administradores"},
			responses = {
					@ApiResponse(description = "Success", responseCode = "200",
							content = @Content(
									mediaType = "application/json",
									schema = @Schema(implementation = AdministradorVO.class)
							)),
					@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
					@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
					@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
			}
	)
	public AdministradorVO update(@RequestBody AdministradorVO administradorVO) {
		return facade.updateAdm(administradorVO);
	}

	@DeleteMapping("/{id}")
	@Operation(summary = "Deleta um administrador", description = "Remove um administrador cadastrado no banco de dados pelo seu ID",
			tags = {"Administradores"},
			responses = {
					@ApiResponse(description = "No Content", responseCode = "204", content = @Content),
					@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
					@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
					@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
			}
	)
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void delete(@PathVariable(value = "id") Long id) {
		facade.deleteAdm(id);
	}

}

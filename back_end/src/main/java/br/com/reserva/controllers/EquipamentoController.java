package br.com.reserva.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
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

import br.com.reserva.data.vo.EquipamentoVO;
import br.com.reserva.facade.Facade;
import br.com.reserva.utils.StatusFuncionamento;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("api/equipamento")
@Tag(name = "Equipamentos", description = "Endpoints para gerenciamento dos equipamentos da UFAPE.")
@CrossOrigin(origins = "*")
public class EquipamentoController {

	@Autowired
	Facade facade;

	@GetMapping(produces={"application/json"})
	@Operation(summary = "Busca todos os equipamentos", description = "Busca todos os equipamentos cadastrados no banco de dados",
			tags = {"Equipamentos"},
			responses = {
					@ApiResponse(description = "Success", responseCode = "200",
							content = {
									@Content(
											mediaType = "application/json",
											array = @ArraySchema(schema = @Schema(implementation = EquipamentoVO.class))
									)
							}),
					@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
					@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
					@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
					@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
			}
	)
	@ResponseStatus(code = HttpStatus.OK)
	public List<EquipamentoVO> getTodosEquip() {
		return facade.getAllEquip();
	}

	@GetMapping(value = "/{id}", produces={"application/json"})
	@Operation(summary = "Busca um equipamento por ID", description = "Busca um equipamento específico cadastrado no banco de dados pelo seu ID",
			tags = {"Equipamentos"},
			responses = {
					@ApiResponse(description = "Success", responseCode = "200",
							content = @Content(
									mediaType = "application/json",
									schema = @Schema(implementation = EquipamentoVO.class)
							)),
					@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
					@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
					@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
					@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
			}
	)
	@ResponseStatus(code = HttpStatus.OK)
	public EquipamentoVO getEquip(@PathVariable(value = "id") long id) {
		return facade.getByIdEquip(id);
	}

	@PostMapping(produces={"application/json"}, consumes={"application/json"})
	@Operation(summary = "Cria um novo equipamento", description = "Cadastra um novo equipamento no banco de dados",
			tags = {"Equipamentos"},
			responses = {
					@ApiResponse(description = "Created", responseCode = "201",
							content = @Content(
									mediaType = "application/json",
									schema = @Schema(implementation = EquipamentoVO.class)
							)),
					@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
					@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
					@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
			}
	)
	@ResponseStatus(code = HttpStatus.CREATED)
	public EquipamentoVO create(@RequestBody EquipamentoVO equip) {
		return facade.createEquip(equip);
	}

	@PutMapping(produces={"application/json"}, consumes={"application/json"})
	@Operation(summary = "Atualiza um equipamento", description = "Atualiza os dados de um equipamento cadastrado no banco de dados",
			tags = {"Equipamentos"},
			responses = {
					@ApiResponse(description = "Success", responseCode = "200",
							content = @Content(
									mediaType = "application/json",
									schema = @Schema(implementation = EquipamentoVO.class)
							)),
					@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
					@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
					@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
					@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
			}
	)
	@ResponseStatus(code = HttpStatus.OK)
	public EquipamentoVO update(@RequestBody EquipamentoVO equip) {
		return facade.updateEquip(equip);
	}

	@DeleteMapping("/{id}")
	@Operation(summary = "Deleta um equipamento", description = "Remove um equipamento cadastrado no banco de dados pelo seu ID",
			tags = {"Equipamentos"},
			responses = {
					@ApiResponse(description = "No Content", responseCode = "204", content = @Content),
					@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
					@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
					@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
					@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
			}
	)
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void delete(@PathVariable(value = "id") long id) {
		facade.deleteEquip(id);
	}

	@PatchMapping(value = "/{id}/{status}", produces={"application/json"})
	@Operation(summary = "Atualiza o status de um equipamento", description = "Atualiza o status de um equipamento cadastrado no banco de dados",
			tags = {"Equipamentos"},
			responses = {
					@ApiResponse(description = "Success", responseCode = "200",
							content = @Content(
									mediaType = "application/json",
									schema = @Schema(implementation = EquipamentoVO.class)
							)),
					@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
					@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
					@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
					@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
			}
	)
	@ResponseStatus(code = HttpStatus.OK)
	public EquipamentoVO updateStatusEquip(@PathVariable(value = "id") long id, @PathVariable(value = "status") String status) {
		StatusFuncionamento statusFuncionamento = StatusFuncionamento.valueOf(status);
		return facade.updateStatusEquip(id, statusFuncionamento);
	}
}

package br.com.reserva.controllers;

import java.util.List;

import br.com.reserva.utils.StatusReserva;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import br.com.reserva.data.vo.ReservaVO;
import br.com.reserva.facade.Facade;

@RestController
@RequestMapping("/api/reserva")
@CrossOrigin(origins = "*")
@Tag(name = "Reserva", description = "Endpoints para gerenciamento das reserva da UFAPE.")
public class ReservaController {
	
	@Autowired
	Facade facade;

	@GetMapping(produces = "application/json")
	@Operation(summary = "Obtém todas as reservas", description = "Retorna uma lista de todas as reservas cadastradas",
			responses = {
					@ApiResponse(responseCode = "200", description = "Operação bem-sucedida",
							content = @Content(mediaType = "application/json",
									schema = @Schema(implementation = ReservaVO.class))),
					@ApiResponse(responseCode = "500", description = "Erro interno no servidor")
			})
	@ResponseStatus(code = HttpStatus.OK)
	public List<ReservaVO> getTotasReservas() {
		return facade.getAllReservas();
	}

	@GetMapping(value = "/responsavel/{id}", produces = "application/json")
	@Operation(summary = "Obtém reservas por responsável", description = "Retorna uma lista de reservas com base no ID do responsável fornecido",
			responses = {
					@ApiResponse(responseCode = "200", description = "Operação bem-sucedida",
							content = @Content(mediaType = "application/json",
									schema = @Schema(implementation = ReservaVO.class))),
					@ApiResponse(responseCode = "400", description = "ID de responsável inválido",
							content = @Content),
					@ApiResponse(responseCode = "500", description = "Erro interno no servidor",
							content = @Content)
			})
	@ResponseStatus(code = HttpStatus.OK)
	public List<ReservaVO> getReservasByIdResponsavel(@PathVariable(value = "id") Long id) {
		return facade.getReservasByIdResponsavel(id);
	}


	@PostMapping(consumes = "application/json", produces = "application/json")
	@Operation(summary = "Cria uma nova reserva", description = "Cria uma nova reserva com os dados fornecidos",
			responses = {
					@ApiResponse(responseCode = "201", description = "Reserva criada com sucesso",
							content = @Content(mediaType = "application/json",
									schema = @Schema(implementation = ReservaVO.class))),
					@ApiResponse(responseCode = "400", description = "Dados de entrada inválidos"),
					@ApiResponse(responseCode = "500", description = "Erro interno no servidor")
			})
	@ResponseStatus(code = HttpStatus.CREATED)
	public ReservaVO createReserva(@RequestBody ReservaVO reserva) {
		return facade.createReserva(reserva);
	}

	@PutMapping(value = "/{id}", consumes = "application/json", produces = "application/json")
	@Operation(summary = "Atualiza uma reserva existente", description = "Atualiza uma reserva com base no ID fornecido e nos dados da reserva",
			responses = {
					@ApiResponse(responseCode = "200", description = "Reserva atualizada com sucesso",
							content = {@Content(mediaType = "application/json",
									schema = @Schema(implementation = ReservaVO.class))}),
					@ApiResponse(responseCode = "400", description = "Dados da solicitação inválidos",
							content = @Content),
					@ApiResponse(responseCode = "404", description = "Reserva não encontrada",
							content = @Content),
					@ApiResponse(responseCode = "500", description = "Erro interno no servidor",
							content = @Content)
			})
	@ResponseStatus(code = HttpStatus.OK)
	public ReservaVO updateReserva(@RequestBody ReservaVO reserva) {
		return facade.updateReserva(reserva);
	}

	@GetMapping("/status")
	@Operation(summary = "Obtém reservas por status", description = "Retorna uma lista de reservas com base no status fornecido",
			responses = {
					@ApiResponse(responseCode = "200", description = "Operação bem-sucedida",
							content = @Content(mediaType = "application/json",
									schema = @Schema(implementation = ReservaVO.class))),
					@ApiResponse(responseCode = "400", description = "Status de reserva inválido",
							content = @Content),
					@ApiResponse(responseCode = "500", description = "Erro interno no servidor",
							content = @Content)
			})
	@ResponseStatus(code = HttpStatus.OK)
	public List<ReservaVO> getReservasByStatus(@RequestParam(value = "status", required = false) String statusUrl) {
		StatusReserva status = StatusReserva.valueOf(statusUrl.toUpperCase());
		return facade.getReservasByStatus(status);
	}
}

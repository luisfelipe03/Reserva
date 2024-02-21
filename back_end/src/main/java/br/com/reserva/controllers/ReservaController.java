package br.com.reserva.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import br.com.reserva.data.vo.ReservaVO;
import br.com.reserva.facade.Facade;

@RestController
@RequestMapping("/api/reserva")
public class ReservaController {
	
	@Autowired
	Facade facade;
	
	@GetMapping
	@ResponseStatus(code = HttpStatus.OK)
	public List<ReservaVO> getTotasReservas() {
		return facade.getAllReservas();
	}
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public ReservaVO createReserva(@RequestBody ReservaVO reserva) {
		return facade.createReserva(reserva);
	}

	@PutMapping
	@ResponseStatus(code = HttpStatus.OK)
	public ReservaVO updateReserva(@RequestBody ReservaVO reserva) {
		return facade.updateReserva(reserva);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void deleteReserva(@PathVariable("id") Long id) {
		facade.deleteReserva(id);
	}

}

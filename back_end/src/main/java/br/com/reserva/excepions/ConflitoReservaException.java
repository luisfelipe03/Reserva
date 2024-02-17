package br.com.reserva.excepions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ConflitoReservaException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ConflitoReservaException() {
		super("Erro ao fazer a reserva! Laboratorio ou equipamento já reservado nesse mesmo horário.");
	}

	public ConflitoReservaException(String ex) {
		super(ex);
	}

}

package br.com.reserva.utils;

public enum StatusReserva {
	CONFIRMADO("CONFIRMADO"),
	FINALIZADO("FINALIZADO"),
	CANCELADO("CANCELADO"),
	EM_ANDAMENTO("EM_ANDAMENTO");

	private final String descricao;

	StatusReserva(String descricao) {
		this.descricao = descricao;
	}

	@Override
	public String toString() {
		return descricao;
	}

}

package br.com.reserva.data.vo;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import br.com.reserva.models.Equipamento;
import br.com.reserva.models.Laboratorio;
import br.com.reserva.models.Reserva;
import br.com.reserva.models.Usuario;
import br.com.reserva.utils.StatusReserva;

public class ReservaVO {

	private long id;
	private List<Equipamento> equipamentos;
	private Laboratorio lab;
	private Usuario responsavel;
	private LocalDateTime entrega;
	private LocalDateTime devolucao;
	private StatusReserva status;

	public ReservaVO() {
		this.status = StatusReserva.CONFIRMADO;
	}

	public boolean conflitoReserva(List<Reserva> reservas, List<Equipamento> equipamentosR, Laboratorio laboratorioR,
			LocalDateTime entregaR, LocalDateTime devolucaoR) {

		boolean conflitoEquipamento = false;
		boolean conflitoLab = false;

		for (Reserva r : reservas) {

			if (equipamentosR != null) {
				if (!Collections.disjoint(r.getEquipamentos(), equipamentosR))
					conflitoEquipamento = true;
			}

			if (laboratorioR != null) {
				if(laboratorioR.equals(r.getLab()))
					conflitoLab = true;
			}

			if (conflitoEquipamento || conflitoLab) {
				if (r.getEntrega() != null && r.getDevolucao() != null && entregaR != null && devolucaoR != null
						&& !entregaR.isBefore(r.getDevolucao())) {
					return true;
				}
			}
		}
		return false;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public List<Equipamento> getEquipamentos() {
		return equipamentos;
	}

	public void setEquipamentos(List<Equipamento> equipamentos) {
		this.equipamentos = equipamentos;
	}

	public Laboratorio getLab() {
		return lab;
	}

	public void setLab(Laboratorio lab) {
		this.lab = lab;
	}

	public Usuario getResponsavel() {
		return responsavel;
	}

	public void setResponsavel(Usuario responsavel) {
		this.responsavel = responsavel;
	}

	public LocalDateTime getEntrega() {
		return entrega;
	}

	public void setEntrega(LocalDateTime entrega) {
		this.entrega = entrega;
	}

	public LocalDateTime getDevolucao() {
		return devolucao;
	}

	public void setDevolucao(LocalDateTime devolucao) {
		this.devolucao = devolucao;
	}

	public StatusReserva getStatus() {
		return status;
	}

	public void setStatus(StatusReserva status) {
		this.status = status;
	}

	@Override
	public int hashCode() {
		return Objects.hash(devolucao, entrega, equipamentos, id, lab, responsavel, status);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ReservaVO other = (ReservaVO) obj;
		return Objects.equals(devolucao, other.devolucao) && Objects.equals(entrega, other.entrega)
				&& Objects.equals(equipamentos, other.equipamentos) && id == other.id && Objects.equals(lab, other.lab)
				&& Objects.equals(responsavel, other.responsavel) && status == other.status;
	}

	@Override
	public String toString() {
		return "ReservaVO [id=" + id + ", equipamentos=" + equipamentos + ", lab=" + lab + ", responsavel="
				+ responsavel + ", entrega=" + entrega + ", devolucao=" + devolucao + ", status=" + status + "]";
	}

}

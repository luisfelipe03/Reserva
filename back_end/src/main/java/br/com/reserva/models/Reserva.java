package br.com.reserva.models;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import br.com.reserva.utils.StatusReserva;
import jakarta.persistence.*;

@Entity
public class Reserva {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@ManyToMany
	private List<Equipamento> equipamentos;
	@ManyToOne
	private Laboratorio lab;
	@ManyToOne
	private Usuario responsavel;
	@Column(nullable = false)
	private LocalDateTime entrega;
	@Column(nullable = false)
	private LocalDateTime devolucao;
	@Enumerated(EnumType.STRING)
	private StatusReserva status;

	public Reserva() {

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
		Reserva other = (Reserva) obj;
		return Objects.equals(devolucao, other.devolucao) && Objects.equals(entrega, other.entrega)
				&& Objects.equals(equipamentos, other.equipamentos) && id == other.id && Objects.equals(lab, other.lab)
				&& Objects.equals(responsavel, other.responsavel) && status == other.status;
	}

	@Override
	public String toString() {
		return "Reserva [id=" + id + ", equipamentos=" + equipamentos + ", lab=" + lab + ", responsavel=" + responsavel
				+ ", entrega=" + entrega + ", devolucao=" + devolucao + ", status=" + status + "]";
	}

}

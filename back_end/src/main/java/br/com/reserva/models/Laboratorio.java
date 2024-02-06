package br.com.reserva.models;

import java.util.List;
import java.util.Objects;

import br.com.reserva.utils.Cargos;
import br.com.reserva.utils.StatusFuncionamento;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Laboratorio {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(nullable = false)
	private String numLaboratorio;
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private List<Cargos> acesso;
	@Column(nullable = false)
	private int capacidade;
	@Column(nullable = true)
	private String descricao;
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private StatusFuncionamento statusFuncionamento;

	public Laboratorio() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNumLaboratorio() {
		return numLaboratorio;
	}

	public void setNumLaboratorio(String numLaboratorio) {
		this.numLaboratorio = numLaboratorio;
	}

	public List<Cargos> getAcesso() {
		return acesso;
	}

	public void setAcesso(List<Cargos> acesso) {
		this.acesso = acesso;
	}

	public int getCapacidade() {
		return capacidade;
	}

	public void setCapacidade(int capacidade) {
		this.capacidade = capacidade;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public StatusFuncionamento getStatusFuncionamento() {
		return statusFuncionamento;
	}

	public void setStatusFuncionamento(StatusFuncionamento statusFuncionamento) {
		this.statusFuncionamento = statusFuncionamento;
	}

	@Override
	public int hashCode() {
		return Objects.hash(acesso, capacidade, descricao, id, numLaboratorio, statusFuncionamento);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Laboratorio other = (Laboratorio) obj;
		return Objects.equals(acesso, other.acesso) && capacidade == other.capacidade
				&& Objects.equals(descricao, other.descricao) && id == other.id
				&& Objects.equals(numLaboratorio, other.numLaboratorio)
				&& statusFuncionamento == other.statusFuncionamento;
	}

	@Override
	public String toString() {
		return "Laboratorio [id=" + id + ", numLaboratorio=" + numLaboratorio + ", acesso=" + acesso + ", capacidade="
				+ capacidade + ", descricao=" + descricao + ", statusFuncionamento=" + statusFuncionamento + "]";
	}

}

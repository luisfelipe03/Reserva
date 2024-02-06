package br.com.reserva.data.vo;

import java.util.List;
import java.util.Objects;

import br.com.reserva.utils.Cargos;
import br.com.reserva.utils.StatusFuncionamento;

public class LaboratorioVO {

	private long id;
	private String numLaboratorio;
	private List<Cargos> acesso;
	private int capacidade;
	private String descricao;
	private StatusFuncionamento statusFuncionamento;
	
	

	public LaboratorioVO() {}

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
	
	public void addAcesso(Cargos cargo) {
		this.acesso.add(cargo);
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
		LaboratorioVO other = (LaboratorioVO) obj;
		return Objects.equals(acesso, other.acesso) && capacidade == other.capacidade
				&& Objects.equals(descricao, other.descricao) && id == other.id
				&& Objects.equals(numLaboratorio, other.numLaboratorio)
				&& statusFuncionamento == other.statusFuncionamento;
	}

	@Override
	public String toString() {
		return "LaboratorioVO [id=" + id + ", numLaboratorio=" + numLaboratorio + ", acesso=" + acesso + ", capacidade="
				+ capacidade + ", descricao=" + descricao + ", statusFuncionamento=" + statusFuncionamento + "]";
	}

}

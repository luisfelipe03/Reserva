package br.com.reserva.data.vo;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import br.com.reserva.utils.Cargos;
import br.com.reserva.utils.StatusFuncionamento;

public class EquipamentoVO {

	private long id;
	private String nome;
	private String numPatrimonio;
	private List<Cargos> acesso;
	private LocalDate dataCompra;
	private String descricao;
	private StatusFuncionamento statusFuncionamento;

	public EquipamentoVO() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNumPatrimonio() {
		return numPatrimonio;
	}

	public void setNumPatrimonio(String numPatrimonio) {
		this.numPatrimonio = numPatrimonio;
	}

	public List<Cargos> getAcesso() {
		return acesso;
	}

	public void setAcesso(List<Cargos> acesso) {
		this.acesso = acesso;
	}

	public LocalDate getDataCompra() {
		return dataCompra;
	}

	public void setDataCompra(LocalDate dataCompra) {
		this.dataCompra = dataCompra;
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
		return Objects.hash(acesso, dataCompra, descricao, id, nome, numPatrimonio, statusFuncionamento);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EquipamentoVO other = (EquipamentoVO) obj;
		return Objects.equals(acesso, other.acesso) && Objects.equals(dataCompra, other.dataCompra)
				&& Objects.equals(descricao, other.descricao) && id == other.id && Objects.equals(nome, other.nome)
				&& Objects.equals(numPatrimonio, other.numPatrimonio)
				&& statusFuncionamento == other.statusFuncionamento;
	}

	@Override
	public String toString() {
		return "EquipamentoVO [id=" + id + ", nome=" + nome + ", numPatrimonio=" + numPatrimonio + ", acesso=" + acesso
				+ ", dataCompra=" + dataCompra + ", descricao=" + descricao + ", statusFuncionamento="
				+ statusFuncionamento + "]";
	}

}

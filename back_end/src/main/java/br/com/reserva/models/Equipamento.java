package br.com.reserva.models;

import java.time.LocalDate;
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
public class Equipamento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(nullable = false)
	private String nome;
	@Column(nullable = false)
	private String numPatrimonio;
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private List<Cargos> acesso;
	@Column(nullable = false)
	private LocalDate dataCompra;
	@Column(nullable = true)
	private String descricao;
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private StatusFuncionamento statusFuncionamento;

	public Equipamento() {
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
		Equipamento other = (Equipamento) obj;
		return Objects.equals(acesso, other.acesso) && Objects.equals(dataCompra, other.dataCompra)
				&& Objects.equals(descricao, other.descricao) && id == other.id && Objects.equals(nome, other.nome)
				&& Objects.equals(numPatrimonio, other.numPatrimonio)
				&& statusFuncionamento == other.statusFuncionamento;
	}

	@Override
	public String toString() {
		return "Equipamento [id=" + id + ", nome=" + nome + ", numPatrimonio=" + numPatrimonio + ", acesso=" + acesso
				+ ", dataCompra=" + dataCompra + ", descricao=" + descricao + ", statusFuncionamento="
				+ statusFuncionamento + "]";
	}

}

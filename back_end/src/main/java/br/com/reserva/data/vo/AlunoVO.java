package br.com.reserva.data.vo;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.reserva.models.Curso;
import br.com.reserva.utils.Cargos;

public class AlunoVO {

	private long id;
	private String nome;
	private String cpf;
	private Cargos cargo = Cargos.ALUNO;
	private String email;
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private String senha;
	private int periodo;
	private Curso curso;

	public AlunoVO() {
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getPeriodo() {
		return periodo;
	}

	public void setPeriodo(int periodo) {
		this.periodo = periodo;
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Cargos getCargo() {
		return cargo;
	}

	public void setCargo(Cargos cargo) {
		this.cargo = cargo;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cargo, cpf, curso, email, id, nome, periodo, senha);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AlunoVO other = (AlunoVO) obj;
		return cargo == other.cargo && Objects.equals(cpf, other.cpf) && Objects.equals(curso, other.curso)
				&& Objects.equals(email, other.email) && id == other.id && Objects.equals(nome, other.nome)
				&& periodo == other.periodo && Objects.equals(senha, other.senha);
	}

	@Override
	public String toString() {
		return "AlunoVO [id=" + id + ", nome=" + nome + ", cpf=" + cpf + ", cargo=" + cargo + ", email=" + email
				+ ", senha=" + senha + ", periodo=" + periodo + ", curso=" + curso + "]";
	}

	

}

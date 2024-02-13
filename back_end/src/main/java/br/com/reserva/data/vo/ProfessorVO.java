package br.com.reserva.data.vo;

import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.reserva.models.Curso;
import br.com.reserva.models.Turma;
import br.com.reserva.utils.Cargos;

public class ProfessorVO {

	private long id;
	private String nome;
	private String cpf;
	private String email;
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private String senha;
	private Cargos cargo = Cargos.PROFESSOR;
	private Curso curso;
	private List<Turma> turmas;

	public ProfessorVO() {
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

	public Cargos getCargo() {
		return cargo;
	}

	public void setCargo(Cargos cargo) {
		this.cargo = cargo;
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	public List<Turma> getTurmas() {
		return turmas;
	}

	public void setTurmas(List<Turma> turmas) {
		this.turmas = turmas;
	}
	
	public void addTurma(Turma turma) {
		this.turmas.add(turma);
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cargo, cpf, curso, email, id, nome, senha, turmas);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProfessorVO other = (ProfessorVO) obj;
		return cargo == other.cargo && Objects.equals(cpf, other.cpf) && Objects.equals(curso, other.curso)
				&& Objects.equals(email, other.email) && id == other.id && Objects.equals(nome, other.nome)
				&& Objects.equals(senha, other.senha) && Objects.equals(turmas, other.turmas);
	}

	@Override
	public String toString() {
		return "ProfessorVO [id=" + id + ", nome=" + nome + ", cpf=" + cpf + ", email=" + email + ", senha=" + senha
				+ ", cargo=" + cargo + ", curso=" + curso + ", turmas=" + turmas + "]";
	}

	
	
	
}

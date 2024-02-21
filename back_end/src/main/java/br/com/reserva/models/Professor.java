package br.com.reserva.models;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Professor extends Usuario{
	
	@ManyToOne
	private Curso curso;
	@OneToMany
	private List<Turma> turmas;

	public Professor() {
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

	@Override
	public String toString() {
		return "Professor[id= " + getId() +"\nNome= " + getNome() +"\nCPF= " + getCpf() +"\nEmail: " + getEmail() 
				+ "\nCurso= " + getCurso() +"\n" + getTurmas() +"]";
	}

	

	

}

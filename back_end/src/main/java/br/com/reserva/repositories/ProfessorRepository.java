package br.com.reserva.repositories;

import br.com.reserva.models.Turma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.reserva.models.Professor;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Long>{
}

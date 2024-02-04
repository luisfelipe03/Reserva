package br.com.reserva.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.reserva.models.Aluno;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long>{

}

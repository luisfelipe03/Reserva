package br.com.reserva.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.reserva.models.Turma;

@Repository
public interface TurmaRepository extends JpaRepository<Turma, Long>{

}

package br.com.reserva.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.reserva.models.Curso;

public interface CursoRepository extends JpaRepository<Curso, Long>{

}

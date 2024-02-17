package br.com.reserva.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.reserva.models.Reserva;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Long> {

}

package br.com.reserva.repositories;

import br.com.reserva.data.vo.ReservaVO;
import br.com.reserva.utils.StatusReserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.reserva.models.Reserva;

import java.util.List;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Long> {
    @Query("SELECT r FROM Reserva r WHERE r.status = ?1")
    List<Reserva> getReservaByStatusReserva(StatusReserva status);
}

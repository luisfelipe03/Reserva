package br.com.reserva.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.reserva.models.Laboratorio;

@Repository
public interface LaboratorioRepository extends JpaRepository<Laboratorio, Long> {

}

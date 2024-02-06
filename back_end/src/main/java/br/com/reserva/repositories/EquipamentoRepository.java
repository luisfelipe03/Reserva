package br.com.reserva.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.reserva.models.Equipamento;

@Repository
public interface EquipamentoRepository extends JpaRepository<Equipamento, Long>{

}

package br.com.reserva.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.reserva.models.Administrador;

public interface AdministradorRepository  extends JpaRepository<Administrador, Long>{

}

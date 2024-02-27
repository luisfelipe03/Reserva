package br.com.reserva.services;

import br.com.reserva.data.vo.AdministradorVO;
import br.com.reserva.data.vo.AlunoVO;
import br.com.reserva.data.vo.ReservaVO;
import br.com.reserva.utils.StatusReserva;

import java.util.List;

public interface AdministradorService {

    List<AdministradorVO> getAll();
    AdministradorVO getById(Long id);
    AdministradorVO create(AdministradorVO adm);
    AdministradorVO update(AdministradorVO adm);
    void delete(Long id);
    ReservaVO updateStatusReserva(long idReserva, StatusReserva status);
    AlunoVO bloquearAcesso(long idAluno);
    AlunoVO liberarAcesso(long idAluno);
}

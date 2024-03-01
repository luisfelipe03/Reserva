package br.com.reserva.services;

import br.com.reserva.data.vo.ReservaVO;
import br.com.reserva.models.Equipamento;
import br.com.reserva.models.Laboratorio;
import br.com.reserva.models.Reserva;
import br.com.reserva.models.Usuario;
import br.com.reserva.utils.StatusReserva;

import java.time.LocalDateTime;
import java.util.List;

public interface ReservaService {

    List<ReservaVO> findAll();
    ReservaVO findById(Long id);
    ReservaVO create(ReservaVO reservaVO);
    ReservaVO update(ReservaVO reserva);
    void delete(Long id);
    boolean conflitoReserva(Usuario responsavel, List<Reserva> reservas, List<Equipamento> equipamentosR, Laboratorio laboratorioR,
                            LocalDateTime entregaR, LocalDateTime devolucaoR);
    boolean verificaConflitoDeDatas(LocalDateTime inicioReserva1, LocalDateTime fimReserva1,
                                    LocalDateTime inicioReserva2, LocalDateTime fimReserva2);
    boolean verificaConflitoDevolucaoAntesEntrega(LocalDateTime entrega, LocalDateTime devolucao);
    boolean verificaCargoEquipamento(Usuario usuario, List<Equipamento> equipamentos);
    boolean verificaCargoLaboratorio(Usuario usuario, Laboratorio laboratorio);
    List<ReservaVO> findAllReservaByStatus(StatusReserva status);
}

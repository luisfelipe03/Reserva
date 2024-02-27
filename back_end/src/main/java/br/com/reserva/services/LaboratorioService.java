package br.com.reserva.services;

import br.com.reserva.data.vo.LaboratorioVO;

import java.util.List;

public interface LaboratorioService {

    List<LaboratorioVO> findAll();
    LaboratorioVO findById(Long id);
    LaboratorioVO create(LaboratorioVO laboratorio);
    LaboratorioVO update(LaboratorioVO laboratorio);
    void delete(Long id);
}

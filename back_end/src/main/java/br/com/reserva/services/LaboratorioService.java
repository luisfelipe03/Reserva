package br.com.reserva.services;

import br.com.reserva.data.vo.LaboratorioVO;
import br.com.reserva.models.Laboratorio;
import br.com.reserva.utils.StatusFuncionamento;

import java.util.List;

public interface LaboratorioService {

    List<LaboratorioVO> findAll();
    LaboratorioVO findById(Long id);
    LaboratorioVO create(LaboratorioVO laboratorio);
    LaboratorioVO update(LaboratorioVO laboratorio);
    void delete(Long id);

}

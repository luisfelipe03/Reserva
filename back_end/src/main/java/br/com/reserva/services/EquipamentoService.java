package br.com.reserva.services;

import br.com.reserva.data.vo.EquipamentoVO;

import java.util.List;

public interface EquipamentoService {

    List<EquipamentoVO> findAll();
    EquipamentoVO findById(Long id);
    EquipamentoVO create(EquipamentoVO equipamento);
    EquipamentoVO update(EquipamentoVO equipamento);
    void delete(Long id);

}

package br.com.reserva.services;

import br.com.reserva.data.vo.AlunoVO;

import java.util.List;

public interface AlunoService {

    List<AlunoVO> findAll();
    AlunoVO findById(Long id);
    AlunoVO create(AlunoVO aluno);
    AlunoVO update(AlunoVO aluno);
    void delete(Long id);
    List<AlunoVO> findAlunosBloqueados();
}

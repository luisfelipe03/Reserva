package br.com.reserva.services;

import br.com.reserva.data.vo.ProfessorVO;
import br.com.reserva.data.vo.TurmaVO;

import java.util.List;

public interface ProfessorService {

    List<ProfessorVO> findAll();
    ProfessorVO findById(Long id);
    ProfessorVO create(ProfessorVO professor);
    ProfessorVO update(ProfessorVO professor);
    void delete(Long id);
    ProfessorVO createTurma(long idProfessor, TurmaVO turma);
    List<TurmaVO> findTurmasByProfessorId(Long id);
    ProfessorVO updateTurma(ProfessorVO professor);
    void deleteTurma(Long idTurma, Long idProf);
    TurmaVO findTurmaById(Long id);
    TurmaVO updateTurma(ProfessorVO professor, TurmaVO turma);
}

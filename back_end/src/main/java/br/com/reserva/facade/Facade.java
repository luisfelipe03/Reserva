package br.com.reserva.facade;

import br.com.reserva.data.vo.*;
import br.com.reserva.excepions.RequiredObjectIsNullException;
import br.com.reserva.mapper.ModelMapper;
import br.com.reserva.models.Laboratorio;
import br.com.reserva.models.Reserva;
import br.com.reserva.services.*;
import br.com.reserva.utils.StatusFuncionamento;
import br.com.reserva.utils.StatusReserva;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Facade {
	
	//Aluno---------------------------------------------------------------------
	@Autowired
	AlunoService alunoService;

	public List<AlunoVO> getAllAlunos(){
		return alunoService.findAll();
	}

	public AlunoVO getByIdAluno(Long id) {
		return alunoService.findById(id);
	}

	public AlunoVO saveAluno(AlunoVO alunoNovo) {
		return alunoService.create(alunoNovo);
	}

	public AlunoVO updateAluno(AlunoVO alunoAtualizado) {
		return alunoService.update(alunoAtualizado);
	}
	public void deleteAluno(Long id) {
		alunoService.delete(id);
	}

	public List<AlunoVO> getAlunosBloqueados() {
		return alunoService.findAlunosBloqueados();
	}

	//Professor---------------------------------------------------------------------
	@Autowired
	ProfessorService professorService;

	public List<ProfessorVO> getAllProfessor(){
		return professorService.findAll();
	}

	public ProfessorVO getByIdProfessor(Long id) {
		return professorService.findById(id);
	}

	public ProfessorVO saveProfessor(ProfessorVO professorNovo) {
		return professorService.create(professorNovo);
	}
	
	public ProfessorVO createTurma(long id, TurmaVO turma) {
		professorService.createTurma(id, turma);
		return professorService.findById(id);
	}
	
	public void updateTurma(ProfessorVO professorVO) {
		professorService.updateTurma(professorVO);
	}

	public ProfessorVO updateProfessor(ProfessorVO professorAtualizado) {
		return professorService.update(professorAtualizado);
	}
	public void deleteProfessor(Long id) {
		professorService.delete(id);
	}

	public void deleteTurma(Long idTurma, Long idProf) {
		professorService.deleteTurma(idTurma, idProf);
	}

	public List<TurmaVO> getTurmas(Long id) {
		return professorService.findTurmasByProfessorId(id);
	}
	//Administrador----------------------------------------------------------------------------------------
	@Autowired
	AdministradorService administradorService;
	
	public List<AdministradorVO> getAllAdm() {
		return administradorService.getAll();
	}
	
	public AdministradorVO getByIdAdm(Long id) {
		return administradorService.getById(id);
	}
	
	public AdministradorVO createAdm(AdministradorVO adm) {
		return administradorService.create(adm);
	}
	
	public AdministradorVO updateAdm(AdministradorVO adm) {
		return administradorService.update(adm);
	}
	
	public void deleteAdm(Long id) {
		administradorService.delete(id);
	}

	public ReservaVO updateStatusReserva(long idReserva, StatusReserva status) {
		return administradorService.updateStatusReserva(idReserva, status);

	}

	public AlunoVO bloquearAluno(Long idAluno) {
		return administradorService.bloquearAcesso(idAluno);
	}
	public AlunoVO liberarAluno(Long idAluno) {
		return administradorService.liberarAcesso(idAluno);
	}
	
	//Equipamento--------------------------------------------------------------------------------------------
	@Autowired
	EquipamentoService equipamentoService;
	
	public List<EquipamentoVO> getAllEquip() {
		return equipamentoService.findAll();
	}
	
	public EquipamentoVO getByIdEquip(Long id) {
		return equipamentoService.findById(id);
	}
	
	public EquipamentoVO createEquip(EquipamentoVO equip) {
		return equipamentoService.create(equip);
	}
	
	public EquipamentoVO updateEquip(EquipamentoVO equip) {
		return equipamentoService.update(equip);
	}
	
	public void deleteEquip(Long id) {
		equipamentoService.delete(id);
	}
	public EquipamentoVO updateStatusEquip(long id, StatusFuncionamento statusFuncionamento) {
		var equip = equipamentoService.findById(id);
		equip.setStatusFuncionamento(statusFuncionamento);
		return equipamentoService.update(equip);
	}
	
	//Laboratorio------------------------------------------------------------------------------------------------
	@Autowired
	LaboratorioService laboratorioService;
	
	public List<LaboratorioVO> getAllLab() {
		return laboratorioService.findAll();
	}
	
	public LaboratorioVO getByIdLab(Long id) {
		return laboratorioService.findById(id);
	}
	
	public LaboratorioVO createLab(LaboratorioVO lab) {
		return laboratorioService.create(lab);
	}
	
	public LaboratorioVO updateLab(LaboratorioVO lab) {
		return laboratorioService.update(lab);
	}
	
	public void deleteLab(Long id) {
		laboratorioService.delete(id);
	}

	public LaboratorioVO updateStatusLab(long id, StatusFuncionamento status) {
		var lab = laboratorioService.findById(id);
		lab.setStatusFuncionamento(status);
		return laboratorioService.update(lab);
	}
	
	//Reserva------------------------------------------------------------------------------------------------
	@Autowired
	ReservaService reservaService;
	
	public List<ReservaVO> getAllReservas() {
		return reservaService.findAll();
	}
	
	public ReservaVO getByIdReserva(Long id) {
		return reservaService.findById(id);
	}
	
	public ReservaVO createReserva(ReservaVO reservaVO) {
		if (reservaVO == null)
			throw new RequiredObjectIsNullException();

		// Converte ReservaVO para entidade Reserva para realizar a verificação
		Reserva reserva = ModelMapper.parseObject(reservaVO, Reserva.class);

		// Verifica se a data de devolução é anterior à data de entrega
		if (reservaService.verificaConflitoDevolucaoAntesEntrega(reserva.getEntrega(), reserva.getDevolucao())) {
			throw new RuntimeException("A data de devolução não pode ocorrer antes da data de entrega");
		}

		List<ReservaVO> reservasVO = reservaService.findAll();
		var reservas = ModelMapper.parseListObjects(reservasVO, Reserva.class);

		// Verifica se há conflito de reserva
		if (reservaService.conflitoReserva(reserva.getResponsavel(),reservas, reserva.getEquipamentos(), reserva.getLab(), reserva.getEntrega(), reserva.getDevolucao())) {
			throw new RuntimeException("Conflito encontrado na reserva");
		}
		return reservaService.create(reservaVO);
	}
	
	public ReservaVO updateReserva(ReservaVO reservaVO) {
		if (reservaVO == null)
			throw new RequiredObjectIsNullException();

		// Converte ReservaVO para entidade Reserva para realizar a verificação
		Reserva reserva = ModelMapper.parseObject(reservaVO, Reserva.class);

		// Verifica se a data de devolução é anterior à data de entrega
		if (reservaService.verificaConflitoDevolucaoAntesEntrega(reserva.getEntrega(), reserva.getDevolucao())) {
			throw new RuntimeException("A data de devolução não pode ocorrer antes da data de entrega");
		}

		List<ReservaVO> reservasVO = reservaService.findAll();
		var reservas = ModelMapper.parseListObjects(reservasVO, Reserva.class);
		// Verifica se há conflito de reserva
		if (reservaService.conflitoReserva(reserva.getResponsavel(),reservas, reserva.getEquipamentos(), reserva.getLab(), reserva.getEntrega(), reserva.getDevolucao())) {
			throw new RuntimeException("Conflito encontrado na reserva");
		}

		var vo = ModelMapper.parseObject(reserva, ReservaVO.class);
		return reservaService.update(vo);
	}
	
	public void deleteReserva(Long id) {
		reservaService.delete(id);
	}



}

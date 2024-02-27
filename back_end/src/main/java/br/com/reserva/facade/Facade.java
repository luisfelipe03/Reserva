package br.com.reserva.facade;

import java.util.List;

import br.com.reserva.excepions.RequiredObjectIsNullException;
import br.com.reserva.mapper.ModelMapper;
import br.com.reserva.models.Reserva;
import br.com.reserva.utils.StatusReserva;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.reserva.data.vo.AdministradorVO;
import br.com.reserva.data.vo.AlunoVO;
import br.com.reserva.data.vo.EquipamentoVO;
import br.com.reserva.data.vo.LaboratorioVO;
import br.com.reserva.data.vo.ProfessorVO;
import br.com.reserva.data.vo.ReservaVO;
import br.com.reserva.data.vo.TurmaVO;
import br.com.reserva.services.AdministradorServiceImpl;
import br.com.reserva.services.AlunoServiceImpl;
import br.com.reserva.services.EquipamentoServiceImpl;
import br.com.reserva.services.LaboratorioServiceImpl;
import br.com.reserva.services.ProfessorServiceImpl;
import br.com.reserva.services.ReservaServiceImpl;

@Service
public class Facade {
	
	//Aluno---------------------------------------------------------------------
	@Autowired
	AlunoServiceImpl alunoServiceImpl;

	public List<AlunoVO> getAllAlunos(){
		return alunoServiceImpl.findAll();
	}

	public AlunoVO getByIdAluno(Long id) {
		return alunoServiceImpl.findById(id);
	}

	public AlunoVO saveAluno(AlunoVO alunoNovo) {
		return alunoServiceImpl.create(alunoNovo);
	}

	public AlunoVO updateAluno(AlunoVO alunoAtualizado) {
		return alunoServiceImpl.update(alunoAtualizado);
	}
	public void deleteAluno(Long id) {
		alunoServiceImpl.delete(id);
	}

	public List<AlunoVO> getAlunosBloqueados() {
		return alunoServiceImpl.findAlunosBloqueados();
	}

	//Professor---------------------------------------------------------------------
	@Autowired
	ProfessorServiceImpl professorServiceImpl;

	public List<ProfessorVO> getAllProfessor(){
		return professorServiceImpl.findAll();
	}

	public ProfessorVO getByIdProfessor(Long id) {
		return professorServiceImpl.findById(id);
	}

	public ProfessorVO saveProfessor(ProfessorVO professorNovo) {
		return professorServiceImpl.create(professorNovo);
	}
	
	public ProfessorVO createTurma(long id, TurmaVO turma) {
		professorServiceImpl.createTurma(id, turma);
		return professorServiceImpl.findById(id);
	}
	
	public void updateTurma(ProfessorVO professorVO) {
		professorServiceImpl.updateTurma(professorVO);
	}

	public ProfessorVO updateProfessor(ProfessorVO professorAtualizado) {
		return professorServiceImpl.update(professorAtualizado);
	}
	public void deleteProfessor(Long id) {
		professorServiceImpl.delete(id);
	}

	public void deleteTurma(Long idTurma, Long idProf) {
		professorServiceImpl.deleteTurma(idTurma, idProf);
	}

	public List<TurmaVO> getTurmas(Long id) {
		return professorServiceImpl.findTurmasByProfessorId(id);
	}
	//Administrador----------------------------------------------------------------------------------------
	@Autowired
	AdministradorServiceImpl administradorServiceImpl;
	
	public List<AdministradorVO> getAllAdm() {
		return administradorServiceImpl.getAll();
	}
	
	public AdministradorVO getByIdAdm(Long id) {
		return administradorServiceImpl.getById(id);
	}
	
	public AdministradorVO createAdm(AdministradorVO adm) {
		return administradorServiceImpl.create(adm);
	}
	
	public AdministradorVO updateAdm(AdministradorVO adm) {
		return administradorServiceImpl.update(adm);
	}
	
	public void deleteAdm(Long id) {
		administradorServiceImpl.delete(id);
	}

	public ReservaVO updateStatusReserva(long idReserva, StatusReserva status) {
		return administradorServiceImpl.updateStatusReserva(idReserva, status);

	}

	public AlunoVO bloquearAluno(Long idAluno) {
		return administradorServiceImpl.bloquearAcesso(idAluno);
	}
	public AlunoVO liberarAluno(Long idAluno) {
		return administradorServiceImpl.liberarAcesso(idAluno);
	}
	
	//Equipamento--------------------------------------------------------------------------------------------
	@Autowired
	EquipamentoServiceImpl equipamentoServiceImpl;
	
	public List<EquipamentoVO> getAllEquip() {
		return equipamentoServiceImpl.findAll();
	}
	
	public EquipamentoVO getByIdEquip(Long id) {
		return equipamentoServiceImpl.findById(id);
	}
	
	public EquipamentoVO createEquip(EquipamentoVO equip) {
		return equipamentoServiceImpl.create(equip);
	}
	
	public EquipamentoVO updateEquip(EquipamentoVO equip) {
		return equipamentoServiceImpl.update(equip);
	}
	
	public void deleteEquip(Long id) {
		equipamentoServiceImpl.delete(id);
	}
	
	//Laboratorio------------------------------------------------------------------------------------------------
	@Autowired
	LaboratorioServiceImpl laboratorioServiceImpl;
	
	public List<LaboratorioVO> getAllLab() {
		return laboratorioServiceImpl.findAll();
	}
	
	public LaboratorioVO getByIdLab(Long id) {
		return laboratorioServiceImpl.findById(id);
	}
	
	public LaboratorioVO createLab(LaboratorioVO lab) {
		return laboratorioServiceImpl.create(lab);
	}
	
	public LaboratorioVO updateLab(LaboratorioVO lab) {
		return laboratorioServiceImpl.update(lab);
	}
	
	public void deleteLab(Long id) {
		laboratorioServiceImpl.delete(id);
	}
	
	//Reserva------------------------------------------------------------------------------------------------
	@Autowired
	ReservaServiceImpl reservaServiceImpl;
	
	public List<ReservaVO> getAllReservas() {
		return reservaServiceImpl.findAll();
	}
	
	public ReservaVO getByIdReserva(Long id) {
		return reservaServiceImpl.findById(id);
	}
	
	public ReservaVO createReserva(ReservaVO reservaVO) {
		if (reservaVO == null)
			throw new RequiredObjectIsNullException();

		// Converte ReservaVO para entidade Reserva para realizar a verificação
		Reserva reserva = ModelMapper.parseObject(reservaVO, Reserva.class);

		// Verifica se a data de devolução é anterior à data de entrega
		if (reservaServiceImpl.verificaConflitoDevolucaoAntesEntrega(reserva.getEntrega(), reserva.getDevolucao())) {
			throw new RuntimeException("A data de devolução não pode ocorrer antes da data de entrega");
		}

		List<ReservaVO> reservasVO = reservaServiceImpl.findAll();
		var reservas = ModelMapper.parseListObjects(reservasVO, Reserva.class);

		// Verifica se há conflito de reserva
		if (reservaServiceImpl.conflitoReserva(reserva.getResponsavel(),reservas, reserva.getEquipamentos(), reserva.getLab(), reserva.getEntrega(), reserva.getDevolucao())) {
			throw new RuntimeException("Conflito encontrado na reserva");
		}
		return reservaServiceImpl.create(reservaVO);
	}
	
	public ReservaVO updateReserva(ReservaVO reservaVO) {
		if (reservaVO == null)
			throw new RequiredObjectIsNullException();

		// Converte ReservaVO para entidade Reserva para realizar a verificação
		Reserva reserva = ModelMapper.parseObject(reservaVO, Reserva.class);

		// Verifica se a data de devolução é anterior à data de entrega
		if (reservaServiceImpl.verificaConflitoDevolucaoAntesEntrega(reserva.getEntrega(), reserva.getDevolucao())) {
			throw new RuntimeException("A data de devolução não pode ocorrer antes da data de entrega");
		}

		List<ReservaVO> reservasVO = reservaServiceImpl.findAll();
		var reservas = ModelMapper.parseListObjects(reservasVO, Reserva.class);
		// Verifica se há conflito de reserva
		if (reservaServiceImpl.conflitoReserva(reserva.getResponsavel(),reservas, reserva.getEquipamentos(), reserva.getLab(), reserva.getEntrega(), reserva.getDevolucao())) {
			throw new RuntimeException("Conflito encontrado na reserva");
		}

		var vo = ModelMapper.parseObject(reserva, ReservaVO.class);
		return reservaServiceImpl.update(vo);
	}
	
	public void deleteReserva(Long id) {
		reservaServiceImpl.delete(id);
	}


}

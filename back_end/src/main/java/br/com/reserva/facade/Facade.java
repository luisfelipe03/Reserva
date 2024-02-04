package br.com.reserva.facade;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.reserva.data.vo.AdministradorVO;
import br.com.reserva.data.vo.AlunoVO;
import br.com.reserva.data.vo.ProfessorVO;
import br.com.reserva.data.vo.TurmaVO;
import br.com.reserva.services.AdministradorService;
import br.com.reserva.services.AlunoService;
import br.com.reserva.services.ProfessorService;

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
	
//	//Equipamento--------------------------------------------------------------------------------------------
//	@Autowired
//	EquipamentoService equipamentoService;
//	
//	public List<EquipamentoVO> getAllEquip() {
//		return equipamentoService.findAll();
//	}
//	
//	public EquipamentoVO getByIdEquip(Long id) {
//		return equipamentoService.findById(id);
//	}
//	
//	public EquipamentoVO createEquip(EquipamentoVO equip) {
//		return equipamentoService.create(equip);
//	}
//	
//	public EquipamentoVO updateEquip(EquipamentoVO equip) {
//		return equipamentoService.update(equip);
//	}
//	
//	public void deleteEquip(Long id) {
//		equipamentoService.delete(id);
//	}
//	
	//Laboratorio------------------------------------------------------------------------------------------------
//	@Autowired
//	LaboratorioService laboratorioService;
//	
//	public List<LaboratorioVO> getAllLab() {
//		return laboratorioService.findAll();
//	}
//	
//	public LaboratorioVO getByIdLab(Long id) {
//		return laboratorioService.findById(id);
//	}
//	
//	public LaboratorioVO createLab(LaboratorioVO lab) {
//		return laboratorioService.create(lab);
//	}
//	
//	public LaboratorioVO updateLab(LaboratorioVO lab) {
//		return laboratorioService.update(lab);
//	}
//	
//	public void deleteLab(Long id) {
//		laboratorioService.delete(id);
//	}
}

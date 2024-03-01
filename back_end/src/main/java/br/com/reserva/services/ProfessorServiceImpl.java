package br.com.reserva.services;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.reserva.data.vo.ProfessorVO;
import br.com.reserva.data.vo.TurmaVO;
import br.com.reserva.excepions.RequiredObjectIsNullException;
import br.com.reserva.excepions.ResourceNotFoundException;
import br.com.reserva.mapper.ModelMapper;
import br.com.reserva.models.Professor;
import br.com.reserva.models.Turma;
import br.com.reserva.repositories.ProfessorRepository;
import br.com.reserva.repositories.TurmaRepository;

@Service
public class ProfessorServiceImpl implements ProfessorService {
    // Logger para registrar mensagens de log
	private Logger logger = Logger.getLogger(ProfessorServiceImpl.class.getName());

	// Repositórios injetados usando a anotação @Autowired
	@Autowired
	ProfessorRepository repository;
	@Autowired
	TurmaRepository turmaRepository;

	// Método para obter todos os professores cadastrados
	public List<ProfessorVO> findAll() {
		logger.info("Listando todos os professores cadastrados");
		return ModelMapper.parseListObjects(repository.findAll(), ProfessorVO.class);
	}

	// Método para encontrar um professor por ID
	public ProfessorVO findById(Long id) {
		logger.info("Listando um professor pelo id");

		// Obtém a entidade do repositório ou lança uma exceção se não existir
		var entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Não existe professor cadastrado com id: " + id));
		
		// Retorna a entidade convertida para um objeto VO usando o ModelMapper
		return ModelMapper.parseObject(entity, ProfessorVO.class);
	}

	// Método para cadastrar um novo professor
	public ProfessorVO create(ProfessorVO professor) {
		if (professor == null)
			throw new RequiredObjectIsNullException();
		
		logger.info("Cadastrando professor");

		// Converte o objeto VO para uma entidade e salva no repositório
		var entity = ModelMapper.parseObject(professor, Professor.class);
		return ModelMapper.parseObject(repository.save(entity), ProfessorVO.class);
	}

	// Método para atualizar os dados de um professor existente
	public ProfessorVO update(ProfessorVO professor) {
		if (professor == null)
			throw new RequiredObjectIsNullException();
		
		logger.info("Atualizando cadastro de professor");

		// Obtém a entidade existente do repositório ou lança uma exceção se não existir
		var entity = repository.findById(professor.getId()).orElseThrow(
				() -> new ResourceNotFoundException("Não existe professor cadastrado com id:" + professor.getId()));

		// Atualiza os campos da entidade com base nos dados do objeto VO
		entity.setNome(professor.getNome());
		entity.setCpf(professor.getCpf());
		entity.setEmail(professor.getEmail());
		entity.setSenha(professor.getSenha());
		entity.setCurso(professor.getCurso());

		// Converte a entidade atualizada de volta para um objeto VO e salva no repositório
		return ModelMapper.parseObject(repository.save(entity), ProfessorVO.class);
		
	}

	// Método para excluir um professor por ID
	public void delete(Long id) {

		logger.info("Deletando professor pelo id");

		// Obtém a entidade do repositório ou lança uma exceção se não existir
		var entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Não existe professor cadastrado com id: " + id));
		
		// Exclui a entidade do repositório
		repository.delete(entity);
	}

	// Turma--------------------------------------------------
	// Método para associar uma turma a um professor
	public ProfessorVO createTurma(long idProfessor, TurmaVO turma) {
		 // Converte o objeto VO da turma para uma entidade e salva no repositório de turmas
		var entity = ModelMapper.parseObject(turma, Turma.class);
		turmaRepository.save(entity);
		
		// Obtém o professor pelo ID, lança uma exceção se não existir
		ProfessorVO professor = ModelMapper.parseObject(
				repository.findById(idProfessor).orElseThrow(
						() -> new ResourceNotFoundException("Não existe professor cadastrado com id:" + idProfessor)),
				ProfessorVO.class);
		
		// Adiciona a turma ao professor e salva no repositório de professores
		professor.addTurma(entity);
		var entityP = ModelMapper.parseObject(professor, Professor.class);
		return ModelMapper.parseObject(repository.save(entityP), ProfessorVO.class);
	}

	@Override
	public List<TurmaVO> findTurmasByProfessorId(Long id) {
		Professor professor = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Não existe professor cadastrado com id: " + id));
		List<TurmaVO> turmas = ModelMapper.parseListObjects(professor.getTurmas(), TurmaVO.class);
		return turmas;
	}

	public ProfessorVO updateTurma(ProfessorVO professor) {
		var entity = repository.findById(professor.getId()).orElseThrow(
				() -> new ResourceNotFoundException("Não existe professor cadastrado com id:" + professor.getId()));

		entity.setTurmas(professor.getTurmas());
		return ModelMapper.parseObject(repository.save(entity), ProfessorVO.class);
	}

	public void deleteTurma(Long idTurma, Long idProf) {

		logger.info("Deletando Turma pelo id");

		// Obtém a entidade do repositório ou lança uma exceção se não existir
		var entity = turmaRepository.findById(idTurma)
				.orElseThrow(() -> new ResourceNotFoundException("Não existe professor cadastrado com id: " + idTurma));

		var prof = repository.findById(idProf).orElseThrow(
				() -> new ResourceNotFoundException("Não existe professor cadastrado com id:" + idProf));

		if(!prof.getTurmas().contains(entity)) {
			throw new ResourceNotFoundException("Não existe turma cadastrada com id: " + idTurma + " para o professor com id: " + idProf);
		}

		prof.getTurmas().remove(entity);
		repository.save(prof);
		// Exclui a entidade do repositório
		turmaRepository.delete(entity);
	}

	@Override
	public TurmaVO findTurmaById(Long idTurma, ProfessorVO professor) {
		Professor professorEntity = repository.findById(professor.getId()).orElseThrow(
				() -> new ResourceNotFoundException("Não existe professor cadastrado com id:" + professor.getId()));
		Turma turma = turmaRepository.findById(idTurma).orElseThrow(
				() -> new ResourceNotFoundException("Não existe turma cadastrada com id:" + idTurma));
		if(!professorEntity.getTurmas().contains(turma)) {
			throw new ResourceNotFoundException("Não existe turma cadastrada com id:" + idTurma + " para o professor com id:" + professor.getId());
		}
		return ModelMapper.parseObject(turma, TurmaVO.class);
	}

	@Override
	public TurmaVO updateTurma(ProfessorVO professor,TurmaVO turma) {
		Turma entity = turmaRepository.findById(turma.getId()).orElseThrow(
				() -> new ResourceNotFoundException("Não existe turma cadastrada com id:" + turma.getId()));
		Professor professorEntity = repository.findById(professor.getId()).orElseThrow(
				() -> new ResourceNotFoundException("Não existe professor cadastrado com id:" + professor.getId()));
		if(!professorEntity.getTurmas().contains(entity)) {
			throw new ResourceNotFoundException("Não existe turma cadastrada com id:" + turma.getId() + " para o professor com id:" + professor.getId());
		}

		TurmaVO turmaAtualizada = null;
		for(Turma t : professorEntity.getTurmas()) {
			if(t.getId() == turma.getId()) {
				t.setDisciplina(turma.getDisciplina());
				t.setCurso(turma.getCurso());
				turmaRepository.save(t);
				turmaAtualizada = ModelMapper.parseObject(t, TurmaVO.class);
			}
		}
		return turmaAtualizada;
	}

}

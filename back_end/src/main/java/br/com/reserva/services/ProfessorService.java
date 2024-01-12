package br.com.reserva.services;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.reserva.data.vo.ProfessorVO;
import br.com.reserva.excepions.ResourceNotFoundException;
import br.com.reserva.mapper.ModelMapper;
import br.com.reserva.models.Professor;
import br.com.reserva.repositories.ProfessorRepository;

@Service
public class ProfessorService {
	
	private Logger logger = Logger.getLogger(AlunoService.class.getName());

	@Autowired
	ProfessorRepository repository;
	
	public List<ProfessorVO> findAll() {
		logger.info("Listando todos os professores cadastrados");
		return ModelMapper.parseListObjects(repository.findAll(), ProfessorVO.class);
	}
	
	public ProfessorVO findById(Long id) {

		logger.info("Listando um professor pelo id");

		var entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Não existe professor cadastrado com id: " + id));
		return ModelMapper.parseObject(entity, ProfessorVO.class);
	}

	public ProfessorVO create(ProfessorVO professor) {
		logger.info("Cadastrando professor");
		var entity = ModelMapper.parseObject(professor, Professor.class);
		var vo = ModelMapper.parseObject(repository.save(entity), ProfessorVO.class);
		return vo;
	}

	public ProfessorVO update(ProfessorVO professor) {

		logger.info("Atualizando cadastro de professor");

		var entity = repository.findById(professor.getId())
				.orElseThrow(() -> new ResourceNotFoundException("Não existe professor cadastrado com id:" + professor.getId()));

		entity.setNome(professor.getNome());
		entity.setCpf(professor.getCpf());
		entity.setEmail(professor.getEmail());
		entity.setCurso(professor.getCurso());
		entity.setTurmas(professor.getTurmas());

		var vo = ModelMapper.parseObject(repository.save(entity), ProfessorVO.class);
		return vo;
	}

	public void delete(Long id) {

		logger.info("Deletando professor pelo id");

		var entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Não existe professor cadastrado com id: " + id));
		repository.delete(entity);
	}
}

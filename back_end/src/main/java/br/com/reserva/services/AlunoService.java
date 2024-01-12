package br.com.reserva.services;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.reserva.data.vo.AlunoVO;
import br.com.reserva.excepions.ResourceNotFoundException;
import br.com.reserva.mapper.ModelMapper;
import br.com.reserva.models.Aluno;
import br.com.reserva.repositories.AlunoRepository;

@Service
public class AlunoService {

	private Logger logger = Logger.getLogger(AlunoService.class.getName());

	@Autowired
	AlunoRepository repository;

	public List<AlunoVO> findAll() {
		logger.info("Listando todos os alunos cadastrados no bd");
		return ModelMapper.parseListObjects(repository.findAll(), AlunoVO.class);
	}

	public AlunoVO findById(Long id) {

		logger.info("Listando um aluno pelo id");

		var entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
		return ModelMapper.parseObject(entity, AlunoVO.class);
	}

	public AlunoVO create(AlunoVO aluno) {
		logger.info("Cadastrando aluno");
		var entity = ModelMapper.parseObject(aluno, Aluno.class);
		var vo = ModelMapper.parseObject(repository.save(entity), AlunoVO.class);
		return vo;
	}

	public AlunoVO update(AlunoVO aluno) {

		logger.info("Updating one person!");

		var entity = repository.findById(aluno.getId())
				.orElseThrow(() -> new ResourceNotFoundException("Não existe aluno cadastrado com id:" + aluno.getId()));

		entity.setNome(aluno.getNome());
		entity.setCpf(aluno.getCpf());
		entity.setEmail(aluno.getEmail());
		entity.setPeriodo(aluno.getPeriodo());
		entity.setCurso(aluno.getCurso());

		var vo = ModelMapper.parseObject(repository.save(entity), AlunoVO.class);
		return vo;
	}

	public void delete(Long id) {

		logger.info("Deleting one person!");

		var entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
		repository.delete(entity);
	}
}

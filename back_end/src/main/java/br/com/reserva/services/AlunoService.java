package br.com.reserva.services;

import java.util.List;
import java.util.logging.Logger;

import br.com.reserva.utils.Cargos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.reserva.data.vo.AlunoVO;
import br.com.reserva.excepions.ResourceNotFoundException;
import br.com.reserva.mapper.ModelMapper;
import br.com.reserva.models.Aluno;
import br.com.reserva.repositories.AlunoRepository;

@Service
public class AlunoService {

	// Logger para registrar mensagens de log
	private Logger logger = Logger.getLogger(AlunoService.class.getName());

	// Repositório de Alunos injetado usando a anotação @Autowired
	@Autowired
	AlunoRepository repository;

	// Método para obter todos os alunos cadastrados
	public List<AlunoVO> findAll() {
		logger.info("Listando todos os alunos cadastrados");
		// Utiliza o ModelMapper para converter entidades em objetos VO
		return ModelMapper.parseListObjects(repository.findAll(), AlunoVO.class);
	}

	// Método para encontrar um aluno por ID
	public AlunoVO findById(Long id) {

		logger.info("Listando um aluno pelo id");

		// Obtém a entidade do repositório ou lança uma exceção se não existir
		var entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Não existe aluno cadastrado com id: " + id));

		// Converte a entidade para um objeto VO usando o ModelMapper
		return ModelMapper.parseObject(entity, AlunoVO.class);
	}

	// Método para cadastrar um novo aluno
	public AlunoVO create(AlunoVO aluno) {
		logger.info("Cadastrando aluno");

		// Converte o objeto VO para uma entidade e salva no repositório
		var entity = ModelMapper.parseObject(aluno, Aluno.class);
		var vo = ModelMapper.parseObject(repository.save(entity), AlunoVO.class);
		return vo;
	}

	// Método para atualizar os dados de um aluno existente
	public AlunoVO update(AlunoVO aluno) {

		logger.info("Atualizando cadastro de aluno");

		// Obtém a entidade existente do repositório ou lança uma exceção se não existir
		var entity = repository.findById(aluno.getId()).orElseThrow(
				() -> new ResourceNotFoundException("Não existe aluno cadastrado com id:" + aluno.getId()));

		// Atualiza os campos da entidade com base nos dados do objeto VO
		entity.setNome(aluno.getNome());
		entity.setCpf(aluno.getCpf());
		entity.setEmail(aluno.getEmail());
		entity.setSenha(aluno.getSenha());
		entity.setPeriodo(aluno.getPeriodo());
		entity.setCurso(aluno.getCurso());

		// Converte a entidade atualizada de volta para um objeto VO e salva no
		// repositório
		var vo = ModelMapper.parseObject(repository.save(entity), AlunoVO.class);
		return vo;
	}

	// Método para excluir um aluno por ID
	public void delete(Long id) {

		logger.info("Deletando aluno pelo id");

		// Obtém a entidade do repositório ou lança uma exceção se não existir

		var entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Não existe aluno cadastrado com id: " + id));

		// Exclui a entidade do repositório
		repository.delete(entity);
	}

	public List<AlunoVO> findAlunosBloqueados() {
		logger.info("Listando alunos bloqueados");
		return ModelMapper.parseListObjects(repository.findByCargo(Cargos.BLOQUEADO), AlunoVO.class);
	}
}

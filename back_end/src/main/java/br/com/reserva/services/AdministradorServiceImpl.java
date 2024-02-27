package br.com.reserva.services;

import java.util.List;
import java.util.logging.Logger;

import br.com.reserva.data.vo.AlunoVO;
import br.com.reserva.data.vo.ReservaVO;
import br.com.reserva.repositories.AlunoRepository;
import br.com.reserva.repositories.ReservaRepository;
import br.com.reserva.utils.Cargos;
import br.com.reserva.utils.StatusReserva;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.reserva.data.vo.AdministradorVO;
import br.com.reserva.excepions.ResourceNotFoundException;
import br.com.reserva.mapper.ModelMapper;
import br.com.reserva.models.Administrador;
import br.com.reserva.repositories.AdministradorRepository;

@Service
public class AdministradorServiceImpl  implements AdministradorService{
	
	// Logger para registrar mensagens de log
	private Logger logger = Logger.getLogger(AdministradorServiceImpl.class.getName());
	
	// Repositório de Administradores injetado usando a anotação @Autowired
	@Autowired
	AdministradorRepository repository;
	@Autowired
	ReservaRepository reservaRepository;
	@Autowired
	AlunoRepository alunoRepository;
	
	// Método para obter todos os administradores cadastrados
	public List<AdministradorVO> getAll() {
		logger.info("Listando todos os adms cadastrados");
		
		// Utiliza o ModelMapper para converter entidades em objetos VO
		return ModelMapper.parseListObjects(repository.findAll(), AdministradorVO.class);
	}
	
	// Método para encontrar um administrador por ID
	public AdministradorVO getById(Long id) {
		logger.info("Listando um adm pelo id");
		
		// Obtém a entidade do repositório ou lança uma exceção se não existir
		var entity = repository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("Não existe adm cadastrado com id: " + id));
		
		// Converte a entidade para um objeto VO usando o ModelMapper
		return ModelMapper.parseObject(entity, AdministradorVO.class);
	}
	
	// Método para cadastrar um novo administrador
	public AdministradorVO create(AdministradorVO adm) {
		logger.info("Cadastrando adm");
		
		// Converte o objeto VO para uma entidade e salva no repositório
		var entity = ModelMapper.parseObject(adm, Administrador.class);
		var vo = ModelMapper.parseObject(repository.save(entity), AdministradorVO.class);
		return vo;
	}
	
	// Método para atualizar os dados de um administrador existente
	public AdministradorVO update(AdministradorVO adm) {
		logger.info("Atualizando cadastro de adm");

		// Obtém a entidade existente do repositório ou lança uma exceção se não existir
		var entity = repository.findById(adm.getId())
				.orElseThrow(() -> new ResourceNotFoundException("Não existe adm cadastrado com id:" + adm.getId()));

		// Atualiza os campos da entidade com base nos dados do objeto VO
		entity.setNome(adm.getNome());
		entity.setCpf(adm.getCpf());
		entity.setEmail(adm.getEmail());
		entity.setSenha(adm.getSenha());
		
		// Converte a entidade atualizada de volta para um objeto VO e salva no repositório
		var vo = ModelMapper.parseObject(repository.save(entity), AdministradorVO.class);
		return vo;
	}
	
	// Método para excluir um administrador por ID
	public void delete(Long id) {

		logger.info("Deletando adm pelo id");

		// Obtém a entidade do repositório ou lança uma exceção se não existir
		var entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Não existe adm cadastrado com id: " + id));
		
		// Exclui a entidade do repositório
		repository.delete(entity);
	}

	public ReservaVO updateStatusReserva(long idReserva, StatusReserva status) {
		logger.info("Atualizando status da reserva");

		// Obtém a entidade existente do repositório ou lança uma exceção se não existir
		var entity = reservaRepository.findById(idReserva)
				.orElseThrow(() -> new ResourceNotFoundException("Não existe reserva cadastrada com id:" + idReserva));

		// Atualiza os campos da entidade com base nos dados do objeto VO
		entity.setStatus(status);

		// Converte a entidade atualizada de volta para um objeto VO e salva no repositório
		var vo = ModelMapper.parseObject(reservaRepository.save(entity), ReservaVO.class);
		return vo;
	}

	public AlunoVO bloquearAcesso(long idAluno) {
		logger.info("Bloqueando acesso do aluno");

		// Obtém a entidade existente do repositório ou lança uma exceção se não existir
		var entity = alunoRepository.findById(idAluno)
				.orElseThrow(() -> new ResourceNotFoundException("Não existe aluno cadastrado com id:" + idAluno));

		// Atualiza os campos da entidade com base nos dados do objeto VO
		entity.setCargo(Cargos.BLOQUEADO);

		// Converte a entidade atualizada de volta para um objeto VO e salva no repositório
		var vo = ModelMapper.parseObject(alunoRepository.save(entity), AlunoVO.class);
		return vo;
	}
	public AlunoVO liberarAcesso(long idAluno) {
		logger.info("Liberando acesso do aluno");

		// Obtém a entidade existente do repositório ou lança uma exceção se não existir
		var entity = alunoRepository.findById(idAluno)
				.orElseThrow(() -> new ResourceNotFoundException("Não existe aluno cadastrado com id:" + idAluno));

		if(entity.getCargo() != Cargos.BLOQUEADO){
			throw new ResourceNotFoundException("O aluno não está bloqueado");
		}
		// Atualiza os campos da entidade com base nos dados do objeto VO
		entity.setCargo(Cargos.ALUNO);

		// Converte a entidade atualizada de volta para um objeto VO e salva no repositório
		var vo = ModelMapper.parseObject(alunoRepository.save(entity), AlunoVO.class);
		return vo;
	}

}

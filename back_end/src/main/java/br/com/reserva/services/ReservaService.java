package br.com.reserva.services;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.reserva.data.vo.ReservaVO;
import br.com.reserva.excepions.ConflitoReservaException;
import br.com.reserva.excepions.RequiredObjectIsNullException;
import br.com.reserva.excepions.ResourceNotFoundException;
import br.com.reserva.mapper.ModelMapper;
import br.com.reserva.models.Reserva;
import br.com.reserva.repositories.ReservaRepository;

@Service
public class ReservaService {

	// Logger para registrar mensagens de log
	private Logger logger = Logger.getLogger(ReservaService.class.getName());

	@Autowired
	ReservaRepository repository;

	public List<ReservaVO> findAll() {
		logger.info("Listando todas as reservas");
		return ModelMapper.parseListObjects(repository.findAll(), ReservaVO.class);
	}

	public ReservaVO findById(Long id) {
		logger.info("Listando reservas pelo id");

		var entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Não existe reserva cadastrado com id: " + id));

		return ModelMapper.parseObject(entity, ReservaVO.class);
	}

	public ReservaVO create(ReservaVO reserva) {
		if (reserva == null)
			throw new RequiredObjectIsNullException();

		if(reserva.verificaConflitoDevolucaoAntesEntrega(reserva.getEntrega(), reserva.getDevolucao())) {
			throw new ConflitoReservaException("A data de devolução não pode ocorrer antes da data de entrega");
		}

		if(reserva.conflitoReserva(repository.findAll(),reserva.getEquipamentos(), reserva.getLab(), reserva.getEntrega(),
				reserva.getDevolucao())) {
			throw new ConflitoReservaException();
		}
		
		logger.info("Cadastrando uma reserva");

		var entity = ModelMapper.parseObject(reserva, Reserva.class);
		return ModelMapper.parseObject(repository.save(entity), ReservaVO.class);
	}

	public ReservaVO update(ReservaVO reserva) {
		if (reserva == null)
			throw new RequiredObjectIsNullException();

		if(reserva.verificaConflitoDevolucaoAntesEntrega(reserva.getEntrega(), reserva.getDevolucao())) {
			throw new ConflitoReservaException("A data de devolução não pode ocorrer antes da data de entrega");
		}
		
//		if(reserva.conflitoReserva(repository.findAll(),reserva.getEquipamentos(), reserva.getLab(), reserva.getEntrega(),
//				reserva.getDevolucao())) 
//			throw new ConflitoReservaException();
		
		if(reserva.getDevolucao().isBefore(reserva.getEntrega())) {
			throw new ConflitoReservaException("A data de devolução não pode ocorrer antes da data de entrega");
		}

		logger.info("Atualizando cadastro de reserva");

		var entity = repository.findById(reserva.getId()).orElseThrow(
				() -> new ResourceNotFoundException("Não existe reserva cadastrado com id: " + reserva.getId()));

		entity.setEquipamentos(reserva.getEquipamentos());
		entity.setLab(reserva.getLab());
		entity.setResponsavel(reserva.getResponsavel());
		entity.setEntrega(reserva.getEntrega());
		entity.setDevolucao(reserva.getDevolucao());
		entity.setStatus(reserva.getStatus());

		return ModelMapper.parseObject(repository.save(entity), ReservaVO.class);
	}

	public void delete(Long id) {
		logger.info("Deletando reserva pelo id");

		var entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Não existe reserva cadastrado com id: " + id));

		repository.delete(entity);
	}
}

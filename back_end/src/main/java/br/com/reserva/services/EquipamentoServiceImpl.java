package br.com.reserva.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.reserva.data.vo.EquipamentoVO;
import br.com.reserva.excepions.RequiredObjectIsNullException;
import br.com.reserva.excepions.ResourceNotFoundException;
import br.com.reserva.mapper.ModelMapper;
import br.com.reserva.models.Equipamento;
import br.com.reserva.repositories.EquipamentoRepository;

import java.util.List;
import java.util.logging.Logger;

@Service
public class EquipamentoServiceImpl implements EquipamentoService {

	// Logger para registrar mensagens de log
	private Logger logger = Logger.getLogger(EquipamentoServiceImpl.class.getName());
	
	@Autowired
	EquipamentoRepository repository;
	
	public List<EquipamentoVO> findAll() {
		logger.info("Listando todos os equipamentos");
		return ModelMapper.parseListObjects(repository.findAll(), EquipamentoVO.class);
	}
	
	public EquipamentoVO findById(Long id) {
		logger.info("Listando equipamento pelo id");
		
		var entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Não existe equipamento cadastrado com id: " + id));
		
		return ModelMapper.parseObject(entity, EquipamentoVO.class);
	}
	
	public EquipamentoVO create(EquipamentoVO equipamento) {
		if (equipamento == null)
			throw new RequiredObjectIsNullException();
		
		logger.info("Cadastrando equipamento");
		
		var entity = ModelMapper.parseObject(equipamento, Equipamento.class);
		return ModelMapper.parseObject(repository.save(entity), EquipamentoVO.class);
	}
	
	public EquipamentoVO update(EquipamentoVO equipamento) {
		if (equipamento == null)
			throw new RequiredObjectIsNullException();
		
		logger.info("Atualizando cadastro de equipamento");
		
		var entity = repository.findById(equipamento.getId())
				.orElseThrow(() -> new ResourceNotFoundException("Não existe equipamento cadastrado com id: " + equipamento.getId()));
		
		entity.setNome(equipamento.getNome());
		entity.setNumPatrimonio(equipamento.getNumPatrimonio());
		entity.setAcesso(equipamento.getAcesso());
		entity.setDataCompra(equipamento.getDataCompra());
		entity.setStatusFuncionamento(equipamento.getStatusFuncionamento());
		entity.setDescricao(equipamento.getDescricao());
		
		return ModelMapper.parseObject(repository.save(entity), EquipamentoVO.class);
	}
	
	public void delete(Long id) {
		logger.info("Deletando equipamento pelo id");
		
		var entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Não existe equipamento cadastrado com id: " + id));
		
		repository.delete(entity);
	}
}

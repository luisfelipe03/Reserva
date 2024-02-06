package br.com.reserva.services;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.reserva.data.vo.LaboratorioVO;
import br.com.reserva.excepions.RequiredObjectIsNullException;
import br.com.reserva.excepions.ResourceNotFoundException;
import br.com.reserva.mapper.ModelMapper;
import br.com.reserva.models.Laboratorio;
import br.com.reserva.repositories.LaboratorioRepository;

@Service
public class LaboratorioService {

	// Logger para registrar mensagens de log
		private Logger logger = Logger.getLogger(LaboratorioService.class.getName());
		
		@Autowired
		LaboratorioRepository repository;
		
		public List<LaboratorioVO> findAll() {
			logger.info("Listando todos os laboratorios");
			return ModelMapper.parseListObjects(repository.findAll(), LaboratorioVO.class);
		}
		
		public LaboratorioVO findById(Long id) {
			logger.info("Listando laboratorio pelo id");
			
			var entity = repository.findById(id)
					.orElseThrow(() -> new ResourceNotFoundException("Não existe laboratorio cadastrado com id: " + id));
			
			return ModelMapper.parseObject(entity, LaboratorioVO.class);
		}
		
		public LaboratorioVO create(LaboratorioVO laboratorio) {
			if (laboratorio == null)
				throw new RequiredObjectIsNullException();
			
			logger.info("Cadastrando laboratorio");
			
			var entity = ModelMapper.parseObject(laboratorio, Laboratorio.class);
			return ModelMapper.parseObject(repository.save(entity), LaboratorioVO.class);
		}
		
		public LaboratorioVO update(LaboratorioVO laboratorio) {
			if (laboratorio == null)
				throw new RequiredObjectIsNullException();
			
			logger.info("Atualizando cadastro de laboratorio");
			
			var entity = repository.findById(laboratorio.getId())
					.orElseThrow(() -> new ResourceNotFoundException("Não existe laboratorio cadastrado com id: " + laboratorio.getId()));
			
			entity.setNumLaboratorio(laboratorio.getNumLaboratorio());
			entity.setCapacidade(laboratorio.getCapacidade());
			entity.setAcesso(laboratorio.getAcesso());
			entity.setDescricao(laboratorio.getDescricao());
			entity.setStatusFuncionamento(laboratorio.getStatusFuncionamento());
			
			return ModelMapper.parseObject(repository.save(entity), LaboratorioVO.class);
		}
		
		public void delete(Long id) {
			logger.info("Deletando laboratorio pelo id");
			
			var entity = repository.findById(id)
					.orElseThrow(() -> new ResourceNotFoundException("Não existe laboratorio cadastrado com id: " + id));
			
			repository.delete(entity);
		}
}

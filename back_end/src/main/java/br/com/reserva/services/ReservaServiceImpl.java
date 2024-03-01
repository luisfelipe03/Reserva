package br.com.reserva.services;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

import br.com.reserva.models.Equipamento;
import br.com.reserva.models.Laboratorio;
import br.com.reserva.models.Usuario;
import br.com.reserva.utils.StatusFuncionamento;
import br.com.reserva.utils.StatusReserva;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.reserva.data.vo.ReservaVO;
import br.com.reserva.excepions.ResourceNotFoundException;
import br.com.reserva.mapper.ModelMapper;
import br.com.reserva.models.Reserva;
import br.com.reserva.repositories.ReservaRepository;

@Service
public class ReservaServiceImpl implements ReservaService {

	// Logger para registrar mensagens de log
	private Logger logger = Logger.getLogger(ReservaServiceImpl.class.getName());

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
		return ModelMapper.parseObject(repository.findById(id), ReservaVO.class);
	}

	public ReservaVO create(ReservaVO reservaVO) {
		// Realiza o cadastro da reserva
		logger.info("Cadastrando uma reserva");
		Reserva savedReserva = repository.save(ModelMapper.parseObject(reservaVO, Reserva.class));
		return ModelMapper.parseObject(savedReserva, ReservaVO.class);
	}

	public ReservaVO update(ReservaVO reserva) {
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

	public boolean conflitoReserva(Usuario responsavel, List<Reserva> reservas, List<Equipamento> equipamentosR, Laboratorio laboratorioR, LocalDateTime entregaR, LocalDateTime devolucaoR) {

		// Verifica permissões antes de processar as reservas
		if (!verificaPermissoesParaReserva(responsavel, equipamentosR, laboratorioR)) {
			throw new RuntimeException("Usuário sem permissão para reservar equipamento ou laboratório");
		}

		// Verifica conflitos de reservas
		for (Reserva reserva : reservas) {
			if (verificaConflito(reserva, equipamentosR, laboratorioR, entregaR, devolucaoR)) {
				return true; // Há um conflito
			}
		}

		return false; // Não há conflito
	}

	private boolean verificaPermissoesParaReserva(Usuario responsavel, List<Equipamento> equipamentos, Laboratorio laboratorio) {
		return verificaCargoEquipamento(responsavel, equipamentos) && verificaCargoLaboratorio(responsavel, laboratorio);
	}

	private boolean verificaConflito(Reserva reserva, List<Equipamento> equipamentos, Laboratorio laboratorio, LocalDateTime entrega, LocalDateTime devolucao) {
		boolean conflitoEquipamento = equipamentos != null && !Collections.disjoint(reserva.getEquipamentos(), equipamentos);
		boolean conflitoLab = laboratorio != null && laboratorio.equals(reserva.getLab());

		return (conflitoEquipamento || conflitoLab) && verificaConflitoDeDatas(reserva.getEntrega(), reserva.getDevolucao(), entrega, devolucao);
	}

	public boolean verificaConflitoDeDatas(LocalDateTime inicioReserva1, LocalDateTime fimReserva1,
											LocalDateTime inicioReserva2, LocalDateTime fimReserva2) {
		// Verifica se há sobreposição entre os intervalos de tempo
		return !inicioReserva1.isAfter(fimReserva2) && !inicioReserva2.isAfter(fimReserva1);
	}


	public boolean verificaConflitoDevolucaoAntesEntrega(LocalDateTime entrega, LocalDateTime devolucao) {
		return entrega.isAfter(devolucao);
	}

	public boolean verificaCargoEquipamento(Usuario usuario, List<Equipamento> equipamentos) {
		return equipamentos.stream()
				.anyMatch(equipamento -> equipamento.getAcesso().contains(usuario.getCargo()) && equipamento.getStatusFuncionamento() != StatusFuncionamento.EM_MANUTENCAO);
	}

	public boolean verificaCargoLaboratorio(Usuario usuario, Laboratorio laboratorio) {
		return laboratorio != null && laboratorio.getAcesso().contains(usuario.getCargo()) && laboratorio.getStatusFuncionamento() != StatusFuncionamento.EM_MANUTENCAO;
	}

	public List<ReservaVO> findAllReservaByStatus(StatusReserva status) {
		return ModelMapper.parseListObjects(repository.getReservaByStatusReserva(status), ReservaVO.class);
	}

}

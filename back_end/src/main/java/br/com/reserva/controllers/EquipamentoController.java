package br.com.reserva.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.reserva.data.vo.EquipamentoVO;
import br.com.reserva.facade.Facade;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("api/equipamento")
@Tag(name = "Equipamento", description = "Endpoints para gerenciamento de equipamento")
public class EquipamentoController {
	
	@Autowired
	Facade facade;
	
	@GetMapping
	@ResponseStatus(code = HttpStatus.OK)
	public List<EquipamentoVO> getTodosEquip() {
		return facade.getAllEquip();
	}
	
	@GetMapping("/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public EquipamentoVO getEquip(@PathVariable(value = "id") long id) {
		return facade.getByIdEquip(id);
	}
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public EquipamentoVO create(EquipamentoVO equip) {
		return facade.createEquip(equip);
	}
	
	@PutMapping
	@ResponseStatus(code = HttpStatus.OK)
	public EquipamentoVO update(EquipamentoVO equip) {
		return facade.updateEquip(equip);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void delete(@PathVariable(value = "id") long id) {
		facade.deleteEquip(id);
	}

}

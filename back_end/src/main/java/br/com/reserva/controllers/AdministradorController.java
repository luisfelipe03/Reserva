package br.com.reserva.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.reserva.data.vo.AdministradorVO;
import br.com.reserva.facade.Facade;

@RestController
@RequestMapping("api/adm")
public class AdministradorController {
	
	@Autowired
	Facade facade;
	
	@GetMapping
	@ResponseStatus(code = HttpStatus.OK)
	public List<AdministradorVO> getTodosAdm() {
		return facade.getAllAdm();
	}
	
	@GetMapping("/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public AdministradorVO getTodosAdm(@PathVariable(value = "id") Long id) {
		return facade.getByIdAdm(id);
	}
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public AdministradorVO create(@RequestBody AdministradorVO administradorVO) {
		return facade.createAdm(administradorVO);
	}
	
	@PutMapping
	@ResponseStatus(code = HttpStatus.OK)
	public AdministradorVO update(@RequestBody AdministradorVO administradorVO) {
		return facade.updateAdm(administradorVO);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void delete(@PathVariable(value = "id") Long id) {
		facade.deleteAdm(id);
	}

}

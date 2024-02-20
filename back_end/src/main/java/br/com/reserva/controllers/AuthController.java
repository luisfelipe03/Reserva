package br.com.reserva.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.reserva.facade.Facade;
import br.com.reserva.mapper.ModelMapper;
import br.com.reserva.models.Administrador;
import br.com.reserva.models.Aluno;
import br.com.reserva.models.Professor;
import br.com.reserva.models.Usuario;
import br.com.reserva.services.AuthService;
import br.com.reserva.utils.Cargos;

@RestController
@RequestMapping("api/login")
public class AuthController {
	
	@Autowired
	AuthService service;
	@Autowired
	Facade facade;
	
	@PostMapping
	public ResponseEntity<Usuario> login(@RequestBody Usuario usuario) {		
		
		Usuario usrLogado = service.autenticar(usuario.getEmail(),usuario.getSenha());
		if(usrLogado != null) {
			if(usrLogado.getCargo() == Cargos.ADMINISTRADOR) {
				var adm = facade.getByIdAdm(usrLogado.getId());
				var admResponse = ModelMapper.parseObject(adm, Administrador.class);
				return ResponseEntity.status(HttpStatus.OK).body(admResponse);
				
			}
			if(usrLogado.getCargo() == Cargos.PROFESSOR) {
				var prof = facade.getByIdProfessor(usrLogado.getId());
				var profResponse = ModelMapper.parseObject(prof, Professor.class);
				return ResponseEntity.status(HttpStatus.OK).body(profResponse);
			}
			if(usrLogado.getCargo() == Cargos.ADMINISTRADOR) {
				var aluno = facade.getByIdAdm(usrLogado.getId());
				var alunoResponse = ModelMapper.parseObject(aluno, Aluno.class);
				return ResponseEntity.status(HttpStatus.OK).body(alunoResponse);
			}
		}
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(usrLogado);
	}

}

package br.com.reserva.controllers;

import br.com.reserva.data.vo.UsuarioVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.reserva.facade.Facade;
import br.com.reserva.mapper.ModelMapper;
import br.com.reserva.models.Administrador;
import br.com.reserva.models.Aluno;
import br.com.reserva.models.Professor;
import br.com.reserva.models.Usuario;
import br.com.reserva.services.AuthServiceImpl;
import br.com.reserva.utils.Cargos;

@RestController
@RequestMapping("api/login")
@CrossOrigin(origins = "*")
public class AuthController {
	
	@Autowired
	AuthServiceImpl service;
	@Autowired
	Facade facade;

	@PostMapping
	@Operation(summary = "Autentica um usuário", description = "Realiza a autenticação de um usuário e retorna os detalhes do usuário com base no cargo",
			tags = {"Login"},
			responses = {
					@ApiResponse(description = "OK - Autenticado", responseCode = "200",
							content = @Content(
									mediaType = "application/json",
									schema = @Schema(implementation = UsuarioVO.class)
							)),
					@ApiResponse(description = "Unauthorized - Credenciais inválidas", responseCode = "401", content = @Content),
					@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
			}
	)
	public ResponseEntity<Usuario> login(@RequestBody Usuario usuario) {
		
		Usuario usrLogado = service.autenticar(usuario.getEmail(),usuario.getSenha());
		if(usrLogado != null) {
			if(usrLogado.getCargo() == Cargos.ADMINISTRADOR) {
				var adm = facade.getByIdAdm(usrLogado.getId());
				var admResponse = ModelMapper.parseObject(adm, Administrador.class);
				return ResponseEntity.status(HttpStatus.OK).body(admResponse);
			}
			else if(usrLogado.getCargo() == Cargos.PROFESSOR) {
				var prof = facade.getByIdProfessor(usrLogado.getId());
				var profResponse = ModelMapper.parseObject(prof, Professor.class);
				return ResponseEntity.status(HttpStatus.OK).body(profResponse);
			}
			else if(usrLogado.getCargo() == Cargos.ALUNO) {
				var aluno = facade.getByIdAluno(usrLogado.getId());
				var alunoResponse = ModelMapper.parseObject(aluno, Aluno.class);
				return ResponseEntity.status(HttpStatus.OK).body(alunoResponse);
			}
		}
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(usrLogado);
	}

}

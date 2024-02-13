package br.com.reserva.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.reserva.excepions.AutenticacaoException;
import br.com.reserva.models.Usuario;
import br.com.reserva.repositories.UsuarioRepository;

@Service
public class AuthService {

	@Autowired
	UsuarioRepository repository;

	public Usuario autenticar(String email, String senha) {
		System.out.println("email: " + email + "\nsenha: " + senha);
		for (Usuario u : repository.findAll()) {
			if (u.getEmail().equals(email) && u.getSenha().equals(senha)) {
				return u;
			}
		}
		throw new AutenticacaoException("Falha na autenticação. Email ou senha inválidos.");
	}
}

package br.com.reserva.services;

import br.com.reserva.models.Usuario;

public interface AuthService {
    Usuario autenticar(String email, String senha);
}

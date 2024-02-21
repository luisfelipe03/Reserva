package br.com.reserva.data.vo;

import br.com.reserva.utils.Cargos;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;

import java.util.Objects;

public class UsuarioVO {

    private long id;
    private String nome;
    private String cpf;
    private String email;
    private String senha;
    private Cargos cargo;

    public UsuarioVO () {}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Cargos getCargo() {
        return cargo;
    }

    public void setCargo(Cargos cargo) {
        this.cargo = cargo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UsuarioVO usuarioVO)) return false;
        return id == usuarioVO.id && Objects.equals(nome, usuarioVO.nome) && Objects.equals(cpf, usuarioVO.cpf) && Objects.equals(email, usuarioVO.email) && Objects.equals(senha, usuarioVO.senha) && cargo == usuarioVO.cargo;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, cpf, email, senha, cargo);
    }

    @Override
    public String toString() {
        return "UsuarioVO{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", cpf='" + cpf + '\'' +
                ", email='" + email + '\'' +
                ", senha='" + senha + '\'' +
                ", cargo=" + cargo +
                '}';
    }
}

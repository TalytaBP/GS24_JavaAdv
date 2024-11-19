package br.com.fiap.eletriz.model;

import jakarta.persistence.*;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
import java.util.UUID;

@Entity
@Table (name = "GS_USUARIO_LOGIN")
public class UsuarioLoginModel extends RepresentationModel<UsuarioLoginModel> implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column
    @GeneratedValue(strategy =  GenerationType.AUTO)
    private UUID id_usuario_login;

    @Column
    private String email;

    @Column
    private String senha;

    public UUID getId_usuario_login() {
        return id_usuario_login;
    }

    public void setId_usuario_login(UUID id_usuario_login) {
        this.id_usuario_login = id_usuario_login;
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
}

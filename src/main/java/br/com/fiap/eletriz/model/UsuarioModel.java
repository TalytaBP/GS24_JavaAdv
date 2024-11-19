package br.com.fiap.eletriz.model;

import jakarta.persistence.*;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
import java.util.UUID;


@Entity
@Table(name = "GS_USUARIO")
public class UsuarioModel extends RepresentationModel<UsuarioModel> implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column
    @GeneratedValue(strategy =  GenerationType.AUTO)
    private UUID id_usuario;

    @Column
    private String nm_usuario;

    @Column(columnDefinition = "NUMBER(11,0)")
    private Long nr_cpf;

    @Column
    private int qt_pessoas_casa;

    public UUID getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(UUID id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getNm_usuario() {
        return nm_usuario;
    }

    public void setNm_usuario(String nm_usuario) {
        this.nm_usuario = nm_usuario;
    }

    public Long getNr_cpf() {
        return nr_cpf;
    }

    public void setNr_cpf(Long nr_cpf) {
        this.nr_cpf = nr_cpf;
    }

    public int getQt_pessoas_casa() {
        return qt_pessoas_casa;
    }

    public void setQt_pessoas_casa(int qt_pessoas_casa) {
        this.qt_pessoas_casa = qt_pessoas_casa;
    }
}

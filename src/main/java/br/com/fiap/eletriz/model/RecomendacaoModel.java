package br.com.fiap.eletriz.model;

import jakarta.persistence.*;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
import java.util.UUID;

@Entity
@Table (name = "GS_RECOMENDACAO")
public class RecomendacaoModel extends RepresentationModel<RecomendacaoModel> implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column
    @GeneratedValue(strategy =  GenerationType.AUTO)
    private UUID id_recomendacao;

    @Column
    private int gasto_total_recomendado;

    @Column
    private int tempo_uso_recomendado;

    public UUID getId_recomendacao() {
        return id_recomendacao;
    }

    public void setId_recomendacao(UUID id_recomendacao) {
        this.id_recomendacao = id_recomendacao;
    }

    public int getGasto_total_recomendado() {
        return gasto_total_recomendado;
    }

    public void setGasto_total_recomendado(int gasto_total_recomendado) {
        this.gasto_total_recomendado = gasto_total_recomendado;
    }

    public int getTempo_uso_recomendado() {
        return tempo_uso_recomendado;
    }

    public void setTempo_uso_recomendado(int tempo_uso_recomendado) {
        this.tempo_uso_recomendado = tempo_uso_recomendado;
    }
}

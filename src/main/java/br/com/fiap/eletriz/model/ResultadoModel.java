package br.com.fiap.eletriz.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "GS_RESULTADO")
public class ResultadoModel extends RepresentationModel<ResultadoModel> implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column
    @GeneratedValue(strategy =  GenerationType.AUTO)
    private UUID id_resultado;

    @Column
    private int gasto_total_energia;

    @Column
    private int comparacao_mensal;

    public UUID getId_resultado() {
        return id_resultado;
    }

    public void setId_resultado(UUID id_resultado) {
        this.id_resultado = id_resultado;
    }

    public int getGasto_total_energia() {
        return gasto_total_energia;
    }

    public void setGasto_total_energia(int gasto_total_energia) {
        this.gasto_total_energia = gasto_total_energia;
    }

    public int getComparacao_mensal() {
        return comparacao_mensal;
    }

    public void setComparacao_mensal(int comparacao_mensal) {
        this.comparacao_mensal = comparacao_mensal;
    }
}

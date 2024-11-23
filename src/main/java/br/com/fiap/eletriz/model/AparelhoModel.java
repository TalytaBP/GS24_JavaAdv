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
@Table (name = "GS_APARELHO")
public class AparelhoModel extends RepresentationModel<AparelhoModel> implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column
    @GeneratedValue(strategy =  GenerationType.AUTO)
    private UUID id_aparelho;

    @Column
    private int tempo_uso;

    public UUID getId_aparelho() {
        return id_aparelho;
    }

    public void setId_aparelho(UUID id_aparelho) {
        this.id_aparelho = id_aparelho;
    }

    public int getTempo_uso() {
        return tempo_uso;
    }

    public void setTempo_uso(int tempo_uso) {
        this.tempo_uso = tempo_uso;
    }
}

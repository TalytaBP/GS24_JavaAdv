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
@Table (name ="GS_TIPO_APARELHO")
public class TipoAparelhoModel extends RepresentationModel<TipoAparelhoModel> implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column
    @GeneratedValue(strategy =  GenerationType.AUTO)
    private UUID id_tp_aparelho;

    @Column
    private String nm_aparelho;

    public UUID getId_tp_aparelho() {
        return id_tp_aparelho;
    }

    public void setId_tp_aparelho(UUID id_tp_aparelho) {
        this.id_tp_aparelho = id_tp_aparelho;
    }

    public String getNm_aparelho() {
        return nm_aparelho;
    }

    public void setNm_aparelho(String nm_aparelho) {
        this.nm_aparelho = nm_aparelho;
    }
}

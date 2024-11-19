package br.com.fiap.eletriz.model;

import jakarta.persistence.*;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
import java.util.UUID;

@Entity
@Table (name = "GS_TIPO_GASTO")
public class TipoGastoModel extends RepresentationModel<TipoGastoModel> implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column
    @GeneratedValue(strategy =  GenerationType.AUTO)
    private UUID id_tp_gasto;

    @Column
    private String tp_gasto;

    public UUID getId_tp_gasto() {
        return id_tp_gasto;
    }

    public void setId_tp_gasto(UUID id_tp_gasto) {
        this.id_tp_gasto = id_tp_gasto;
    }

    public String getTp_gasto() {
        return tp_gasto;
    }

    public void setTp_gasto(String tp_gasto) {
        this.tp_gasto = tp_gasto;
    }
}

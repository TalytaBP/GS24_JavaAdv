package br.com.fiap.eletriz.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table (name= "GS_HISTORICO_LOGIN")
public class HistoricoLoginModel  extends RepresentationModel<HistoricoLoginModel> implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column
    @GeneratedValue(strategy =  GenerationType.AUTO)
    private UUID id_hist_login;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime dt_inicio_login;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime dt_final_login;

    public UUID getId_hist_login() {
        return id_hist_login;
    }

    public void setId_hist_login(UUID id_hist_login) {
        this.id_hist_login = id_hist_login;
    }

    public LocalDateTime getDt_inicio_login() {
        return dt_inicio_login;
    }

    public void setDt_inicio_login(LocalDateTime dt_inicio_login) {
        this.dt_inicio_login = dt_inicio_login;
    }

    public LocalDateTime getDt_final_login() {
        return dt_final_login;
    }

    public void setDt_final_login(LocalDateTime dt_final_login) {
        this.dt_final_login = dt_final_login;
    }
}

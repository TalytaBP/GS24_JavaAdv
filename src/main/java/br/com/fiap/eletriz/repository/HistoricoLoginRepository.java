package br.com.fiap.eletriz.repository;

import br.com.fiap.eletriz.model.HistoricoLoginModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface HistoricoLoginRepository extends JpaRepository<HistoricoLoginModel, UUID> {
}

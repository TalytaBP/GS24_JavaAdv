package br.com.fiap.eletriz.repository;

import br.com.fiap.eletriz.model.TipoGastoModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TipoGastoRepository extends JpaRepository<TipoGastoModel, UUID> {
}

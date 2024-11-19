package br.com.fiap.eletriz.repository;

import br.com.fiap.eletriz.model.ResultadoModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ResultadoRepository extends JpaRepository<ResultadoModel, UUID> {
}

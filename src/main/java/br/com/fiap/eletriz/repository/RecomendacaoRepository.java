package br.com.fiap.eletriz.repository;

import br.com.fiap.eletriz.model.RecomendacaoModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RecomendacaoRepository extends JpaRepository<RecomendacaoModel, UUID> {
}

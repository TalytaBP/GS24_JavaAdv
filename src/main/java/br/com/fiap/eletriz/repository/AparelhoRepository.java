package br.com.fiap.eletriz.repository;

import br.com.fiap.eletriz.model.AparelhoModel;
import br.com.fiap.eletriz.model.UsuarioModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AparelhoRepository extends JpaRepository<AparelhoModel, UUID> {
}

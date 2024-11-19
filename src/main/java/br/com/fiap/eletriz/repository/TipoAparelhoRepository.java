package br.com.fiap.eletriz.repository;

import br.com.fiap.eletriz.model.TipoAparelhoModel;
import br.com.fiap.eletriz.model.UsuarioModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TipoAparelhoRepository extends JpaRepository<TipoAparelhoModel, UUID> {
}

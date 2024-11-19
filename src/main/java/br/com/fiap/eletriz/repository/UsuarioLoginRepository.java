package br.com.fiap.eletriz.repository;

import br.com.fiap.eletriz.model.UsuarioLoginModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UsuarioLoginRepository extends JpaRepository <UsuarioLoginModel, UUID> {
}

package br.com.fiap.eletriz.service;

import br.com.fiap.eletriz.model.UsuarioModel;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UsuarioService {

    UsuarioModel salvar (UsuarioModel usuarioModel);

    Optional<UsuarioModel> buscarPorId(UUID id);

    List<UsuarioModel> listarTodos();

    UsuarioModel atualizar(UUID id, UsuarioModel usuarioModel);

    void deletar (UUID id);
}

package br.com.fiap.eletriz.service;

import br.com.fiap.eletriz.model.UsuarioLoginModel;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UsuarioLoginService {

    UsuarioLoginModel salvar (UsuarioLoginModel usuarioLoginModel);

    Optional<UsuarioLoginModel> buscarporId (UUID id);

    List<UsuarioLoginModel> listarTodos();

    UsuarioLoginModel atualizar(UUID id, UsuarioLoginModel usuarioLoginModel);

    void deletar (UUID id);
}

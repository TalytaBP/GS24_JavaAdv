package br.com.fiap.eletriz.service;

import br.com.fiap.eletriz.model.AparelhoModel;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AparelhoService {

    AparelhoModel salvar (AparelhoModel aparelho);

    Optional<AparelhoModel> buscarPorId (UUID id);

    List<AparelhoModel> listarTodos();

    AparelhoModel atualizar (UUID id, AparelhoModel aparelho);

    void remover (UUID id);
}

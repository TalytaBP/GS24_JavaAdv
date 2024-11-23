package br.com.fiap.eletriz.service;

import br.com.fiap.eletriz.model.TipoAparelhoModel;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TipoAparelhoService {

    TipoAparelhoModel salvar (TipoAparelhoModel tipoAparelhoModel);

    Optional<TipoAparelhoModel> buscarPorId (UUID id);

    List<TipoAparelhoModel> listarTodos();

    TipoAparelhoModel atualizar (UUID id, TipoAparelhoModel tipoAparelhoModel);

    void remover (UUID id);
}

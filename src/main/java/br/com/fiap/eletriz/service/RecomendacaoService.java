package br.com.fiap.eletriz.service;

import br.com.fiap.eletriz.model.RecomendacaoModel;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RecomendacaoService {

    RecomendacaoModel salvar(RecomendacaoModel recomendacaoModel);

    Optional<RecomendacaoModel> buscarPorId(UUID id);

    List<RecomendacaoModel> listarTodos();

    RecomendacaoModel atualizar(UUID id, RecomendacaoModel recomendacaoModel);

    void remover(UUID id);
}

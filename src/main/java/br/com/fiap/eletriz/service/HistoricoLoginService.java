package br.com.fiap.eletriz.service;

import br.com.fiap.eletriz.model.HistoricoLoginModel;
import br.com.fiap.eletriz.model.RecomendacaoModel;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface HistoricoLoginService {

    HistoricoLoginModel salvar(HistoricoLoginModel historicoLoginModel);

    Optional<HistoricoLoginModel> buscarPorId (UUID id);

    List<HistoricoLoginModel> listarTodos();

    HistoricoLoginModel atualizar (UUID id, HistoricoLoginModel historicoLoginModel);

    void remover (UUID id);
}

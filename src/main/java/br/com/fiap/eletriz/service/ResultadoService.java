package br.com.fiap.eletriz.service;

import br.com.fiap.eletriz.model.ResultadoModel;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ResultadoService {

    ResultadoModel salvar (ResultadoModel resultadoModel);

    Optional<ResultadoModel> buscarPorId (UUID id);

    List<ResultadoModel> listarTodos();

    ResultadoModel atualizar (UUID id, ResultadoModel resultadoModel);

    void remover (UUID id);
}

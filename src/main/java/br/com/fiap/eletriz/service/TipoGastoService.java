package br.com.fiap.eletriz.service;

import br.com.fiap.eletriz.model.TipoGastoModel;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TipoGastoService {

    TipoGastoModel salvar (TipoGastoModel tipoGastoModel);

    Optional<TipoGastoModel> buscarPorId(UUID id);

    List<TipoGastoModel> listarTodos();

    TipoGastoModel atualizar (UUID id , TipoGastoModel tipoGastoModel);

    void remover (UUID id);


}

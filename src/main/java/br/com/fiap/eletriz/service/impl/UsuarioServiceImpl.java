package br.com.fiap.eletriz.service.impl;


import br.com.fiap.eletriz.model.UsuarioModel;
import br.com.fiap.eletriz.repository.UsuarioRepository;
import br.com.fiap.eletriz.service.UsuarioService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UsuarioModel salvar(UsuarioModel usuarioModel) {
        return usuarioRepository.save(usuarioModel);
    }

    @Override
    public Optional<UsuarioModel> buscarPorId(UUID id) {
        return usuarioRepository.findById(id);
    }


    @Override
    public List<UsuarioModel> listarTodos() {
        return usuarioRepository.findAll();
    }

    @Override
    public UsuarioModel atualizar(UUID id, UsuarioModel usuarioModel) {
        return usuarioRepository.findById(id).map(usuario ->{
            return usuarioRepository.save(usuarioModel);
        }).orElseThrow(() -> new EntityNotFoundException("Usuário com ID" + id + "não encontrada"));
    }

    @Override
    public void deletar(UUID id) {
        if (usuarioRepository.existsById(id)) {
            usuarioRepository.deleteById(id);
        }
        else {
            throw new EntityNotFoundException("Usuario com ID" + id + "não encontrada");
        }

    }


}

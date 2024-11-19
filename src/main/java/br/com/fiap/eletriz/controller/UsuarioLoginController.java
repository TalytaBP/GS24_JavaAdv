package br.com.fiap.eletriz.controller;

import br.com.fiap.eletriz.dto.UsuarioLoginDto;
import br.com.fiap.eletriz.model.UsuarioLoginModel;
import br.com.fiap.eletriz.repository.UsuarioLoginRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class UsuarioLoginController {

    @Autowired
    UsuarioLoginRepository usuarioLoginRepository;

    @PostMapping("/usuario_login")
    public ResponseEntity<UsuarioLoginModel> saveUsuarioLogin(@RequestBody @Valid UsuarioLoginDto usuarioLoginDto) {
        var usuarioLoginModel = new UsuarioLoginModel();
        BeanUtils.copyProperties(usuarioLoginDto, usuarioLoginModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioLoginRepository.save(usuarioLoginModel));
    }
    @GetMapping("/usuario_login")
    public ResponseEntity<List<UsuarioLoginModel>> getAllUsuarioLogin(){
        List<UsuarioLoginModel> usuarioLoginList = usuarioLoginRepository.findAll();
        if (!usuarioLoginList.isEmpty()) {
            for (UsuarioLoginModel usuarioLogin : usuarioLoginList) {
                UUID id = usuarioLogin.getId_usuario_login();
                usuarioLogin.add(linkTo(methodOn(UsuarioLoginController.class).getOneUsuarioLogin(id)).withSelfRel());
            }
        }

        return ResponseEntity.status(HttpStatus.OK).body(usuarioLoginList);
    }

    @GetMapping("/usuario_login/{id}")
    public ResponseEntity<Object> getOneUsuarioLogin(@PathVariable(value = "id") UUID id) {
        Optional<UsuarioLoginModel> usuarioLoginO = usuarioLoginRepository.findById(id);
        if (usuarioLoginO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario de login não foi encontrado.");
        }

        usuarioLoginO.get().add(linkTo(methodOn(UsuarioLoginController.class).getAllUsuarioLogin()).withRel("Lista de usuarios"));


        return ResponseEntity.status(HttpStatus.OK).body(usuarioLoginO.get());
    }
    @PutMapping("/usuario_login/{id}")
    public ResponseEntity<Object> updateUsuarioLogin(@PathVariable(value="id") UUID id, @RequestBody @Valid UsuarioLoginDto usuarioLoginDto) {

        Optional<UsuarioLoginModel> usuarioLogin0 = usuarioLoginRepository.findById(id);
        if (usuarioLogin0.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Login do usuário não foi encontrado");
        }
        var usuarioLoginModel = usuarioLogin0.get();
        BeanUtils.copyProperties(usuarioLoginDto, usuarioLoginModel);
        return ResponseEntity.status(HttpStatus.OK).body(usuarioLoginRepository.save(usuarioLoginModel));
    }
    @DeleteMapping("/usuario_login/{id}")
    public ResponseEntity<Object> deleteUsuarioLogin(@PathVariable(value = "id") UUID id) {
        Optional<UsuarioLoginModel> usuarioLogin0 = usuarioLoginRepository.findById(id);
        if (usuarioLogin0.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Login do usuário não foi encontrado");
        }
        usuarioLoginRepository.delete(usuarioLogin0.get());
        return ResponseEntity.status(HttpStatus.OK).body("Login do Usuário foi deletado com sucesso.");
    }

}

package br.com.fiap.eletriz.controller;

import br.com.fiap.eletriz.dto.UsuarioDto;
import br.com.fiap.eletriz.model.UsuarioModel;
import br.com.fiap.eletriz.repository.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class UsuarioController {


        @Autowired
        UsuarioRepository usuarioRepository;

        @PostMapping("/usuario")
        public ResponseEntity<UsuarioModel> saveUsuario(@RequestBody @Valid UsuarioDto usuarioDto) {
            var usuarioModel = new UsuarioModel();
            System.out.println(usuarioModel);
            BeanUtils.copyProperties(usuarioDto, usuarioModel);
            return ResponseEntity.status(HttpStatus.CREATED).body(usuarioRepository.save(usuarioModel));
        }
        @GetMapping("/usuario")
        public ResponseEntity<List<UsuarioModel>> getAllUsuario(){
            List<UsuarioModel> usuarioList = usuarioRepository.findAll();
            if (!usuarioList.isEmpty()) {
                for (UsuarioModel usuario : usuarioList) {
                    UUID id = usuario.getId_usuario();
                    usuario.add(linkTo(methodOn(UsuarioController.class).getOneUsuario(id)).withSelfRel());
                }
            }

            return ResponseEntity.status(HttpStatus.OK).body(usuarioList);
        }

        @GetMapping("/usuario/{id}")
        public ResponseEntity<Object> getOneUsuario(@PathVariable(value = "id") UUID id) {
            Optional<UsuarioModel> usuarioO = usuarioRepository.findById(id);
            if (usuarioO.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario não encontrado.");
            }

            usuarioO.get().add(linkTo(methodOn(UsuarioController.class).getAllUsuario()).withRel("Lista de usuarios"));


            return ResponseEntity.status(HttpStatus.OK).body(usuarioO.get());
        }
        @PutMapping("/usuario/{id}")
        public ResponseEntity<Object> updateUsuario(@PathVariable(value="id") UUID id, @RequestBody @Valid UsuarioDto usuarioDto) {

            Optional<UsuarioModel> usuario0 = usuarioRepository.findById(id);
            if (usuario0.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado");
            }
            var usuarioModel = usuario0.get();
            BeanUtils.copyProperties(usuarioDto, usuarioModel);
            return ResponseEntity.status(HttpStatus.OK).body(usuarioRepository.save(usuarioModel));
        }
        @DeleteMapping("/usuario/{id}")
        public ResponseEntity<Object> deleteUsuario(@PathVariable(value = "id") UUID id) {
            Optional<UsuarioModel> usuario0 = usuarioRepository.findById(id);
            if (usuario0.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado");
            }
            usuarioRepository.delete(usuario0.get());
            return ResponseEntity.status(HttpStatus.OK).body("Usuário deletado com sucesso.");
        }


}
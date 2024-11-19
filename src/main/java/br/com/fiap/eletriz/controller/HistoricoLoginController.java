package br.com.fiap.eletriz.controller;

import br.com.fiap.eletriz.dto.HistoricoLoginDto;
import br.com.fiap.eletriz.model.HistoricoLoginModel;
import br.com.fiap.eletriz.repository.HistoricoLoginRepository;
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
public class HistoricoLoginController {
    @Autowired
    HistoricoLoginRepository historicoLoginRepository;

    @PostMapping("/historico_login")
    public ResponseEntity<HistoricoLoginModel> saveHistoricoLogin(@RequestBody @Valid HistoricoLoginDto historicoLoginDto) {
        var historicoLoginModel = new HistoricoLoginModel();
        BeanUtils.copyProperties(historicoLoginDto, historicoLoginModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(historicoLoginRepository.save(historicoLoginModel));
    }
    @GetMapping("/historico_login")
    public ResponseEntity<List<HistoricoLoginModel>> getAllHistoricoLogin(){
        List<HistoricoLoginModel> historicoLoginList = historicoLoginRepository.findAll();
        if (!historicoLoginList.isEmpty()) {
            for (HistoricoLoginModel historicoLogin : historicoLoginList) {
                UUID id = historicoLogin.getId_hist_login();
                historicoLogin.add(linkTo(methodOn(HistoricoLoginController.class).getOneHistoricoLogin(id)).withSelfRel());
            }
        }

        return ResponseEntity.status(HttpStatus.OK).body(historicoLoginList);
    }

    @GetMapping("/historico_login/{id}")
    public ResponseEntity<Object> getOneHistoricoLogin(@PathVariable(value = "id") UUID id) {
        Optional<HistoricoLoginModel> historicoLoginO = historicoLoginRepository.findById(id);
        if (historicoLoginO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Historico do login não encontrado.");
        }

        historicoLoginO.get().add(linkTo(methodOn(HistoricoLoginController.class).getAllHistoricoLogin()).withRel("Lista de historico de login"));


        return ResponseEntity.status(HttpStatus.OK).body(historicoLoginO.get());
    }
    @PutMapping("/historico_login/{id}")
    public ResponseEntity<Object> updateHistoricoLogin(@PathVariable(value="id") UUID id, @RequestBody @Valid HistoricoLoginDto historicoLoginDto) {

        Optional<HistoricoLoginModel> historicoLogin0 = historicoLoginRepository.findById(id);
        if (historicoLogin0.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Historico do login não foi encontrado");
        }
        var historicoLoginModel = historicoLogin0.get();
        BeanUtils.copyProperties(historicoLoginDto, historicoLoginModel);
        return ResponseEntity.status(HttpStatus.OK).body(historicoLoginRepository.save(historicoLoginModel));
    }
    @DeleteMapping("/historico_login/{id}")
    public ResponseEntity<Object> deleteHistoricoLogin(@PathVariable(value = "id") UUID id) {
        Optional<HistoricoLoginModel> historicoLogin0 = historicoLoginRepository.findById(id);
        if (historicoLogin0.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Historico do login não foi encontrado");
        }
        historicoLoginRepository.delete(historicoLogin0.get());
        return ResponseEntity.status(HttpStatus.OK).body("Historico de login  foi deletado com sucesso.");
    }
}

package br.com.fiap.eletriz.controller;

import br.com.fiap.eletriz.dto.AparelhoDto;
import br.com.fiap.eletriz.dto.UsuarioDto;
import br.com.fiap.eletriz.model.AparelhoModel;
import br.com.fiap.eletriz.repository.AparelhoRepository;
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
public class AparelhoController {

    @Autowired
    AparelhoRepository aparelhoRepository;

    @PostMapping("/aparelho")
    public ResponseEntity<AparelhoModel> saveAparelho(@RequestBody @Valid AparelhoDto aparelhoDto) {
        var aparelhoModel = new AparelhoModel();
        BeanUtils.copyProperties(aparelhoDto, aparelhoModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(aparelhoRepository.save(aparelhoModel));
    }
    @GetMapping("/aparelho")
    public ResponseEntity<List<AparelhoModel>> getAllAparelho(){
        List<AparelhoModel> aparelhoList = aparelhoRepository.findAll();
        if (!aparelhoList.isEmpty()) {
            for (AparelhoModel aparelho : aparelhoList) {
                UUID id = aparelho.getId_aparelho();
                aparelho.add(linkTo(methodOn(AparelhoController.class).getOneAparelho(id)).withSelfRel());
            }
        }

        return ResponseEntity.status(HttpStatus.OK).body(aparelhoList);
    }

    @GetMapping("/aparelho/{id}")
    public ResponseEntity<Object> getOneAparelho(@PathVariable(value = "id") UUID id) {
        Optional<AparelhoModel> aparelhoO = aparelhoRepository.findById(id);
        if (aparelhoO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Aparelho não encontrado.");
        }

        aparelhoO.get().add(linkTo(methodOn(AparelhoController.class).getAllAparelho()).withRel("Lista de aparelhos"));


        return ResponseEntity.status(HttpStatus.OK).body(aparelhoO.get());
    }
    @PutMapping("/aparelho/{id}")
    public ResponseEntity<Object> updateAparelho(@PathVariable(value="id") UUID id, @RequestBody @Valid AparelhoDto aparelhoDto) {

        Optional<AparelhoModel> aparelho0 = aparelhoRepository.findById(id);
        if (aparelho0.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Aparelho não encontrado");
        }
        var aparelhoModel = aparelho0.get();
        BeanUtils.copyProperties(aparelhoDto, aparelhoModel);
        return ResponseEntity.status(HttpStatus.OK).body(aparelhoRepository.save(aparelhoModel));
    }
    @DeleteMapping("/aparelho/{id}")
    public ResponseEntity<Object> deleteAparelho(@PathVariable(value = "id") UUID id) {
        Optional<AparelhoModel> aparelho0 = aparelhoRepository.findById(id);
        if (aparelho0.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Aparelho não encontrado");
        }
        aparelhoRepository.delete(aparelho0.get());
        return ResponseEntity.status(HttpStatus.OK).body("Aparelho deletado com sucesso.");
    }
}

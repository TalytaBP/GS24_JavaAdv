package br.com.fiap.eletriz.controller;

import br.com.fiap.eletriz.dto.AparelhoDto;
import br.com.fiap.eletriz.dto.TipoAparelhoDto;
import br.com.fiap.eletriz.dto.UsuarioDto;
import br.com.fiap.eletriz.model.AparelhoModel;
import br.com.fiap.eletriz.model.TipoAparelhoModel;
import br.com.fiap.eletriz.repository.AparelhoRepository;
import br.com.fiap.eletriz.repository.TipoAparelhoRepository;
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
public class TipoAparelhoController {

    @Autowired
    TipoAparelhoRepository tipoAparelhoRepository;

    @PostMapping("/tipo_aparelho")
    public ResponseEntity<TipoAparelhoModel> saveTipoAparelho(@RequestBody @Valid TipoAparelhoDto tipoAparelhoDto) {
        var tipoAparelhoModel = new TipoAparelhoModel();
        BeanUtils.copyProperties(tipoAparelhoDto, tipoAparelhoModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(tipoAparelhoRepository.save(tipoAparelhoModel));
    }
    @GetMapping("/tipo_aparelho")
    public ResponseEntity<List<TipoAparelhoModel>> getAllTipoAparelho(){
        List<TipoAparelhoModel> tipoAparelhoList = tipoAparelhoRepository.findAll();
        if (!tipoAparelhoList.isEmpty()) {
            for (TipoAparelhoModel tipoAparelho : tipoAparelhoList) {
                UUID id = tipoAparelho.getId_tp_aparelho();
                tipoAparelho.add(linkTo(methodOn(AparelhoController.class).getOneAparelho(id)).withSelfRel());
            }
        }

        return ResponseEntity.status(HttpStatus.OK).body(tipoAparelhoList);
    }

    @GetMapping("/tipo_aparelho/{id}")
    public ResponseEntity<Object> getOneTipoAparelho(@PathVariable(value = "id") UUID id) {
        Optional<TipoAparelhoModel> tipoAparelhoO = tipoAparelhoRepository.findById(id);
        if (tipoAparelhoO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tipo de aparelho não encontrado.");
        }

        tipoAparelhoO.get().add(linkTo(methodOn(TipoAparelhoController.class).getAllTipoAparelho()).withRel("Lista de tipos de aparelhos"));


        return ResponseEntity.status(HttpStatus.OK).body(tipoAparelhoO.get());
    }
    @PutMapping("/tipo_aparelho/{id}")
    public ResponseEntity<Object> updateTipoAparelho(@PathVariable(value="id") UUID id, @RequestBody @Valid TipoAparelhoDto tipoAparelhoDto) {

        Optional<TipoAparelhoModel> tipoAparelho0 = tipoAparelhoRepository.findById(id);
        if (tipoAparelho0.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tipo de aparelho não encontrado");
        }
        var tipoAparelhoModel = tipoAparelho0.get();
        BeanUtils.copyProperties(tipoAparelhoDto, tipoAparelhoModel);
        return ResponseEntity.status(HttpStatus.OK).body(tipoAparelhoRepository.save(tipoAparelhoModel));
    }
    @DeleteMapping("/tipo_aparelho/{id}")
    public ResponseEntity<Object> deleteTipoAparelho(@PathVariable(value = "id") UUID id) {
        Optional<TipoAparelhoModel> tipoAparelho0 = tipoAparelhoRepository.findById(id);
        if (tipoAparelho0.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tipo de aparelho não encontrado");
        }
        tipoAparelhoRepository.delete(tipoAparelho0.get());
        return ResponseEntity.status(HttpStatus.OK).body("Tipo de aparelho deletado com sucesso.");
    }
}

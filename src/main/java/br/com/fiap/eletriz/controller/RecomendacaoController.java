package br.com.fiap.eletriz.controller;

import br.com.fiap.eletriz.dto.RecomendacaoDto;
import br.com.fiap.eletriz.model.RecomendacaoModel;
import br.com.fiap.eletriz.repository.RecomendacaoRepository;
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
public class RecomendacaoController {

    @Autowired
    RecomendacaoRepository recomendacaoRepository;

    @PostMapping("/dicas")
    public ResponseEntity<RecomendacaoModel> saveRecomendacao(@RequestBody @Valid RecomendacaoDto recomendacaoDto) {
        var recomendacaoModel = new RecomendacaoModel();
        BeanUtils.copyProperties(recomendacaoDto, recomendacaoModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(recomendacaoRepository.save(recomendacaoModel));
    }
    @GetMapping("/dicas")
    public ResponseEntity<List<RecomendacaoModel>> getAllRecomendacao(){
        List<RecomendacaoModel> recomendacaoList = recomendacaoRepository.findAll();
        if (!recomendacaoList.isEmpty()) {
            for (RecomendacaoModel recomendacao : recomendacaoList) {
                UUID id = recomendacao.getId_recomendacao();
                recomendacao.add(linkTo(methodOn(RecomendacaoController.class).getOneRecomendacao(id)).withSelfRel());
            }
        }

        return ResponseEntity.status(HttpStatus.OK).body(recomendacaoList);
    }

    @GetMapping("/dicas/{id}")
    public ResponseEntity<Object> getOneRecomendacao(@PathVariable(value = "id") UUID id) {
        Optional<RecomendacaoModel> recomendacaoO = recomendacaoRepository.findById(id);
        if (recomendacaoO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("A dica não foi encontrada.");
        }

        recomendacaoO.get().add(linkTo(methodOn(RecomendacaoController.class).getAllRecomendacao()).withRel("Lista de dicas"));


        return ResponseEntity.status(HttpStatus.OK).body(recomendacaoO.get());
    }
    @PutMapping("/dicas/{id}")
    public ResponseEntity<Object> updateRecomendacao(@PathVariable(value="id") UUID id, @RequestBody @Valid RecomendacaoDto recomendacaoDto) {

        Optional<RecomendacaoModel> recomendacao0 = recomendacaoRepository.findById(id);
        if (recomendacao0.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Dica não encontrada");
        }
        var recomendacaoModel = recomendacao0.get();
        BeanUtils.copyProperties(recomendacaoDto, recomendacaoModel);
        return ResponseEntity.status(HttpStatus.OK).body(recomendacaoRepository.save(recomendacaoModel));
    }
    @DeleteMapping("/dicas/{id}")
    public ResponseEntity<Object> deleteRecomendacao(@PathVariable(value = "id") UUID id) {
        Optional<RecomendacaoModel> recomendacao0 = recomendacaoRepository.findById(id);
        if (recomendacao0.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Dica não encontrada");
        }
        recomendacaoRepository.delete(recomendacao0.get());
        return ResponseEntity.status(HttpStatus.OK).body("Dica deletada com sucesso.");
    }

}

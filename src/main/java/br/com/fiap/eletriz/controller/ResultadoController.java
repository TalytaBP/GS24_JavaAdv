package br.com.fiap.eletriz.controller;

import br.com.fiap.eletriz.dto.ResultadoDto;
import br.com.fiap.eletriz.model.ResultadoModel;
import br.com.fiap.eletriz.repository.ResultadoRepository;
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
public class ResultadoController {

    @Autowired
    ResultadoRepository resultadoRepository;

    @PostMapping("/resultado")
    public ResponseEntity<ResultadoModel> saveResultado(@RequestBody @Valid ResultadoDto resultadoDto) {
        var resultadoModel = new ResultadoModel();
        BeanUtils.copyProperties(resultadoDto, resultadoModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(resultadoRepository.save(resultadoModel));
    }
    @GetMapping("/resultado")
    public ResponseEntity<List<ResultadoModel>> getAllResultado(){
        List<ResultadoModel> resultadoList = resultadoRepository.findAll();
        if (!resultadoList.isEmpty()) {
            for (ResultadoModel resultado : resultadoList) {
                UUID id = resultado.getId_resultado();
                resultado.add(linkTo(methodOn(ResultadoController.class).getOneResultado(id)).withSelfRel());
            }
        }

        return ResponseEntity.status(HttpStatus.OK).body(resultadoList);
    }

    @GetMapping("/resultado/{id}")
    public ResponseEntity<Object> getOneResultado(@PathVariable(value = "id") UUID id) {
        Optional<ResultadoModel> resultadoO = resultadoRepository.findById(id);
        if (resultadoO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Resultado não encontrado.");
        }

        resultadoO.get().add(linkTo(methodOn(ResultadoController.class).getAllResultado()).withRel("Lista de resultados"));


        return ResponseEntity.status(HttpStatus.OK).body(resultadoO.get());
    }
    @PutMapping("/resultado/{id}")
    public ResponseEntity<Object> updateResultado(@PathVariable(value="id") UUID id, @RequestBody @Valid ResultadoDto resultadoDto) {

        Optional<ResultadoModel> resultado0 = resultadoRepository.findById(id);
        if (resultado0.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Resultado não encontrado");
        }
        var resultadoModel = resultado0.get();
        BeanUtils.copyProperties(resultadoDto, resultadoModel);
        return ResponseEntity.status(HttpStatus.OK).body(resultadoRepository.save(resultadoModel));
    }
    @DeleteMapping("/resultado/{id}")
    public ResponseEntity<Object> deleteResultado(@PathVariable(value = "id") UUID id) {
        Optional<ResultadoModel> resultado0 = resultadoRepository.findById(id);
        if (resultado0.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Resultado não encontrado");
        }
        resultadoRepository.delete(resultado0.get());
        return ResponseEntity.status(HttpStatus.OK).body("Resultado deletado com sucesso.");
    }
}

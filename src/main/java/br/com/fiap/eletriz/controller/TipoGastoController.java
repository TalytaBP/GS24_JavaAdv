package br.com.fiap.eletriz.controller;

import br.com.fiap.eletriz.dto.TipoGastoDto;
import br.com.fiap.eletriz.model.TipoGastoModel;
import br.com.fiap.eletriz.repository.TipoGastoRepository;
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
public class TipoGastoController {

    @Autowired
    TipoGastoRepository tipoGastoRepository;

    @PostMapping("/tipo_gasto")
    public ResponseEntity<TipoGastoModel> saveTipoGasto(@RequestBody @Valid TipoGastoDto tipoGastoDto) {
        var tipoGastoModel = new TipoGastoModel();
        BeanUtils.copyProperties(tipoGastoDto, tipoGastoModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(tipoGastoRepository.save(tipoGastoModel));
    }
    @GetMapping("/tipo_gasto")
    public ResponseEntity<List<TipoGastoModel>> getAllTipoGasto(){
        List<TipoGastoModel> tipoGastoList = tipoGastoRepository.findAll();
        if (!tipoGastoList.isEmpty()) {
            for (TipoGastoModel tipoGasto : tipoGastoList) {
                UUID id = tipoGasto.getId_tp_gasto();
                tipoGasto.add(linkTo(methodOn(TipoGastoController.class).getOneTipoGasto(id)).withSelfRel());
            }
        }

        return ResponseEntity.status(HttpStatus.OK).body(tipoGastoList);
    }

    @GetMapping("/tipo_gasto/{id}")
    public ResponseEntity<Object> getOneTipoGasto(@PathVariable(value = "id") UUID id) {
        Optional<TipoGastoModel> tipoGastoO = tipoGastoRepository.findById(id);
        if (tipoGastoO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tipo de gasto não encontrado.");
        }

        tipoGastoO.get().add(linkTo(methodOn(TipoGastoController.class).getAllTipoGasto()).withRel("Lista de tipo de gastos"));


        return ResponseEntity.status(HttpStatus.OK).body(tipoGastoO.get());
    }
    @PutMapping("/tipo_gasto/{id}")
    public ResponseEntity<Object> updateTipoGasto(@PathVariable(value="id") UUID id, @RequestBody @Valid TipoGastoDto tipoGastoDto) {

        Optional<TipoGastoModel> tipoGasto0 = tipoGastoRepository.findById(id);
        if (tipoGasto0.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tipo de gasto não encontrado");
        }
        var tipoGastoModel = tipoGasto0.get();
        BeanUtils.copyProperties(tipoGastoDto, tipoGastoModel);
        return ResponseEntity.status(HttpStatus.OK).body(tipoGastoRepository.save(tipoGastoModel));
    }
    @DeleteMapping("/tipo_gasto/{id}")
    public ResponseEntity<Object> deleteTipoGasto(@PathVariable(value = "id") UUID id) {
        Optional<TipoGastoModel> tipoGasto0 = tipoGastoRepository.findById(id);
        if (tipoGasto0.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tipo de gastos não encontrado");
        }
        tipoGastoRepository.delete(tipoGasto0.get());
        return ResponseEntity.status(HttpStatus.OK).body("Tipo de gastos deletado com sucesso.");
    }
}

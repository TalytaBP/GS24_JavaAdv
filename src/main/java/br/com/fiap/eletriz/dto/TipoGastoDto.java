package br.com.fiap.eletriz.dto;

import jakarta.validation.constraints.NotBlank;

public record TipoGastoDto(@NotBlank String tp_gasto) {
}

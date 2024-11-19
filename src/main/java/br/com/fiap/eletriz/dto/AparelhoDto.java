package br.com.fiap.eletriz.dto;

import jakarta.validation.constraints.NotNull;

public record AparelhoDto(@NotNull int tempo_uso) {
}

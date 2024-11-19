package br.com.fiap.eletriz.dto;

import jakarta.validation.constraints.NotNull;

public record ResultadoDto(@NotNull int gasto_total_energia, @NotNull int comparacao_mensal) {
}

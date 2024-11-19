package br.com.fiap.eletriz.dto;

import jakarta.validation.constraints.NotNull;

public record RecomendacaoDto(@NotNull int gasto_total_recomendacao, @NotNull int tempo_uso_recomendacao) {
}

package br.com.fiap.eletriz.dto;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record HistoricoLoginDto(@NotNull LocalDateTime dt_inicio_login, @NotNull LocalDateTime dt_final_login) {
}

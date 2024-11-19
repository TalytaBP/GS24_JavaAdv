package br.com.fiap.eletriz.dto;

import jakarta.validation.constraints.NotBlank;

public record TipoAparelhoDto(@NotBlank String nm_aparelho) {
}

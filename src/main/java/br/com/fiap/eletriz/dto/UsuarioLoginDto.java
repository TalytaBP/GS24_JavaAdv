package br.com.fiap.eletriz.dto;

import jakarta.validation.constraints.NotBlank;

public record UsuarioLoginDto(@NotBlank String email, @NotBlank String senha) {
}

package br.com.fiap.eletriz.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UsuarioDto(@NotBlank String nm_usuario, @NotBlank String nr_cpf, @NotNull int qt_pessoas_casa) {
}

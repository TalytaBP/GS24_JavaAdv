package br.com.fiap.eletriz.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UsuarioDto(@NotBlank String nm_usuario, @NotNull Long nr_cpf, @NotNull int qt_pessoas_casa) {
}

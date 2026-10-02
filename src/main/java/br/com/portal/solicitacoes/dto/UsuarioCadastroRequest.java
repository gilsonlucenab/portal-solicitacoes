package br.com.portal.solicitacoes.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UsuarioCadastroRequest(
        @NotBlank
        @Size(max = 100)
        String nome,

        @NotBlank
        @Size(min = 3, max = 50)
        String usuario,

        @NotBlank
        @Size(min = 6, max = 72)
        String senha
) {
}
package br.com.portal.solicitacoes.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record SolicitacaoRequest(
        @NotBlank
        @Size(max = 150)
        String titulo,

        @NotBlank
        String descricao,

        @NotNull
        Categoria categoria
) {
    public enum Categoria {
        TI,
        RH,
        COMPRAS,
        FINANCEIRO,
        INFRAESTRUTURA
    }
}
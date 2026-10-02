package br.com.portal.solicitacoes.dto;

import java.time.LocalDateTime;

public record SolicitacaoResponse(
        Long id,
        String titulo,
        String descricao,
        String categoria,
        String status,
        LocalDateTime dataCriacao,
        Long solicitanteId,
        String solicitanteNome
) {
}
package br.com.portal.solicitacoes.controller;

import br.com.portal.solicitacoes.dto.SolicitacaoRequest;
import br.com.portal.solicitacoes.dto.SolicitacaoResponse;
import br.com.portal.solicitacoes.entity.Solicitacao;
import br.com.portal.solicitacoes.service.SolicitacaoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/solicitacoes")
public class SolicitacaoController {

    private final SolicitacaoService solicitacaoService;

    public SolicitacaoController(
            SolicitacaoService solicitacaoService) {
        this.solicitacaoService = solicitacaoService;
    }

    @GetMapping
    public List<SolicitacaoResponse> listarTodas() {
        return solicitacaoService.listarTodas()
                .stream()
                .map(this::converterParaResponse)
                .toList();
    }

    @GetMapping("/{id}")
    public SolicitacaoResponse buscarPorId(@PathVariable Long id) {
        return converterParaResponse(
                solicitacaoService.buscarPorId(id)
        );
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SolicitacaoResponse criar(
            @Valid @RequestBody SolicitacaoRequest request,
            Authentication authentication) {

        Solicitacao solicitacao = solicitacaoService.criar(
                request,
                authentication.getName()
        );

        return converterParaResponse(solicitacao);
    }

    private SolicitacaoResponse converterParaResponse(
            Solicitacao solicitacao) {

        return new SolicitacaoResponse(
                solicitacao.getId(),
                solicitacao.getTitulo(),
                solicitacao.getDescricao(),
                solicitacao.getCategoria(),
                solicitacao.getStatus(),
                solicitacao.getDataCriacao(),
                solicitacao.getSolicitante().getId(),
                solicitacao.getSolicitante().getNome()
        );
    }
}
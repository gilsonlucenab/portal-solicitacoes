package br.com.portal.solicitacoes.service;

import br.com.portal.solicitacoes.dto.SolicitacaoRequest;
import br.com.portal.solicitacoes.entity.Solicitacao;
import br.com.portal.solicitacoes.entity.Usuario;
import br.com.portal.solicitacoes.repository.SolicitacaoRepository;
import br.com.portal.solicitacoes.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SolicitacaoService {

    private final SolicitacaoRepository solicitacaoRepository;
    private final UsuarioRepository usuarioRepository;

    public SolicitacaoService(
            SolicitacaoRepository solicitacaoRepository,
            UsuarioRepository usuarioRepository) {
        this.solicitacaoRepository = solicitacaoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public List<Solicitacao> listarTodas() {
        return solicitacaoRepository.findAll();
    }

    public Solicitacao buscarPorId(Long id) {
        return solicitacaoRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Solicitação não encontrada"));
    }

    @Transactional
    public Solicitacao criar(
            SolicitacaoRequest request,
            String loginUsuario) {

        Usuario usuario = usuarioRepository.findByUsuario(loginUsuario)
                .orElseThrow(() ->
                        new RuntimeException("Usuário não encontrado"));

        Solicitacao solicitacao = new Solicitacao();
        solicitacao.setTitulo(request.titulo());
        solicitacao.setDescricao(request.descricao());
        solicitacao.setCategoria(request.categoria().name());
        solicitacao.setStatus("ABERTO");
        solicitacao.setSolicitante(usuario);

        return solicitacaoRepository.save(solicitacao);
    }
    @Transactional
    public Solicitacao atualizarStatus(Long id, String novoStatus) {
    Solicitacao solicitacao = buscarPorId(id);

    if (!List.of("ABERTO", "EM_ATENDIMENTO", "CONCLUIDO")
            .contains(novoStatus)) {
        throw new IllegalArgumentException("Status inválido");
    }

    solicitacao.setStatus(novoStatus);

    return solicitacaoRepository.save(solicitacao);
    }

    @Transactional
    public void excluir(Long id) {
        Solicitacao solicitacao = buscarPorId(id);

        if (!"ABERTO".equals(solicitacao.getStatus())) {
            throw new IllegalStateException(
                    "Só é possível excluir solicitações abertas");
        }

        solicitacaoRepository.delete(solicitacao);
    }
}
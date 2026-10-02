package br.com.portal.solicitacoes.service;

import br.com.portal.solicitacoes.entity.Usuario;
import br.com.portal.solicitacoes.repository.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(
            UsuarioRepository usuarioRepository,
            PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public Usuario cadastrar(String nome, String login, String senha) {
        if (usuarioRepository.existsByUsuario(login)) {
            throw new IllegalArgumentException(
                    "Já existe um usuário com esse login");
        }

        Usuario usuario = new Usuario();
        usuario.setNome(nome);
        usuario.setUsuario(login);
        usuario.setSenha(passwordEncoder.encode(senha));

        return usuarioRepository.save(usuario);
    }

    public Usuario buscarPorLogin(String login) {
        return usuarioRepository.findByUsuario(login)
                .orElseThrow(() ->
                        new RuntimeException("Usuário não encontrado"));
    }
}
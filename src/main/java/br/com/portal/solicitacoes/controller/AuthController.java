package br.com.portal.solicitacoes.controller;

import br.com.portal.solicitacoes.dto.UsuarioCadastroRequest;
import br.com.portal.solicitacoes.entity.Usuario;
import br.com.portal.solicitacoes.service.UsuarioService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UsuarioService usuarioService;

    public AuthController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/cadastro")
    @ResponseStatus(HttpStatus.CREATED)
    public Map<String, Object> cadastrar(
            @Valid @RequestBody UsuarioCadastroRequest request) {

        Usuario usuario = usuarioService.cadastrar(
                request.nome(),
                request.usuario(),
                request.senha()
        );

        return Map.of(
                "id", usuario.getId(),
                "nome", usuario.getNome(),
                "usuario", usuario.getUsuario()
        );
    }

    @GetMapping("/csrf")
    public CsrfToken csrf(HttpServletRequest request) {
        return (CsrfToken) request.getAttribute(
                CsrfToken.class.getName()
        );
    }
}
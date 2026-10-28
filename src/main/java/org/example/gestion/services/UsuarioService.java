package org.example.gestion.services;

import lombok.RequiredArgsConstructor;
import org.example.gestion.config.JwtService;
import org.example.gestion.dto.LoginRequest;
import org.example.gestion.dto.LoginResponse;
import org.example.gestion.models.Usuario;
import org.example.gestion.repositories.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    //Registro: regresa null si el username ya existe//
    public Usuario registrar(Usuario usuario) {
        if (usuarioRepository.existsByUsername(usuario.getUsername())) return null;

        //encriptar el texto plano con BCrypt//
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        usuario.setRol("USER");
        return usuarioRepository.save(usuario);
    }

    //Login: revisa username y contraseña y regresa el token//
    public LoginResponse login(LoginRequest request) {
        Usuario usuario = usuarioRepository.findByUsername(request.username()).orElse(null);

        if (usuario == null) return null;
        if (!passwordEncoder.matches(request.password(), usuario.getPassword())) return null;

        String token = jwtService.generarToken(usuario);
        return new LoginResponse(token, usuario.getUsername(), usuario.getRol());
    }
}

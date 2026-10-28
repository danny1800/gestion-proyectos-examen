package org.example.gestion.controller;

import lombok.RequiredArgsConstructor;
import org.example.gestion.dto.LoginRequest;
import org.example.gestion.dto.LoginResponse;
import org.example.gestion.models.Usuario;
import org.example.gestion.services.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class UsuarioController {
    private final UsuarioService usuarioService;

    //Registro//
    @PostMapping("/registro")
    public ResponseEntity<?> registro(@RequestBody Usuario usuario) {
        Usuario nuevo = usuarioService.registrar(usuario);
        if (nuevo == null) {
            return ResponseEntity.badRequest().body("El username ya esta registrado");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
    }

    //Login//
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        LoginResponse respuesta = usuarioService.login(request);
        if (respuesta == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Username o contraseña incorrectos");
        }
        return ResponseEntity.ok(respuesta);
    }
}

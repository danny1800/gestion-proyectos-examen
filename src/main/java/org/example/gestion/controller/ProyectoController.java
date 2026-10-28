package org.example.gestion.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.example.gestion.models.Proyecto;
import org.example.gestion.services.ProyectoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/proyectos")
@RequiredArgsConstructor
public class ProyectoController {
    private final ProyectoService proyectoService;

    //Obtener mis proyectos//
    @GetMapping
    public ResponseEntity<List<Proyecto>> obtenerMisProyectos(Authentication authentication) {
        return ResponseEntity.ok(proyectoService.obtenerPorUsuario(authentication.getName()));
    }

    //Crear proyecto//
    @PostMapping
    public ResponseEntity<Proyecto> crear(@RequestBody Proyecto proyecto, Authentication authentication) {
        Proyecto nuevo = proyectoService.guardar(proyecto, authentication.getName());
        if (nuevo == null) return ResponseEntity.notFound().build();
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
    }
}

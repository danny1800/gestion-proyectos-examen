package org.example.gestion.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.example.gestion.dto.TareaRequest;
import org.example.gestion.models.Tarea;
import org.example.gestion.services.TareaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tareas")
@RequiredArgsConstructor
public class TareaController {
    private final TareaService tareaService;

    //Crear tarea en un proyecto//
    @PostMapping
    public ResponseEntity<Tarea> crear(@RequestBody TareaRequest request, Authentication authentication) {
        Tarea nueva = tareaService.guardar(request, authentication.getName());
        if (nueva == null) return ResponseEntity.notFound().build();
        return ResponseEntity.status(HttpStatus.CREATED).body(nueva);
    }

    //Actualizar estado//
    @PutMapping("/{id}")
    public ResponseEntity<Tarea> actualizarEstado(@PathVariable Long id, @RequestBody Tarea tarea,
                                                  Authentication authentication) {
        Tarea actualizada = tareaService.actualizarEstado(id, tarea, authentication.getName());
        if (actualizada == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(actualizada);
    }
}

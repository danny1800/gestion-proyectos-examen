package org.example.gestion.services;

import lombok.RequiredArgsConstructor;
import org.example.gestion.dto.TareaRequest;
import org.example.gestion.models.Proyecto;
import org.example.gestion.models.Tarea;
import org.example.gestion.models.enums.EstadoTarea;
import org.example.gestion.repositories.ProyectoRepository;
import org.example.gestion.repositories.TareaRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TareaService {
    private final TareaRepository tareaRepository;
    private final ProyectoRepository proyectoRepository;

    //regresa null si el proyecto no existe o no es del usuario//
    public Tarea guardar(TareaRequest request, String username) {
        Proyecto proyecto = proyectoRepository
                .findByIdAndUsuarioUsername(request.proyectoId(), username).orElse(null);
        if (proyecto == null) return null;

        Tarea tarea = new Tarea();
        tarea.setTitulo(request.titulo());
        tarea.setEstado(request.estado() != null ? request.estado() : EstadoTarea.PENDIENTE);
        tarea.setProyecto(proyecto);
        return tareaRepository.save(tarea);
    }

    //Actualizar solo el estado//
    public Tarea actualizarEstado(Long id, Tarea datos, String username) {
        Tarea tarea = tareaRepository.findByIdAndProyectoUsuarioUsername(id, username).orElse(null);
        if (tarea == null) return null;

        tarea.setEstado(datos.getEstado());
        return tareaRepository.save(tarea);
    }
}

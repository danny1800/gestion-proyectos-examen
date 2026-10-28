package org.example.gestion.dto;

import org.example.gestion.models.enums.EstadoTarea;

public record TareaRequest(String titulo, EstadoTarea estado, Long proyectoId) {
}

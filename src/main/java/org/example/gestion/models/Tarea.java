package org.example.gestion.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.example.gestion.models.enums.EstadoTarea;

@Data
@NoArgsConstructor
@Entity
@Table(name = "tareas")
public class Tarea {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String titulo;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private EstadoTarea estado = EstadoTarea.PENDIENTE;

    //relacion muchos a uno con proyectos//
    @JsonIgnore
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "proyecto_id", nullable = false)
    private Proyecto proyecto;

    //en el JSON solo se muestra el id del proyecto//
    @JsonProperty("proyectoId")
    public Long getProyectoId() {
        return proyecto != null ? proyecto.getId() : null;
    }
}

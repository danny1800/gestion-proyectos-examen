package org.example.gestion.repositories;

import org.example.gestion.models.Tarea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TareaRepository extends JpaRepository<Tarea, Long> {

    Optional<Tarea> findByIdAndProyectoUsuarioUsername(Long id, String username);
}

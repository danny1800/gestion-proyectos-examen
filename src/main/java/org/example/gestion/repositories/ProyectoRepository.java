package org.example.gestion.repositories;

import org.example.gestion.models.Proyecto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProyectoRepository extends JpaRepository<Proyecto, Long> {

    List<Proyecto> findByUsuarioUsername(String username);

    long countByUsuarioUsername(String username);

    Optional<Proyecto> findByIdAndUsuarioUsername(Long id, String username);
}

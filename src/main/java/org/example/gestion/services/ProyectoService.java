package org.example.gestion.services;

import lombok.RequiredArgsConstructor;
import org.example.gestion.models.Proyecto;
import org.example.gestion.models.Usuario;
import org.example.gestion.repositories.ProyectoRepository;
import org.example.gestion.repositories.UsuarioRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProyectoService {
    private final ProyectoRepository proyectoRepository;
    private final UsuarioRepository usuarioRepository;

    private static final int LIMITE_PROYECTOS = 3;

    //solo los proyectos del usuario logueado//
    public List<Proyecto> obtenerPorUsuario(String username) {
        return proyectoRepository.findByUsuarioUsername(username);
    }

    public Proyecto guardar(Proyecto proyecto, String username) {
        Usuario usuario = usuarioRepository.findByUsername(username).orElse(null);
        if (usuario == null) return null;

        //REGLA DE NEGOCIO: maximo 3 proyectos por usuario → 400//
        if (proyectoRepository.countByUsuarioUsername(username) >= LIMITE_PROYECTOS) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Alcanzaste el limite de 3 proyectos, no puedes crear mas");
        }

        //se asigna automaticamente al usuario del token//
        proyecto.setUsuario(usuario);
        return proyectoRepository.save(proyecto);
    }
}

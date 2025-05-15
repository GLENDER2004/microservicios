package pe.edu.upeu.msvcgestion_usuario.service;

import pe.edu.upeu.msvcgestion_usuario.entity.Permiso;

import java.util.List;
import java.util.Optional;

public interface PermisoService {
    Permiso guardar(Permiso permiso);
    Optional<Permiso> buscarPorId(Long id);
    List<Permiso> listar();
    void eliminar(Long id);

}

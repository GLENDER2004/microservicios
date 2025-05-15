package pe.edu.upeu.msvcgestion_usuario.service;

import pe.edu.upeu.msvcgestion_usuario.entity.Rol;
import pe.edu.upeu.msvcgestion_usuario.entity.Permiso;

import java.util.List;
import java.util.Optional;

public interface RolService {
    Rol guardar(Rol rol);
    Optional<Rol> buscarPorId(Long id);
    List<Rol> listar();
    void eliminar(Long id);
}

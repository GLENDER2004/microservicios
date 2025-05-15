package pe.edu.upeu.msvcgestion_usuario.service;

import pe.edu.upeu.msvcgestion_usuario.entity.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {

    Usuario guardar(Usuario usuario);
    Optional<Usuario> buscarPorId(Long id);
    List<Usuario> listar();
    void eliminar(Long id);
    Optional<Usuario> buscarPorCorreo(String correo);

}

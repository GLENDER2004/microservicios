package pe.edu.upeu.msvcgestion_usuario.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upeu.msvcgestion_usuario.entity.Usuario;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByCorreo(String correo);
}

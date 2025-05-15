package pe.edu.upeu.msvcgestion_usuario.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upeu.msvcgestion_usuario.entity.Profile;

public interface ProfileRepository extends JpaRepository<Profile, Long> {
}

package pe.edu.upeu.msvcgestion_usuario.serviceImpl;

import org.springframework.stereotype.Service;
import pe.edu.upeu.msvcgestion_usuario.entity.Rol;
import pe.edu.upeu.msvcgestion_usuario.entity.Permiso;
import pe.edu.upeu.msvcgestion_usuario.repositories.RolRepository;
import pe.edu.upeu.msvcgestion_usuario.repositories.PermisoRepository;
import pe.edu.upeu.msvcgestion_usuario.service.RolService;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RolServiceImpl implements RolService {

    private final RolRepository rolRepository;
    private final PermisoRepository permisoRepository;

    public RolServiceImpl(RolRepository rolRepository, PermisoRepository permisoRepository) {
        this.rolRepository = rolRepository;
        this.permisoRepository = permisoRepository;
    }

    @Override
    public Rol guardar(Rol rol) {
        if (rol.getPermisos() != null && !rol.getPermisos().isEmpty()) {

            List<Long> permisosIds = rol.getPermisos().stream()
                    .map(Permiso::getId)
                    .collect(Collectors.toList());

            List<Permiso> permisos = permisoRepository.findAllById(permisosIds);
            rol.setPermisos(new HashSet<>(permisos));
        }

        return rolRepository.save(rol);
    }

    @Override
    public Optional<Rol> buscarPorId(Long id) {
        return rolRepository.findById(id);
    }

    @Override
    public List<Rol> listar() {
        return rolRepository.findAll();
    }

    @Override
    public void eliminar(Long id) {
        rolRepository.deleteById(id);
    }
}

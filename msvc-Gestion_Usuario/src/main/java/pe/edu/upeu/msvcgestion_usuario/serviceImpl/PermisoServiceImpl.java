package pe.edu.upeu.msvcgestion_usuario.serviceImpl;

import org.springframework.stereotype.Service;
import pe.edu.upeu.msvcgestion_usuario.entity.Permiso;
import pe.edu.upeu.msvcgestion_usuario.repositories.PermisoRepository;
import pe.edu.upeu.msvcgestion_usuario.service.PermisoService;
import pe.edu.upeu.msvcgestion_usuario.service.RolService;

import java.util.List;
import java.util.Optional;

@Service
public class PermisoServiceImpl implements PermisoService {
    private final PermisoRepository permisoRepository;

    public PermisoServiceImpl(PermisoRepository permisoRepository) {
        this.permisoRepository = permisoRepository;
    }

    @Override
    public Permiso guardar(Permiso permiso) {
        return permisoRepository.save(permiso);
    }

    @Override
    public Optional<Permiso> buscarPorId(Long id) {
        return permisoRepository.findById(id);
    }

    @Override
    public List<Permiso> listar() {
        return permisoRepository.findAll();
    }

    @Override
    public void eliminar(Long id) {
        permisoRepository.deleteById(id);
    }

}

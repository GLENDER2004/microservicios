package pe.edu.upeu.msvcgestion_usuario.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upeu.msvcgestion_usuario.entity.Permiso;
import pe.edu.upeu.msvcgestion_usuario.entity.Rol;
import pe.edu.upeu.msvcgestion_usuario.repositories.PermisoRepository;
import pe.edu.upeu.msvcgestion_usuario.repositories.RolRepository;
import pe.edu.upeu.msvcgestion_usuario.service.RolService;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/roles")
public class RolController {

    private final RolService rolService;
    private final PermisoRepository permisoRepository;  // Cambio aquí

    public RolController(RolService rolService, PermisoRepository permisoRepository) {
        this.rolService = rolService;
        this.permisoRepository = permisoRepository;
    }

    // Listar roles
    @GetMapping
    public List<Rol> listar() {
        return rolService.listar();
    }

    // Crear rol con permisos
    @PostMapping
    public ResponseEntity<Rol> crear(@RequestBody Rol rolRequest) {
        Rol rolGuardado = rolService.guardar(rolRequest);
        return ResponseEntity.ok(rolGuardado);
    }

    // Buscar por ID
    @GetMapping("/{id}")
    public ResponseEntity<Rol> obtenerPorId(@PathVariable Long id) {
        return rolService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Eliminar rol
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        rolService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    // Editar rol
    @PutMapping("/{id}")
    public ResponseEntity<Rol> editar(@PathVariable Long id, @RequestBody Rol rolRequest) {
        Optional<Rol> rolExistente = rolService.buscarPorId(id);

        if (rolExistente.isPresent()) {
            Rol rol = rolExistente.get();

            rol.setNombre(rolRequest.getNombre());
            rol.setDescripcion(rolRequest.getDescripcion());
            rol.setEstado(rolRequest.getEstado());

            if (rolRequest.getPermisos() != null && !rolRequest.getPermisos().isEmpty()) {

                List<Long> permisosIds = rolRequest.getPermisos().stream()
                        .map(Permiso::getId)
                        .collect(Collectors.toList());

                List<Permiso> permisos = permisoRepository.findAllById(permisosIds);

                rol.setPermisos(new HashSet<>(permisos));
            }

            Rol rolActualizado = rolService.guardar(rol);
            return ResponseEntity.ok(rolActualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}

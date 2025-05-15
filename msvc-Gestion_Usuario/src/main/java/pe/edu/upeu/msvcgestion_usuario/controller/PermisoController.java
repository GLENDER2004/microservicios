package pe.edu.upeu.msvcgestion_usuario.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upeu.msvcgestion_usuario.entity.Permiso;
import pe.edu.upeu.msvcgestion_usuario.service.PermisoService;

import java.util.List;

@RestController
@RequestMapping("/api/permisos")
public class PermisoController {

    private final PermisoService permisoService;

    public PermisoController(PermisoService permisoService) {
        this.permisoService = permisoService;
    }

    // Listar permisos
    @GetMapping
    public List<Permiso> listar() {
        return permisoService.listar();
    }

    // Crear permiso
    @PostMapping
    public ResponseEntity<Permiso> crear(@RequestBody Permiso permiso) {
        Permiso permisoGuardado = permisoService.guardar(permiso);
        return ResponseEntity.ok(permisoGuardado);
    }

    // Buscar por ID
    @GetMapping("/{id}")
    public ResponseEntity<Permiso> obtenerPorId(@PathVariable Long id) {
        return permisoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Eliminar permiso
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        permisoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }



}

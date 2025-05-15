package pe.edu.upeu.msvcgestion_usuario.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
//import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;
import pe.edu.upeu.msvcgestion_usuario.client.AuthClient;
import pe.edu.upeu.msvcgestion_usuario.entity.Usuario;
import pe.edu.upeu.msvcgestion_usuario.service.UsuarioService;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private AuthClient authClient;

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    //Listar
    @GetMapping
    public List<Usuario> listar() {
        return usuarioService.listar();
    }

    // Buscar por ID
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> obtenerPorId(@PathVariable Long id) {
        return usuarioService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    // Crear usuario con profile
    @PostMapping
    public ResponseEntity<Usuario> crear(@RequestBody Usuario usuario) {
        // 1. Encriptar la contraseña usando auth_service
        String hashedPassword = authClient.encryptPassword(usuario.getPassword());
        usuario.setPassword(hashedPassword);

        // 2. Guardar el usuario con contraseña ya encriptada
        Usuario usuarioGuardado = usuarioService.guardar(usuario);
        return ResponseEntity.ok(usuarioGuardado);
    }

    // Eliminar usuario
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        usuarioService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    // Editar usuario con profile
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> editar(@PathVariable Long id, @RequestBody Usuario usuarioActualizado) {
        return usuarioService.buscarPorId(id).map(usuarioExistente -> {
            // Actualizar campos simples
            usuarioExistente.setCorreo(usuarioActualizado.getCorreo());
            usuarioExistente.setUsuario(usuarioActualizado.getUsuario());
            usuarioExistente.setEstado(usuarioActualizado.getEstado());
            usuarioExistente.setRol(usuarioActualizado.getRol());

            // Encriptar si la contraseña ha cambiado
            if (!usuarioActualizado.getPassword().equals(usuarioExistente.getPassword())) {
                String passwordEncriptado = authClient.encryptPassword(usuarioActualizado.getPassword());
                usuarioExistente.setPassword(passwordEncriptado);
            }

            // Actualizar profile
            if (usuarioExistente.getProfile() != null && usuarioActualizado.getProfile() != null) {
                usuarioExistente.getProfile().setNombre(usuarioActualizado.getProfile().getNombre());
                usuarioExistente.getProfile().setApellido(usuarioActualizado.getProfile().getApellido());
                usuarioExistente.getProfile().setAvatar_url(usuarioActualizado.getProfile().getAvatar_url());
                usuarioExistente.getProfile().setPais(usuarioActualizado.getProfile().getPais());
                usuarioExistente.getProfile().setCelular(usuarioActualizado.getProfile().getCelular());
            }

            Usuario usuarioGuardado = usuarioService.guardar(usuarioExistente);
            return ResponseEntity.ok(usuarioGuardado);
        }).orElse(ResponseEntity.notFound().build());
    }


    @GetMapping("/buscar-por-correo")
    public ResponseEntity<Usuario> buscarPorCorreo(@RequestParam String correo) {
        return usuarioService.buscarPorCorreo(correo)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }



}

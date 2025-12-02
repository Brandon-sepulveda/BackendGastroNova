package GastroNova.gastro_nova.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import GastroNova.gastro_nova.dto.LoginResponse;
import GastroNova.gastro_nova.model.Usuario;
import GastroNova.gastro_nova.service.UsuarioService;

@RestController
@RequestMapping("/usuario")
@CrossOrigin(origins = "*")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    // ====================
    //     REGISTRO
    // ====================
    @PostMapping("/register")
    public ResponseEntity<Boolean> registrarUsuario(@RequestBody Usuario usuario) {
        try {
            boolean ok = usuarioService.almacenar(usuario);

            if (!ok) {
                // Usuario o correo ya existen
                return ResponseEntity.status(409).body(false);
            }

            return ResponseEntity.ok(true);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(400).body(false);
        }
    }

    // ====================
    //       LOGIN
    // ====================

    // Request para recibir usuario + contraseña
    static class LoginRequest {
        public String usuario;
        public String contrasena;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest body) {
        try {
            // Intentar obtener el usuario con esas credenciales
            Usuario usuario = usuarioService.obtenerPorUsuarioYContrasena(body.usuario, body.contrasena);

            if (usuario == null) {
                // Credenciales incorrectas: success = false y sin datos
                LoginResponse resp = new LoginResponse(false, null, null, null, null, null);
                return ResponseEntity.ok(resp);
            }

            // Credenciales correctas: devolver info básica del usuario
            LoginResponse resp = new LoginResponse(
                    true,
                    usuario.getId(),        // Asegúrate de tener getId() en la entidad
                    usuario.getNombre(),
                    usuario.getApellido(),
                    usuario.getCorreo(),
                    usuario.getUsuario()
            );

            return ResponseEntity.ok(resp);

        } catch (Exception e) {
            e.printStackTrace();
            // Error en el servidor, pero mantenemos el formato de respuesta
            LoginResponse resp = new LoginResponse(false, null, null, null, null, null);
            return ResponseEntity.status(400).body(resp);
        }
    }
}

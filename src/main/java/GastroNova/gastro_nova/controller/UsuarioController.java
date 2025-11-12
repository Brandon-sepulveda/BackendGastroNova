package GastroNova.gastro_nova.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import GastroNova.gastro_nova.model.Usuario;
import GastroNova.gastro_nova.service.UsuarioService;

@RestController
@RequestMapping("/usuario")
@CrossOrigin(origins = "*")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    // --- REGISTRO DE USUARIO ---
    @PostMapping("/register")
    public ResponseEntity<Boolean> registrarUsuario(@RequestBody Usuario usuario) {
        try {
            boolean ok = usuarioService.almacenar(usuario);

            if (!ok) {
                // Si el usuario o correo ya existen
                return ResponseEntity.status(409).body(false);
            }

            // Si todo salió bien
            return ResponseEntity.ok(true);

        } catch (Exception e) {
            // Muestra el error real en la consola
            e.printStackTrace();

            // Devuelve 400 en lugar de 500 (error manejado)
            return ResponseEntity.status(400).body(false);
        }
    }

    // --- LOGIN ---
    public static class LoginRequest {
        public String usuario;
        public String contrasena;
    }

    @PostMapping("/login")
    public ResponseEntity<Boolean> login(@RequestBody LoginRequest body) {
        try {
            boolean ok = usuarioService.Loguear(body.usuario, body.contrasena);
            return ResponseEntity.ok(ok);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(400).body(false);
        }
    }
}

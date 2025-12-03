package GastroNova.gastro_nova.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import GastroNova.gastro_nova.model.Usuario;
import GastroNova.gastro_nova.repository.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    // Guardar usuario (registro DESDE LA APP)
    public boolean almacenar(Usuario usuario) {
        // Validar que no exista el mismo usuario
        if (usuarioRepository.findByUsuario(usuario.getUsuario()).isPresent()) {
            return false;
        }

        // Validar que no exista el mismo correo
        if (usuarioRepository.findByCorreo(usuario.getCorreo()).isPresent()) {
            return false;
        }

        // ⚠️ AQUÍ ESTABA LA INCONSISTENCIA:
        // Si el usuario viene con tipo_usuario=true desde el JSON/controlador,
        // lo estabas guardando tal cual.
        // Como todos los usuarios de la app deben ser NO admin,
        // forzamos tipo_usuario = false.
        usuario.setTipo_usuario(false);

        usuarioRepository.save(usuario);
        return true;
    }

    // Login simple: devuelve true/false
    public boolean Loguear(String usuario, String contrasena) {
        return usuarioRepository.findByUsuarioAndContrasena(usuario, contrasena).isPresent();
    }

    // Login completo: devuelve el Usuario (para ver tipo_usuario en la app)
    public Usuario obtenerPorUsuarioYContrasena(String usuario, String contrasena) {
        Optional<Usuario> opt = usuarioRepository.findByUsuarioAndContrasena(usuario, contrasena);
        return opt.orElse(null);
    }
}

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

    // Guardar usuario (registro)
    public boolean almacenar(Usuario usuario) {
        // Validar que no exista el mismo usuario
        if (usuarioRepository.findByUsuario(usuario.getUsuario()).isPresent()) {
            return false;
        }

        // Validar que no exista el mismo correo
        if (usuarioRepository.findByCorreo(usuario.getCorreo()).isPresent()) {
            return false;
        }

        // Si pasa las validaciones, se guarda
        usuarioRepository.save(usuario);
        return true;
    }

    // Login "simple": sólo responde true/false
    // (lo dejo para no romper lógica que ya tengas en otros lados)
    public boolean Loguear(String usuario, String contrasena) {
        return usuarioRepository.findByUsuarioAndContrasena(usuario, contrasena).isPresent();
    }

    // Login "completo": devuelve el Usuario si las credenciales son correctas
    // o null si son incorrectas
    public Usuario obtenerPorUsuarioYContrasena(String usuario, String contrasena) {
        Optional<Usuario> opt = usuarioRepository.findByUsuarioAndContrasena(usuario, contrasena);
        return opt.orElse(null);
    }
}

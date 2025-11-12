package GastroNova.gastro_nova.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import GastroNova.gastro_nova.model.Usuario;
import GastroNova.gastro_nova.repository.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    //Guardar usuario
    public boolean almacenar (Usuario usuario){
        if(usuarioRepository.findByUsuario(usuario.getUsuario()).isPresent()){
            return false;
        }else if (usuarioRepository.findByCorreo(usuario.getCorreo()).isPresent()){
            return false;
        }else{
            usuarioRepository.save(usuario);
            return true;
        }
    }

    //Loguear
    public boolean Loguear(String usuario, String contrasena){

        if(usuarioRepository.findByUsuarioAndContrasena(usuario, contrasena).isPresent()){
            
            return true;
        } else{
            return false;
        }
    }

    



}

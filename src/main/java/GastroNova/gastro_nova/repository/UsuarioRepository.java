package GastroNova.gastro_nova.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import GastroNova.gastro_nova.model.Usuario;

import java.util.Optional;


public interface UsuarioRepository extends JpaRepository<Usuario,Integer>{

    Optional<Usuario> findByUsuario(String usuario);

    Optional<Usuario> findByCorreo(String correo);
    
    Optional<Usuario> findByContrasena(String contrasena);

    Optional<Usuario> findByUsuarioAndContrasena(String usuario, String contrasena);


}

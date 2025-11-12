package GastroNova.gastro_nova.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import GastroNova.gastro_nova.model.Ruta;
import java.util.Optional;


public interface RutaRepository extends JpaRepository<Ruta,Integer>{

    Optional<Ruta> findByNombre(String nombre);
}

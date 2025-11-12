package GastroNova.gastro_nova.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import GastroNova.gastro_nova.model.Direccion;
import GastroNova.gastro_nova.model.Restaurant;
import java.util.Optional;




public interface RestaurantRepository extends JpaRepository <Restaurant,Integer> {

    Optional <Restaurant> findByNombre(String nombre);
    Optional <Restaurant> findByDireccion(Direccion direccion);

}

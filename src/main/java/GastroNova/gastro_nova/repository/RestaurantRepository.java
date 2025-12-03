package GastroNova.gastro_nova.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import GastroNova.gastro_nova.model.Restaurant;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

    Optional<Restaurant> findByNombre(String nombre);

    // Ya no buscamos por Direccion entidad, porque no existe en Restaurant
    // Optional<Restaurant> findByDireccion(Direccion direccion);
}

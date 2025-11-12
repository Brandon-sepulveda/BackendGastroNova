package GastroNova.gastro_nova.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import GastroNova.gastro_nova.model.RutaSugerida;
import GastroNova.gastro_nova.model.RutaSugeridaId;

public interface RutaSugeridaRepository extends JpaRepository<RutaSugerida, RutaSugeridaId> {

    // 🔹 Obtener todas las entradas de una ruta específica (ordenadas)
    List<RutaSugerida> findByIdRutaIdOrderByOrdenAsc(int rutaId);

    // 🔹 Obtener una entrada específica por ruta y restaurant
    Optional<RutaSugerida> findByIdRutaIdAndIdRestaurantId(int rutaId, int restaurantId);

    // 🔹 Verificar si un restaurant ya está en una ruta
    boolean existsByIdRutaIdAndIdRestaurantId(int rutaId, int restaurantId);

    // 🔹 Borrar un restaurant de una ruta
    void deleteByIdRutaIdAndIdRestaurantId(int rutaId, int restaurantId);
}
package GastroNova.gastro_nova.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import GastroNova.gastro_nova.dto.RestaurantDto;
import GastroNova.gastro_nova.dto.RutaCreateRequest;
import GastroNova.gastro_nova.dto.RutaDto;
import GastroNova.gastro_nova.model.Restaurant;
import GastroNova.gastro_nova.model.Ruta;
import GastroNova.gastro_nova.model.RutaSugerida;
import GastroNova.gastro_nova.repository.RestaurantRepository;
import GastroNova.gastro_nova.repository.RutaRepository;
import GastroNova.gastro_nova.repository.RutaSugeridaRepository;

@Service
public class RutaService {

    @Autowired
    private RutaRepository rutaRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private RutaSugeridaRepository rutaSugeridaRepository;

    /**
     * Devuelve todas las rutas con su lista de restaurantes asociados.
     * Se usa fetch join en el repositorio y @Transactional para evitar
     * LazyInitializationException.
     */
    @Transactional(readOnly = true)
    public List<RutaDto> listarRutasConRestaurantes() {
        List<Ruta> rutas = rutaRepository.findAllWithRestaurantes();

        return rutas.stream()
                .map(this::mapRutaToDto)
                .collect(Collectors.toList());
    }

    /**
     * Crea una ruta y asocia los restaurantes indicados por sus IDs
     * en la tabla intermedia ruta_sugerida (con orden incremental).
     */
    @Transactional
    public RutaDto crearRutaConRestaurantes(RutaCreateRequest request) {

        Ruta ruta = new Ruta();
        ruta.setNombre(request.getNombre());
        ruta.setDescripcion(request.getDescripcion());

        // Guardamos primero la ruta
        ruta = rutaRepository.save(ruta);

        // Asociamos restaurantes si vienen IDs
        if (request.getRestaurantIds() != null && !request.getRestaurantIds().isEmpty()) {
            int orden = 1;
            for (Long restaurantId : request.getRestaurantIds()) {

                Restaurant restaurant = restaurantRepository.findById(restaurantId)
                        .orElseThrow(() -> new RuntimeException("Restaurant no encontrado: " + restaurantId));

                RutaSugerida rs = new RutaSugerida();
                rs.setRuta(ruta);
                rs.setRestaurant(restaurant);
                rs.setOrden(orden++);

                rutaSugeridaRepository.save(rs);
            }
        }

        // Recargamos la ruta con relaciones para devolver DTO completo
        Ruta rutaConRest = rutaRepository.findById(ruta.getId())
                .orElseThrow(() -> new RuntimeException("Ruta recién creada no encontrada"));

        return mapRutaToDto(rutaConRest);
    }

    // =========================
    //   MAPEOS A DTO
    // =========================

    private RutaDto mapRutaToDto(Ruta ruta) {
        List<RestaurantDto> restaurantes = ruta.getRestaurantesOrdenados()
                .stream()
                .map(RutaSugerida::getRestaurant)   // De la relación intermedia tomamos el Restaurant
                .filter(r -> r != null)
                .distinct()
                .map(this::mapRestaurantToDto)
                .collect(Collectors.toList());

        return new RutaDto(
                ruta.getId(),
                ruta.getNombre(),
                ruta.getDescripcion(),
                restaurantes
        );
    }

    private RestaurantDto mapRestaurantToDto(Restaurant restaurant) {
        String direccion = restaurant.getDireccionText();
        if (direccion == null || direccion.isBlank()) {
            direccion = "Dirección no especificada";
        }

        return new RestaurantDto(
            restaurant.getId(),
            restaurant.getNombre(),
            direccion,
            restaurant.getDescripcion()
        );
    }
}

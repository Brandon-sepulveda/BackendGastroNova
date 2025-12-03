package GastroNova.gastro_nova.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import GastroNova.gastro_nova.dto.RestaurantDto;
import GastroNova.gastro_nova.dto.RestaurantRequest;
import GastroNova.gastro_nova.model.Restaurant;
import GastroNova.gastro_nova.service.RestaurantService;

@RestController
@RequestMapping("/restaurant")
@CrossOrigin(origins = "*")
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    // Registrar restaurant
    @PostMapping("/register")
    public ResponseEntity<Boolean> registrarRestaurant(@RequestBody RestaurantRequest dto) {
        try {
            // Convertimos el DTO que viene de Android a la entidad Restaurant
            Restaurant restaurant = new Restaurant();

            restaurant.setNombre(dto.getNombre());
            restaurant.setDescripcion(dto.getDescripcion());

            // Mapeamos "ubicacion" a direccionText
            restaurant.setDireccionText(dto.getUbicacion());

            boolean ok = restaurantService.almacenarRestaurant(restaurant);

            if (!ok) {
                // Si el nombre ya existe
                return ResponseEntity.status(409).body(false);
            }

            // Si todo salió bien
            return ResponseEntity.ok(true);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(400).body(false);
        }
    }

    // Listar restaurants (DTO)
    @GetMapping("/list")
    public ResponseEntity<List<RestaurantDto>> listarRestaurants() {
        List<RestaurantDto> restaurantes = restaurantService.listarDto();
        return ResponseEntity.ok(restaurantes);
    }
}

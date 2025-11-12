package GastroNova.gastro_nova.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import GastroNova.gastro_nova.model.Restaurant;
import GastroNova.gastro_nova.service.RestaurantService;

@RestController
@RequestMapping("/restaurant")
@CrossOrigin(origins = "*")
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    //Registrar restaurant
    @PostMapping("/restaurant/register")
    public ResponseEntity<Boolean> registrarRestaurant(@RequestBody Restaurant restaurant){
        try {
            boolean ok = restaurantService.almacenarRestaurant(restaurant);

            if (!ok) {
                // Si el usuario o correo ya existen
                return ResponseEntity.status(409).body(false);
            }

            // Si todo salió bien
            return ResponseEntity.ok(true);

        } catch (Exception e) {
            // Muestra el error real en la consola
            e.printStackTrace();

            // Devuelve 400 en lugar de 500 (error manejado)
            return ResponseEntity.status(400).body(false);
        }
    }

    @PostMapping("/restaurant/list")


}

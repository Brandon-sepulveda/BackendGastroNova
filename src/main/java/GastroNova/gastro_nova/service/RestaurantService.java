package GastroNova.gastro_nova.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import GastroNova.gastro_nova.dto.RestaurantDto;
import GastroNova.gastro_nova.model.Restaurant;
import GastroNova.gastro_nova.repository.RestaurantRepository;

@Service
public class RestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    // Guardar restaurant
    public boolean almacenarRestaurant(Restaurant restaurant) {

        // Validar nombre único
        if (restaurantRepository.findByNombre(restaurant.getNombre()).isPresent()) {
            return false;
        }

        // (Opcional) Si quieres validar direcciónText no vacía:
        if (restaurant.getDireccionText() == null || restaurant.getDireccionText().isBlank()) {
            restaurant.setDireccionText("Dirección no especificada");
        }

        restaurantRepository.save(restaurant);
        return true;
    }

    // Listar restaurants (DTO para evitar errores de serialización por relaciones JPA)
    public List<RestaurantDto> listarDto() {
        return restaurantRepository.findAll()
                .stream()
                .map(r -> new RestaurantDto(
                        r.getId(),
                        r.getNombre(),
                        r.getDireccionText(),
                        r.getDescripcion()
                ))
                .collect(Collectors.toList());
    }
}

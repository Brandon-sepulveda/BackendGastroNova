package GastroNova.gastro_nova.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import GastroNova.gastro_nova.model.Restaurant;
import GastroNova.gastro_nova.repository.RestaurantRepository;

@Service
public class RestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    //Guardar restaurant
    public boolean almacenarRestaurant (Restaurant restaurant){
        if(restaurantRepository.findByNombre(restaurant.getNombre()).isPresent()){
            return false;
        } else if (restaurantRepository.findByDireccion(restaurant.getDireccion()).isPresent()){
            return false;
        }else{
            restaurantRepository.save(restaurant);
            return true;
        }
    }
    //Listar restaurant
    public List<Restaurant> listar(){
        return restaurantRepository.findAll();
    }
    



}

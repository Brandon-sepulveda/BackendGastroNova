package GastroNova.gastro_nova.dto;

import java.util.List;

public class RutaCreateRequest {

    private String nombre;
    private String descripcion;
    private List<Long> restaurantIds;

    public RutaCreateRequest() {
    }

    public RutaCreateRequest(String nombre, String descripcion, List<Long> restaurantIds) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.restaurantIds = restaurantIds;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<Long> getRestaurantIds() {
        return restaurantIds;
    }

    public void setRestaurantIds(List<Long> restaurantIds) {
        this.restaurantIds = restaurantIds;
    }
}

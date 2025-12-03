package GastroNova.gastro_nova.dto;

import java.util.List;

public class RutaDto {

    private int id;
    private String nombre;
    private String descripcion;
    private List<RestaurantDto> restaurantes;

    public RutaDto() {
    }

    public RutaDto(int id, String nombre, String descripcion, List<RestaurantDto> restaurantes) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.restaurantes = restaurantes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public List<RestaurantDto> getRestaurantes() {
        return restaurantes;
    }

    public void setRestaurantes(List<RestaurantDto> restaurantes) {
        this.restaurantes = restaurantes;
    }
}

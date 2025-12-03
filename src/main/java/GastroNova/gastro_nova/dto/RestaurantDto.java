package GastroNova.gastro_nova.dto;

public class RestaurantDto {

    private Long id;
    private String nombre;
    private String direccionText;
    private String descripcion;

    public RestaurantDto() {
    }

    public RestaurantDto(Long id, String nombre, String direccionText, String descripcion) {
        this.id = id;
        this.nombre = nombre;
        this.direccionText = direccionText;
        this.descripcion = descripcion;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccionText() {
        return direccionText;
    }

    public void setDireccionText(String direccionText) {
        this.direccionText = direccionText;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}

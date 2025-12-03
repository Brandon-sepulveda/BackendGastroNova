package GastroNova.gastro_nova.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Access;
import jakarta.persistence.AccessType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "restaurant")
@Access(AccessType.FIELD)
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    // Campo de dirección en texto simple, mapeado a la columna direccion_text
    @Column(name = "direccion_text", nullable = true)
    private String direccionText;

    private String descripcion;

    // =====================
    //     RELACIONES
    // =====================

 @OneToMany(mappedBy = "restaurant", fetch = FetchType.LAZY)
@JsonIgnore
private List<Resena> resenas = new ArrayList<>();

    @OneToMany(mappedBy = "restaurant", fetch = FetchType.LAZY, orphanRemoval = false)
    @JsonIgnore
    private List<RutaSugerida> rutasDondeAparece = new ArrayList<>();

    // --- helpers opcionales ---
    public void addRutaSugerida(RutaSugerida rs) {
        rutasDondeAparece.add(rs);
        rs.setRestaurant(this);
    }

    public void removeRutaSugerida(RutaSugerida rs) {
        rutasDondeAparece.remove(rs);
        rs.setRestaurant(null);
    }

    // =====================
    //   CONSTRUCTORES
    // =====================

    public Restaurant() {
        this.nombre = "";
        this.descripcion = "";
        this.direccionText = "";
    }

    public Restaurant(String nombre, String direccionText, String descripcion) {
        this.nombre = nombre;
        this.direccionText = direccionText;
        this.descripcion = descripcion;
    }

    // =====================
    //  GETTERS / SETTERS
    // =====================

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

    public List<Resena> getResenas() {
        return resenas;
    }

    public void setResenas(List<Resena> resenas) {
        this.resenas = resenas;
    }

    public List<RutaSugerida> getRutasDondeAparece() {
        return rutasDondeAparece;
    }

    public void setRutasDondeAparece(List<RutaSugerida> rutasDondeAparece) {
        this.rutasDondeAparece = rutasDondeAparece;
    }
}

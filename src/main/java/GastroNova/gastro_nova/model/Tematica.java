package GastroNova.gastro_nova.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Access;
import jakarta.persistence.AccessType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tematica")
@Access(AccessType.FIELD)
public class Tematica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nombre;
    private String descripcion;

    // =====================
    //     RELACIONES
    // =====================

    // Relación con Ruta (una temática puede tener muchas rutas)
    @OneToMany(mappedBy = "tematica", fetch = FetchType.LAZY)
    private List<Ruta> rutas = new ArrayList<>();

    // =====================
    //   CONSTRUCTORES
    // =====================

    public Tematica() {
        this.nombre = "";
        this.descripcion = "";
    }

    public Tematica(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    // =====================
    //  GETTERS / SETTERS
    // =====================

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

    public List<Ruta> getRutas() {
        return rutas;
    }

    public void setRutas(List<Ruta> rutas) {
        this.rutas = rutas;
    }

    // Helpers opcionales

    public void addRuta(Ruta ruta) {
        rutas.add(ruta);
        ruta.setTematica(this);
    }

    public void removeRuta(Ruta ruta) {
        rutas.remove(ruta);
        ruta.setTematica(null);
    }
}

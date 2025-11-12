package GastroNova.gastro_nova.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Access;
import jakarta.persistence.AccessType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Access(AccessType.FIELD)
@Table(name = "RUTA")
public class Ruta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nombre;
    private String descripcion;

    // RELACIONES
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TEMATICA_ID")
    private Tematica tematica; // opcional

    @OneToMany(mappedBy = "ruta", fetch = FetchType.LAZY)
    @JsonIgnore
    private java.util.List<RutasGuardadas> guardadaPorUsuarios = new java.util.ArrayList<>();

    @OneToMany(mappedBy = "ruta", fetch = FetchType.LAZY, orphanRemoval = false)
    @JsonIgnore
    private List<RutaSugerida> restaurantesOrdenados = new ArrayList<>();

    // --- helpers opcionales ---
    public void addRutaSugerida(RutaSugerida rs) {
        restaurantesOrdenados.add(rs);
        rs.setRuta(this);
    }

    public void removeRutaSugerida(RutaSugerida rs) {
        restaurantesOrdenados.remove(rs);
        rs.setRuta(null);
    }

    public Ruta() {
        this.nombre = "";
        this.descripcion = "";
    }
    public Ruta(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
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

    public Tematica getTematica() {
        return tematica;
    }

    public void setTematica(Tematica tematica) {
        this.tematica = tematica;
    }

    public java.util.List<RutasGuardadas> getGuardadaPorUsuarios() {
        return guardadaPorUsuarios;
    }

    public void setGuardadaPorUsuarios(java.util.List<RutasGuardadas> guardadaPorUsuarios) {
        this.guardadaPorUsuarios = guardadaPorUsuarios;
    }

    public List<RutaSugerida> getRestaurantesOrdenados() {
        return restaurantesOrdenados;
    }

    public void setRestaurantesOrdenados(List<RutaSugerida> restaurantesOrdenados) {
        this.restaurantesOrdenados = restaurantesOrdenados;
    }

    
}

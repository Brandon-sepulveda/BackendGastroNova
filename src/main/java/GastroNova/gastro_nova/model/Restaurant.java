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

@Entity
@Access(AccessType.FIELD)
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nombre;
    private String descripcion;

    // RELACIONES
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "EMPRESA_ID")
    private Empresa empresa;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DIRECCION_ID")
    private Direccion direccion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TEMATICA_ID")
    private Tematica tematica; // UNA temática

    @OneToMany(mappedBy = "restaurant", fetch = FetchType.LAZY)
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

    public Restaurant() {
        this.nombre = "";
        this.descripcion = "";
    }
    public Restaurant(String nombre, String descripcion) {
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

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

    public Tematica getTematica() {
        return tematica;
    }

    public void setTematica(Tematica tematica) {
        this.tematica = tematica;
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

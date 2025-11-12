package GastroNova.gastro_nova.model;

import java.io.Serializable;

import jakarta.persistence.Access;
import jakarta.persistence.AccessType;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;



@Entity
@Access(AccessType.FIELD)
@Table(
    name = "RUTA_SUGERIDA",
    uniqueConstraints = @UniqueConstraint(name = "UQ_RS_ORDEN", columnNames = {"RUTA_ID", "ORDEN"})
)
public class RutaSugerida implements Serializable {
    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private RutaSugeridaId id = new RutaSugeridaId();

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId("rutaId") // vincula id.rutaId con la FK
    @JoinColumn(name = "RUTA_ID", nullable = false,
            foreignKey = @ForeignKey(name = "FK_RS_RUTA"))
    private Ruta ruta;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId("restaurantId") // vincula id.restaurantId con la FK
    @JoinColumn(name = "RESTAURANT_ID", nullable = false,
            foreignKey = @ForeignKey(name = "FK_RS_RESTAURANT"))
    private Restaurant restaurant;

    @Column(name = "ORDEN", nullable = false)
    private Integer orden;

    @Column(name = "DESCRIPCION", length = 1000)
    private String descripcion;

    public RutaSugerida() {}

    public RutaSugerida(Ruta ruta, Restaurant restaurant, Integer orden, String descripcion) {
        this.ruta = ruta;
        this.restaurant = restaurant;
        this.orden = orden;
        this.descripcion = descripcion;
        if (ruta != null && restaurant != null) {
            this.id = new RutaSugeridaId(ruta.getId(), restaurant.getId());
        }
    }

    // --- Getters & Setters ---
    public RutaSugeridaId getId() { return id; }
    public void setId(RutaSugeridaId id) { this.id = id; }

    public Ruta getRuta() { return ruta; }
    public void setRuta(Ruta ruta) {
        this.ruta = ruta;
        if (ruta != null) {
            if (this.id == null) this.id = new RutaSugeridaId();
            this.id.setRutaId(ruta.getId());
        }
    }

    public Restaurant getRestaurant() { return restaurant; }
    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
        if (restaurant != null) {
            if (this.id == null) this.id = new RutaSugeridaId();
            this.id.setRestaurantId(restaurant.getId());
        }
    }

    public Integer getOrden() { return orden; }
    public void setOrden(Integer orden) { this.orden = orden; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
}

package GastroNova.gastro_nova.model;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class RutaSugeridaId implements Serializable {
    private static final long serialVersionUID = 1L;

    @Column(name = "RUTA_ID", nullable = false)
    private int rutaId;

    @Column(name = "RESTAURANT_ID", nullable = false)
    private int restaurantId;

    public RutaSugeridaId() {}

    public RutaSugeridaId(int rutaId, int restaurantId) {
        this.rutaId = rutaId;
        this.restaurantId = restaurantId;
    }

    public int getRutaId() { return rutaId; }
    public void setRutaId(int rutaId) { this.rutaId = rutaId; }

    public int getRestaurantId() { return restaurantId; }
    public void setRestaurantId(int restaurantId) { this.restaurantId = restaurantId; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RutaSugeridaId)) return false;
        RutaSugeridaId that = (RutaSugeridaId) o;
        return Objects.equals(rutaId, that.rutaId)
            && Objects.equals(restaurantId, that.restaurantId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rutaId, restaurantId);
    }
}

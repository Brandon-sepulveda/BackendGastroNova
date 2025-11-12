package GastroNova.gastro_nova.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class RutasGuardadasId implements Serializable {
    private static final long serialVersionUID = 1L;

    @Column(name = "USUARIO_ID", nullable = false)
    private int usuarioId;

    @Column(name = "RUTA_ID", nullable = false)
    private int rutaId;

    public RutasGuardadasId() {}
    public RutasGuardadasId(int usuarioId, int rutaId) {
        this.usuarioId = usuarioId;
        this.rutaId = rutaId;
    }

    public int getUsuarioId() { return usuarioId; }
    public void setUsuarioId(int usuarioId) { this.usuarioId = usuarioId; }

    public int getRutaId() { return rutaId; }
    public void setRutaId(int rutaId) { this.rutaId = rutaId; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RutasGuardadasId)) return false;
        RutasGuardadasId that = (RutasGuardadasId) o;
        return Objects.equals(usuarioId, that.usuarioId)
            && Objects.equals(rutaId, that.rutaId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(usuarioId, rutaId);
    }
}


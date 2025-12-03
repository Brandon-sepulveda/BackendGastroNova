package GastroNova.gastro_nova.model;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class RutasGuardadasId implements Serializable {

    @Column(name = "USUARIO_ID")
    private Integer usuarioId;

    @Column(name = "RUTA_ID")
    private Integer rutaId;

    public RutasGuardadasId() {}

    public RutasGuardadasId(Integer usuarioId, Integer rutaId) {
        this.usuarioId = usuarioId;
        this.rutaId = rutaId;
    }

    public Integer getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Integer usuarioId) {
        this.usuarioId = usuarioId;
    }

    public Integer getRutaId() {
        return rutaId;
    }

    public void setRutaId(Integer rutaId) {
        this.rutaId = rutaId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RutasGuardadasId)) return false;
        RutasGuardadasId that = (RutasGuardadasId) o;
        return Objects.equals(usuarioId, that.usuarioId) &&
               Objects.equals(rutaId, that.rutaId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(usuarioId, rutaId);
    }
}

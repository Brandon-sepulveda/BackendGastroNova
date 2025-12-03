package GastroNova.gastro_nova.model;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

@Entity
@Table(name = "rutas_guardadas")
public class RutasGuardadas {

    @EmbeddedId
    private RutasGuardadasId id;

    @MapsId("usuarioId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USUARIO_ID", nullable = false)
    private Usuario usuario;

    @MapsId("rutaId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "RUTA_ID", nullable = false)
    private Ruta ruta;

    public RutasGuardadas() {}

    public RutasGuardadas(Usuario usuario, Ruta ruta) {
        this.usuario = usuario;
        this.ruta = ruta;
        // ✅ ahora coincide con Integer
        this.id = new RutasGuardadasId(usuario.getId(), ruta.getId());
    }

    public RutasGuardadasId getId() {
        return id;
    }

    public void setId(RutasGuardadasId id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Ruta getRuta() {
        return ruta;
    }

    public void setRuta(Ruta ruta) {
        this.ruta = ruta;
    }
}

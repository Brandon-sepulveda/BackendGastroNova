package GastroNova.gastro_nova.model;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Access(AccessType.FIELD)
@Table(name = "RUTAS_GUARDADAS")
public class RutasGuardadas implements Serializable {
    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private RutasGuardadasId id = new RutasGuardadasId();

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId("usuarioId")
    @JoinColumn(
        name = "USUARIO_ID",
        nullable = false,
        foreignKey = @ForeignKey(name = "FK_RG_USUARIO")
    )
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId("rutaId")
    @JoinColumn(
        name = "RUTA_ID",
        nullable = false,
        foreignKey = @ForeignKey(name = "FK_RG_RUTA")
    )
    private Ruta ruta;

    @Column(name = "CREATED_AT", nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    public RutasGuardadas() {}

    public RutasGuardadas(Usuario usuario, Ruta ruta) {
        this.usuario = usuario;
        this.ruta = ruta;
        if (usuario != null && ruta != null) {
            this.id = new RutasGuardadasId(usuario.getId(), ruta.getId());
        }
    }

    public RutasGuardadasId getId() { return id; }
    public void setId(RutasGuardadasId id) { this.id = id; }

    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
        if (usuario != null) {
            if (this.id == null) this.id = new RutasGuardadasId();
            this.id.setUsuarioId(usuario.getId());
        }
    }

    public Ruta getRuta() { return ruta; }
    public void setRuta(Ruta ruta) {
        this.ruta = ruta;
        if (ruta != null) {
            if (this.id == null) this.id = new RutasGuardadasId();
            this.id.setRutaId(ruta.getId());
        }
    }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}

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
import jakarta.persistence.OneToMany;

@Entity
@Access(AccessType.FIELD)
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nombre;
    private String apellido;
    private String correo;
    private String usuario;
    private String contrasena;
    private boolean tipo_usuario;

    // RELACIONES
    @OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY)
    private List<Resena> resenas = new ArrayList<>();

    @OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY)
    @JsonIgnore
    private java.util.List<RutasGuardadas> rutasGuardadas = new java.util.ArrayList<>();

    public Usuario() {
        this.nombre = "";
        this.apellido = "";
        this.correo = "";
        this.usuario = "";
        this.contrasena = "";
        this.tipo_usuario = false;
    }
    public Usuario(String nombre, String apellido, int rut_num, String rut_v, String correo, String usuario,
            String contrasena, boolean tipo_usuario) {
        this.nombre = nombre;
        this.apellido = apellido;

        this.correo = correo;
        this.usuario = usuario;
        this.contrasena = contrasena;
        this.tipo_usuario = tipo_usuario;
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
    public String getApellido() {
        return apellido;
    }
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
    public String getCorreo() {
        return correo;
    }
    public void setCorreo(String correo) {
        this.correo = correo;
    }
    public String getUsuario() {
        return usuario;
    }
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    public String getContrasena() {
        return contrasena;
    }
    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
    public boolean isTipo_usuario() {
        return tipo_usuario;
    }
    public void setTipo_usuario(boolean tipo_usuario) {
        this.tipo_usuario = tipo_usuario;
    }
    public List<Resena> getResenas() {
        return resenas;
    }
    public void setResenas(List<Resena> resenas) {
        this.resenas = resenas;
    }
    public java.util.List<RutasGuardadas> getRutasGuardadas() {
        return rutasGuardadas;
    }
    public void setRutasGuardadas(java.util.List<RutasGuardadas> rutasGuardadas) {
        this.rutasGuardadas = rutasGuardadas;
    }

    

}

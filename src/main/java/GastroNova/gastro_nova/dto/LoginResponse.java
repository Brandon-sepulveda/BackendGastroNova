package GastroNova.gastro_nova.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LoginResponse {

    private boolean success;
    private Integer id;
    private String nombre;
    private String apellido;
    private String correo;
    private String usuario;

    // 👉 campo nuevo: se serializa como "tipo_usuario"
    @JsonProperty("tipo_usuario")
    private boolean tipoUsuario;

    public LoginResponse() {
    }

    public LoginResponse(
            boolean success,
            Integer id,
            String nombre,
            String apellido,
            String correo,
            String usuario,
            boolean tipoUsuario
    ) {
        this.success = success;
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.usuario = usuario;
        this.tipoUsuario = tipoUsuario;
    }

    // getters & setters

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public boolean isTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(boolean tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }
}

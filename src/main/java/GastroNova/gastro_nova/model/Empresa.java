package GastroNova.gastro_nova.model;

import jakarta.persistence.Access;
import jakarta.persistence.AccessType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
@Access(AccessType.FIELD)
public class Empresa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nombre;
    private int rut_num;
    private String rut_v;

    // =====================
    //   CONSTRUCTORES
    // =====================

    public Empresa() {
        this.nombre = "";
        this.rut_num = 0;
        this.rut_v = "";
    }

    public Empresa(String nombre, int rut_num, String rut_v) {
        this.nombre = nombre;
        this.rut_num = rut_num;
        this.rut_v = rut_v;
    }

    // =====================
    //  GETTERS / SETTERS
    // =====================

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

    public int getRut_num() {
        return rut_num;
    }

    public void setRut_num(int rut_num) {
        this.rut_num = rut_num;
    }

    public String getRut_v() {
        return rut_v;
    }

    public void setRut_v(String rut_v) {
        this.rut_v = rut_v;
    }
}
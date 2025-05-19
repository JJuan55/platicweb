
package com.plasticweb.entidad;

import jakarta.persistence.*;
import java.time.LocalDateTime;

/**
 * Entidad que representa al usuario registrado en el sistema.
 * Esta clase se mapea directamente con la tabla 'usuarios' en la base de datos.
 */
@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String apellido;

    @Column(unique = true)
    private String correo;
    private String clave;
    private String rol;      // Valor por defecto: "usuario"
    private String estado;   // Valor por defecto: "activo"

    private LocalDateTime fechaCreacion;


    public Long getId() {
        return id;
    }

    public void setNombre (String nombre){
        this.nombre = nombre;
    }

    public String getNombre(){
        return nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getApellido(){
        return apellido;
    }
    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getRol() {
        return rol;
    }
    public void setRol(String rol) {
        this.rol = rol;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getEstado() {
        return estado;
    }
    public void setFechaCreacion(LocalDateTime fechaCreacion) {
            this.fechaCreacion = fechaCreacion;
    }
    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setClave(Usuario clave) {
        this.clave = clave.getCorreo();
    }


}

package com.plasticweb.dto;

/**
 * Clase que envía la respuesta al frontend después de procesar el registro.
 */
public class RespuestaRegistro {
    public boolean verificado;
    public String mensaje;

    public RespuestaRegistro(boolean verificado, String mensaje) {
        this.verificado = verificado;
        this.mensaje = mensaje;
    }
    public boolean isVerificado() {
        return verificado;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setVerificado(boolean verificado) {
        this.verificado = verificado;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}

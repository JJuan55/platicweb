package dto;

public class RespuestaSesion {

    private boolean exito;
    private String mensaje;

    public RespuestaSesion(boolean exito, String mensaje) {
        this.exito = exito;
        this.mensaje = mensaje;
    }

    public boolean isExito() {
        return exito;
    }

    public String getMensaje() {
        return mensaje;
    }
}

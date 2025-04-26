package controller;

import dto.PeticionInicioSesion;
import dto.RespuestaSesion;
import service.ServicioSesion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/sesion")
public class ControladorSesion {

    @Autowired
    private ServicioSesion servicioSesion;

    /**
     * Punto de entrada desde frontend para iniciar sesión.
     * @param peticion contiene correo y clave del usuario
     * @return respuesta con éxito o error
     */
    @PostMapping("/iniciar")
    public RespuestaSesion iniciarSesion(@RequestBody PeticionInicioSesion peticion) {
        return servicioSesion.iniciarSesion(peticion);
    }
}

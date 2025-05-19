package com.plasticweb.controlador;

import com.plasticweb.dto.VerificacionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.plasticweb.dto.PeticionRegistro;
import com.plasticweb.dto.RespuestaRegistro;
import com.plasticweb.servicio.ServicioRegistro;

/**
 * Controlador REST que expone la ruta POST /api/registro
 * para permitir que el frontend envíe los datos del formulario.
 */
@RestController
@RequestMapping("/api/registro")
@CrossOrigin(origins = "*") // Permite acceso desde cualquier frontend (puedes restringirlo si quieres)
public class ControladorRegistro {

    @Autowired
    private ServicioRegistro servicioRegistro;

    @PostMapping
    public ResponseEntity<RespuestaRegistro> registrar(@RequestBody PeticionRegistro peticion) {
        System.out.println("[DEBUG] JSON recibido: " + peticion);
        RespuestaRegistro respuesta = servicioRegistro.iniciarRegistro(peticion);
        return ResponseEntity.ok(respuesta);
    }

    @PostMapping("/verificar")
    public ResponseEntity<RespuestaRegistro> verificar(@RequestBody VerificacionDTO dto) {
        RespuestaRegistro respuesta = servicioRegistro.verificarCodigo(dto.getCorreo(), dto.getCodigo());
        dto.toString();
        return ResponseEntity.ok(respuesta);
    }
}

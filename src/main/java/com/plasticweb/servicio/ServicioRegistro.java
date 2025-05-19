package com.plasticweb.servicio;

import com.plasticweb.dto.PeticionRegistro;
import com.plasticweb.dto.RespuestaRegistro;
import com.plasticweb.entidad.Usuario;
import com.plasticweb.repositorio.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class ServicioRegistro {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Autowired
    private ServicioCorreo servicioCorreo;

    // Almacenan peticiones pendientes y sus códigos de verificación
    private Map<String, PeticionRegistro> usuariosPendientes = new HashMap<>();
    private Map<String, String> codigosPendientes = new HashMap<>();

    private BCryptPasswordEncoder encriptador = new BCryptPasswordEncoder();

    /**
     * Inicia el proceso de registro: valida los datos y envía un código de verificación al correo.
     */
    public RespuestaRegistro iniciarRegistro(PeticionRegistro peticion) {
        System.out.println("[ServicioRegistro] Iniciando registro para: " + peticion.getCorreo());

        // Validación de campos obligatorios
        if (peticion.getNombre() == null || peticion.getApellido() == null ||
                peticion.getCorreo() == null || peticion.getContrasena() == null) {
            System.out.println("[ServicioRegistro] Faltan campos obligatorios.");
            return new RespuestaRegistro(false, "Todos los campos son obligatorios.");
        }

        String correo = peticion.getCorreo();

        // Verificar si ya existe un usuario con ese correo en la base de datos
        if (usuarioRepositorio.findByCorreo(correo).isPresent()) {
            System.out.println("[ServicioRegistro] El correo ya está registrado.");
            return new RespuestaRegistro(false, "El correo ya está registrado.");
        }

        // Verificar si ya hay un registro pendiente no verificado para ese correo
        if (usuariosPendientes.containsKey(correo)) {
            System.out.println("[ServicioRegistro] Ya hay un registro pendiente para este correo.");
            return new RespuestaRegistro(false, "Ya hay un registro pendiente para este correo. Verifica tu bandeja de entrada.");
        }

        // Generar un código de verificación único
        String codigo = generarCodigoVerificacion();
        System.out.println("[ServicioRegistro] Código generado: " + codigo);

        // Guardar datos temporales (registro aún no confirmado)
        codigosPendientes.put(correo, codigo);
        usuariosPendientes.put(correo, peticion);

        System.out.println("[ServicioRegistro] Datos temporales guardados. Enviando correo...");

        // Enviar el código al correo del usuario
        servicioCorreo.enviarCodigo(correo, codigo);

        System.out.println("[ServicioRegistro] Código de verificación enviado.");

        return new RespuestaRegistro(true, "Código de verificación enviado al correo.");
    }

    /**
     * Verifica el código enviado por el usuario y completa el registro si es válido.
     */
    public RespuestaRegistro verificarCodigo(String correo, String codigo) {
        System.out.println("[ServicioRegistro] Verificando código para: " + correo);

        String codigoCorrecto = codigosPendientes.get(correo);

        // Verificar si el código es válido
        if (codigoCorrecto == null || !codigoCorrecto.equals(codigo)) {
            System.out.println("[ServicioRegistro] Código incorrecto o expirado.");
            return new RespuestaRegistro(false, "Código de verificación incorrecto o expirado.");
        }

        // Obtener la petición de registro temporal
        PeticionRegistro peticion = usuariosPendientes.get(correo);
        if (peticion == null) {
            System.out.println("[ServicioRegistro] No se encontró una petición pendiente para este correo.");
            return new RespuestaRegistro(false, "No hay registro pendiente para este correo.");
        }

        // Crear nuevo usuario con datos verificados
        Usuario nuevo = new Usuario();
        nuevo.setNombre(peticion.getNombre());
        nuevo.setApellido(peticion.getApellido());
        nuevo.setCorreo(peticion.getCorreo());
        nuevo.setClave(encriptador.encode(peticion.getContrasena()));
        nuevo.setRol("usuario"); // Valor por defecto, podría variar
        nuevo.setEstado("activo");
        nuevo.setFechaCreacion(LocalDateTime.now());

        // Guardar usuario en la base de datos
        usuarioRepositorio.save(nuevo);

        System.out.println("[ServicioRegistro] Usuario registrado exitosamente en la base de datos.");

        // Eliminar registros temporales del mapa
        codigosPendientes.remove(correo);
        usuariosPendientes.remove(correo);

        System.out.println("[ServicioRegistro] Datos temporales eliminados.");

        return new RespuestaRegistro(true, "Registro exitoso.");
    }

    /**
     * Genera un código alfanumérico de 6 caracteres para verificación.
     */
    private String generarCodigoVerificacion() {
        return UUID.randomUUID().toString().replace("-", "").substring(0, 6).toUpperCase();
    }
}

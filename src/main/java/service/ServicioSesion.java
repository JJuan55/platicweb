package service;

import dto.PeticionInicioSesion;
import dto.RespuestaSesion;
import modelo.Usuario;
import repository.RepositorioUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ServicioSesion {

    @Autowired
    private RepositorioUsuario repositorioUsuario;

  
    public RespuestaSesion iniciarSesion(PeticionInicioSesion peticion) {
        Optional<Usuario> usuario = repositorioUsuario.findByCorreo(peticion.getCorreo());

        // Verificar existencia y clave
        if (usuario.isPresent() && usuario.get().getClave().equals(peticion.getClave())) {
            return new RespuestaSesion(true, "Inicio de sesión exitoso");
        } else {
            return new RespuestaSesion(false, "Credenciales inválidas");
        }
    }
}

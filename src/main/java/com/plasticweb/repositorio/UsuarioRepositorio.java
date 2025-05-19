package com.plasticweb.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import com.plasticweb.entidad.Usuario;
import java.util.Optional;

/**
 * Repositorio que permite acceder a la base de datos y consultar la tabla 'usuarios'.
 */
public interface UsuarioRepositorio extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByCorreo(String correo);
}

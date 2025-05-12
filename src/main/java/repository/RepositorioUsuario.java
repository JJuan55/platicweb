package repository;

import modelo.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface RepositorioUsuario extends JpaRepository<Usuario, Long> {

    // Método para buscar un usuario por correo
    Optional<Usuario> findByCorreo(String correo);
}


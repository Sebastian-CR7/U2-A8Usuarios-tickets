package mx.edu.utez.Usuarios_tickets.repository.usuario;


import mx.edu.utez.Usuarios_tickets.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

}

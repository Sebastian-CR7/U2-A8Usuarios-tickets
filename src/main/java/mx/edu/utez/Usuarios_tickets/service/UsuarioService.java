package mx.edu.utez.Usuarios_tickets.service;


import mx.edu.utez.Usuarios_tickets.controller.dto.UsuarioRequest;
import mx.edu.utez.Usuarios_tickets.controller.dto.UsuarioResponse;
import mx.edu.utez.Usuarios_tickets.exception.customExceptions.NotFoundException;
import mx.edu.utez.Usuarios_tickets.model.Usuario;
import mx.edu.utez.Usuarios_tickets.repository.usuario.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Transactional
    public UsuarioResponse crearUsuario(UsuarioRequest payload) {
        Usuario usuario = new Usuario();
        usuario.setNombre(payload.getNombre());
        usuario.setActivo(true);

        return UsuarioResponse.from(usuarioRepository.save(usuario));
    }

    @Transactional(readOnly = true)
    public UsuarioResponse obtenerUsuario(Long id) {
        return UsuarioResponse.from(buscarUsuario(id));
    }

    @Transactional
    public UsuarioResponse desactivarUsuario(Long id) {
        Usuario usuario = buscarUsuario(id);
        usuario.setActivo(false);

        return UsuarioResponse.from(usuarioRepository.save(usuario));
    }

    public Usuario buscarUsuario(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("El usuario con id " + id + " no existe"));
    }
}

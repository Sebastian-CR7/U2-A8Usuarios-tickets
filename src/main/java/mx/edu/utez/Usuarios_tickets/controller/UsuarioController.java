package mx.edu.utez.Usuarios_tickets.controller;


import jakarta.validation.Valid;
import mx.edu.utez.Usuarios_tickets.controller.dto.TicketResponse;
import mx.edu.utez.Usuarios_tickets.controller.dto.UsuarioRequest;
import mx.edu.utez.Usuarios_tickets.controller.dto.UsuarioResponse;
import mx.edu.utez.Usuarios_tickets.service.TicketService;
import mx.edu.utez.Usuarios_tickets.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {


    private final UsuarioService usuarioService;
    private final TicketService ticketService;

    public UsuarioController(UsuarioService usuarioService, TicketService ticketService) {
        this.usuarioService = usuarioService;
        this.ticketService = ticketService;
    }

    @PostMapping
    public ResponseEntity<UsuarioResponse> crear(@Valid @RequestBody UsuarioRequest payload) {
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.crearUsuario(payload));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponse> obtener(@PathVariable Long id) {
        return ResponseEntity.ok(usuarioService.obtenerUsuario(id));
    }

    @PatchMapping("/{id}/desactivar")
    public ResponseEntity<UsuarioResponse> desactivar(@PathVariable Long id) {
        return ResponseEntity.ok(usuarioService.desactivarUsuario(id));
    }

    @GetMapping("/{id}/tickets")
    public ResponseEntity<List<TicketResponse>> obtenerTickets(@PathVariable Long id) {
        return ResponseEntity.ok(ticketService.obtenerTicketsUsuario(id));
    }
}

package mx.edu.utez.Usuarios_tickets.controller;


import jakarta.validation.Valid;
import mx.edu.utez.Usuarios_tickets.controller.dto.CambiarPrioridadRequest;
import mx.edu.utez.Usuarios_tickets.controller.dto.CrearTicketRequest;
import mx.edu.utez.Usuarios_tickets.controller.dto.TicketResponse;
import mx.edu.utez.Usuarios_tickets.service.TicketService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {

    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @PostMapping
    public ResponseEntity<TicketResponse> crear(@Valid @RequestBody CrearTicketRequest payload) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ticketService.crearTicket(payload));
    }

    @PatchMapping("/{id}/estado")
    public ResponseEntity<TicketResponse> actualizarEstado(@PathVariable Long id) {
        return ResponseEntity.ok(ticketService.actualizarEstadoTicket(id));
    }

    @PatchMapping("/{id}/prioridad")
    public ResponseEntity<TicketResponse> reasignarPrioridad(@PathVariable Long id, @Valid @RequestBody CambiarPrioridadRequest payload) {
        return ResponseEntity.ok(ticketService.reasignarPrioridad(id, payload));
    }
}

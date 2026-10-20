package mx.edu.utez.Usuarios_tickets.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.Usuarios_tickets.model.EstadoTicket;
import mx.edu.utez.Usuarios_tickets.model.PrioridadTicket;
import mx.edu.utez.Usuarios_tickets.model.Ticket;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TicketResponse {

    private Long id;
    private String descripcion;
    private EstadoTicket estado;
    private PrioridadTicket prioridad;
    private Long usuarioId;

    public static TicketResponse from(Ticket ticket) {
        return new TicketResponse(ticket.getId(), ticket.getDescripcion(), ticket.getEstado(),
                ticket.getPrioridad(), ticket.getUsuario().getId());
    }
}

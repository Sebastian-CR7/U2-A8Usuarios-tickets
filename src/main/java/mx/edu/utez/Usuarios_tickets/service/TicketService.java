package mx.edu.utez.Usuarios_tickets.service;


import mx.edu.utez.Usuarios_tickets.controller.dto.CambiarPrioridadRequest;
import mx.edu.utez.Usuarios_tickets.controller.dto.CrearTicketRequest;
import mx.edu.utez.Usuarios_tickets.controller.dto.TicketResponse;
import mx.edu.utez.Usuarios_tickets.exception.customExceptions.BadRequestException;
import mx.edu.utez.Usuarios_tickets.exception.customExceptions.NotFoundException;
import mx.edu.utez.Usuarios_tickets.model.EstadoTicket;
import mx.edu.utez.Usuarios_tickets.model.Ticket;
import mx.edu.utez.Usuarios_tickets.model.Usuario;
import mx.edu.utez.Usuarios_tickets.repository.ticket.TicketRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TicketService {

    private final TicketRepository ticketRepository;
    private final UsuarioService usuarioService;

    public TicketService(TicketRepository ticketRepository, UsuarioService usuarioService) {
        this.ticketRepository = ticketRepository;
        this.usuarioService = usuarioService;
    }

    @Transactional
    public TicketResponse crearTicket(CrearTicketRequest payload) {
        Usuario usuario = usuarioService.buscarUsuario(payload.getUsuarioId());
        if (!usuario.getActivo()) {
            throw new BadRequestException("El usuario con id " + usuario.getId() + " no esta activo");
        }

        if (payload.getDescripcion() == null || payload.getDescripcion().trim().isEmpty()) {
            throw new BadRequestException("La descripcion no puede estar vacia");
        }

        Ticket ticket = new Ticket();
        ticket.setUsuario(usuario);
        ticket.setDescripcion(payload.getDescripcion());
        ticket.setEstado(EstadoTicket.ABIERTO);
        ticket.setPrioridad(payload.getPrioridad());

        return TicketResponse.from(ticketRepository.save(ticket));
    }

    @Transactional
    public TicketResponse actualizarEstadoTicket(Long ticketId) {
        Ticket ticket = buscarTicket(ticketId);

        if (ticket.getEstado() == EstadoTicket.CERRADO) {
            throw new BadRequestException("El ticket con id " + ticketId + " esta CERRADO y no se puede modificar");
        }

        if (ticket.getEstado() == EstadoTicket.ABIERTO) {
            ticket.setEstado(EstadoTicket.EN_PROCESO);
        } else {
            ticket.setEstado(EstadoTicket.CERRADO);
        }

        return TicketResponse.from(ticketRepository.save(ticket));
    }

    @Transactional
    public TicketResponse reasignarPrioridad(Long ticketId, CambiarPrioridadRequest payload) {
        Ticket ticket = buscarTicket(ticketId);

        if (ticket.getEstado() == EstadoTicket.CERRADO) {
            throw new BadRequestException("El ticket con id " + ticketId + " esta CERRADO y no se puede modificar");
        }

        ticket.setPrioridad(payload.getPrioridad());

        return TicketResponse.from(ticketRepository.save(ticket));
    }

    @Transactional(readOnly = true)
    public List<TicketResponse> obtenerTicketsUsuario(Long usuarioId) {
        usuarioService.buscarUsuario(usuarioId);

        return ticketRepository.findByUsuarioId(usuarioId)
                .stream()
                .map(TicketResponse::from)
                .toList();
    }

    private Ticket buscarTicket(Long id) {
        return ticketRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("El ticket con id " + id + " no existe"));
    }
}

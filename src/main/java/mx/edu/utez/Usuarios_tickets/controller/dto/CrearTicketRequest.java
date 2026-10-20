package mx.edu.utez.Usuarios_tickets.controller.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.Usuarios_tickets.model.PrioridadTicket;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CrearTicketRequest {

    @NotNull(message = "El id del usuario es obligatorio")
    private Long usuarioId;

    @NotBlank(message = "La descripcion es obligatoria")
    private String descripcion;

    @NotNull(message = "La prioridad es obligatoria")
    private PrioridadTicket prioridad;
}

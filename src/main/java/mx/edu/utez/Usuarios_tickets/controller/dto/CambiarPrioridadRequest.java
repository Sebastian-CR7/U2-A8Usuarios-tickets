package mx.edu.utez.Usuarios_tickets.controller.dto;

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
public class CambiarPrioridadRequest {

    @NotNull(message = "La prioridad es obligatoria")
    private PrioridadTicket prioridad;
}

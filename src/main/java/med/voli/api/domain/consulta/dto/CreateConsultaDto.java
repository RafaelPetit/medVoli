package med.voli.api.domain.consulta.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import med.voli.api.domain.medico.Especialidade;

import java.time.LocalDateTime;

public record CreateConsultaDto(
        Long idMedico,

        Especialidade especialidade,

        @NotNull
        Long idPaciente,

        @NotNull
        LocalDateTime data
) {
}

package med.voli.api.domain.consulta.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public record ResponseCreateConsultaDto(
        Long id,
        Long idMedico,
        Long idPaciente,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
        LocalDateTime data
    ) {
}

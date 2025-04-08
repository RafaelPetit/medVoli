package med.voli.api.domain.consulta.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import med.voli.api.domain.consulta.Consulta;

import java.time.LocalDateTime;

public record ResponseCreateConsultaDto(
        Long id,
        Long idMedico,
        Long idPaciente,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
        LocalDateTime data
    ) {
    public ResponseCreateConsultaDto(Consulta consulta) {
        this(consulta.getId(), consulta.getMedico().getId(), consulta.getPaciente().getId(), consulta.getData());
    }
}

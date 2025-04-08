package med.voli.api.domain.consulta.validations;

import med.voli.api.domain.consulta.dto.CreateConsultaDto;

public interface AppointmentSchedulerValidator {
    void validate(CreateConsultaDto createConsultaDto);
}

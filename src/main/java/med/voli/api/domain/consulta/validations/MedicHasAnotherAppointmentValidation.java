package med.voli.api.domain.consulta.validations;

import med.voli.api.domain.consulta.ConsultaRepository;
import med.voli.api.domain.consulta.dto.CreateConsultaDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MedicHasAnotherAppointmentValidation implements AppointmentSchedulerValidator {

    @Autowired
    private ConsultaRepository consultaRepository;

    public void validate(CreateConsultaDto createConsultaDto) {
        var medicHasAnotherAppointment = consultaRepository.existsByMedicoIdAndData(createConsultaDto.idMedico(), createConsultaDto.data());
    }

}

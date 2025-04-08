package med.voli.api.domain.consulta.validations;

import med.voli.api.domain.consulta.ConsultaRepository;
import med.voli.api.domain.consulta.dto.CreateConsultaDto;
import med.voli.api.infra.exception.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PatientHasAnotherAppointment implements AppointmentSchedulerValidator {

    @Autowired
    private ConsultaRepository consultaRepository;

    public void validate(CreateConsultaDto createConsultaDto) {
        var firstAppointment = createConsultaDto.data().withHour(7);
        var lastAppointment = createConsultaDto.data().withHour(18);
        var patientHasAnotherAppointment = consultaRepository.existsByPacienteIdAndDataBetween(createConsultaDto.idPaciente(), firstAppointment, lastAppointment);

        if(patientHasAnotherAppointment) {
            throw new ValidationException("Paciente já possui consulta marcada para este dia");
        }

    }


}

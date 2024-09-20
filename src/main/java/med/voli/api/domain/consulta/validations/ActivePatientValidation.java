package med.voli.api.domain.consulta.validations;

import jakarta.validation.ValidationException;
import med.voli.api.domain.consulta.dto.CreateConsultaDto;
import med.voli.api.domain.paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ActivePatientValidation implements AppointmentSchedulerValidator {

    @Autowired
    private static PacienteRepository pacienteRepository;

    public void validate(CreateConsultaDto createConsultaDto) {
        var activePatient = pacienteRepository.findActiveById(createConsultaDto.idPaciente());
        if(!activePatient) {
            throw new ValidationException("Patient is not active");
        }
    }
}

package med.voli.api.domain.consulta.validations;

import med.voli.api.domain.consulta.dto.CreateConsultaDto;
import med.voli.api.domain.paciente.PacienteRepository;
import med.voli.api.infra.exception.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ActivePatientValidation implements AppointmentSchedulerValidator {


    @Autowired
    private PacienteRepository pacienteRepository;

    public void validate(CreateConsultaDto createConsultaDto) {
        if (createConsultaDto.idMedico() == null) {
            throw new ValidationException("Patient cant be null");
        }

        var activePatient = pacienteRepository.findActiveById(createConsultaDto.idPaciente());
        if(!activePatient) {
            throw new ValidationException("Patient is not active");
        }
    }
}

package med.voli.api.domain.consulta.validations;

import jakarta.validation.ValidationException;
import med.voli.api.domain.consulta.dto.CreateConsultaDto;
import med.voli.api.domain.medico.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ActiveMedicValidation implements AppointmentSchedulerValidator {

    @Autowired
    private MedicoRepository medicoRepository;

    public void validate(CreateConsultaDto createConsultaDto) {
        if (createConsultaDto.idMedico() == null) {
            return;
        }

        var activeMedic = medicoRepository.findActiveById(createConsultaDto.idMedico());
        if(!activeMedic) {
            throw new ValidationException("Medic is not active");
        }
    }
}

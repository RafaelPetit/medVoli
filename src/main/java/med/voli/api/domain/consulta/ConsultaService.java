package med.voli.api.domain.consulta;

import jakarta.validation.ValidationException;
import med.voli.api.domain.consulta.dto.CreateConsultaDto;
import med.voli.api.domain.medico.Medico;
import med.voli.api.domain.medico.MedicoRepository;
import med.voli.api.domain.paciente.PacienteRepository;
import med.voli.api.infra.exception.ExistingValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConsultaService {

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    public void create(CreateConsultaDto createConsultaDto) {
        if (!pacienteRepository.existsById(createConsultaDto.idPaciente())) {
            throw new ExistingValidationException("Paciente not found");
        }

        if (createConsultaDto.idMedico() !=null && !pacienteRepository.existsById(createConsultaDto.idMedico())) {
            throw new ExistingValidationException("Medic not found");
        }

        var paciente = pacienteRepository.findById(createConsultaDto.idPaciente()).get();
        var medico = findMedico(createConsultaDto);
        var consulta = new Consulta(null, medico, paciente, createConsultaDto.data(), true);

        consultaRepository.save(consulta);
    }

    private Medico findMedico(CreateConsultaDto createConsultaDto) {
        if (createConsultaDto.idMedico() != null) {
            return medicoRepository.getReferenceById(createConsultaDto.idMedico());
        }

        if(createConsultaDto.especialidade() == null) {
            throw new ValidationException("Especialidade is required when medic is not informed");
        }

        return medicoRepository.findFirstByEspecialidade(createConsultaDto.especialidade(), createConsultaDto.data());
    }
}

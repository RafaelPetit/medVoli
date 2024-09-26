package med.voli.api.domain.consulta;

import med.voli.api.domain.consulta.dto.CreateConsultaDto;
import med.voli.api.domain.consulta.dto.ResponseCreateConsultaDto;
import med.voli.api.domain.consulta.validations.AppointmentSchedulerValidator;
import med.voli.api.domain.medico.Medico;
import med.voli.api.domain.medico.MedicoRepository;
import med.voli.api.domain.paciente.PacienteRepository;
import med.voli.api.infra.exception.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsultaService {

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;


    @Autowired
    private List<AppointmentSchedulerValidator> validations;

    public ResponseCreateConsultaDto create(CreateConsultaDto createConsultaDto) {
        if (!pacienteRepository.existsById(createConsultaDto.idPaciente())) {
            throw new ValidationException("Paciente not found");
        }

        if (createConsultaDto.idMedico() !=null && !pacienteRepository.existsById(createConsultaDto.idMedico())) {
            throw new ValidationException("Medic not found");
        }

        validations.forEach(v -> v.validate(createConsultaDto));

        var paciente = pacienteRepository.findById(createConsultaDto.idPaciente()).get();
        var medico = findMedico(createConsultaDto);
        if(medico == null) {
            throw new ValidationException(" there isn't any available Medic for this date");
        }



        var consulta = new Consulta(null, medico, paciente, createConsultaDto.data(), true);

        consultaRepository.save(consulta);

        return new ResponseCreateConsultaDto(consulta);
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

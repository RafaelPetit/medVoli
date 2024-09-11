package med.voli.api.domain.paciente.dto;

import med.voli.api.domain.paciente.Paciente;

public record ResponseListPacienteDto(String nome, String email, String cpf){
    public ResponseListPacienteDto(Paciente paciente) {
        this(paciente.getNome(), paciente.getEmail(), paciente.getCpf());
    }
}

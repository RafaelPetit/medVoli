package med.voli.api.paciente.dto;

import med.voli.api.paciente.Paciente;

public record ResponseListPacienteDto(String nome, String email, String cpf){
    public ResponseListPacienteDto(Paciente paciente) {
        this(paciente.getNome(), paciente.getEmail(), paciente.getCpf());
    }
}

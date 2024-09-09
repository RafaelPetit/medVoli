package med.voli.api.paciente.dto;

import med.voli.api.endereco.Endereco;
import med.voli.api.paciente.Paciente;

public record ResponsePacienteDto(
        Long id,
        String nome,
        String email,
        String telefone,
        String cpf,
        Endereco endereco) {
    public ResponsePacienteDto(Paciente paciente) {
        this(paciente.getId(), paciente.getNome(), paciente.getEmail(), paciente.getTelefone(), paciente.getCpf(), paciente.getEndereco());
    }
}
